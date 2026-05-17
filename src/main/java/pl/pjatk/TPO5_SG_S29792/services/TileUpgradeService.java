package pl.pjatk.TPO5_SG_S29792.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pjatk.TPO5_SG_S29792.dto.GetTileUpgradeResponse;
import pl.pjatk.TPO5_SG_S29792.dto.GetUnitResponse;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.models.tile_upgrades.*;
import pl.pjatk.TPO5_SG_S29792.repositories.TerrainRepository;
import pl.pjatk.TPO5_SG_S29792.repositories.TileUpgradeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TileUpgradeService {
    private final TileUpgradeRepository tileUpgradeRepository;
    private final TerrainService terrainService;

    private TileUpgrade findById(Long id) {
        return tileUpgradeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tile upgrade not found"));
    }

    public List<GetTileUpgradeResponse> getAll(String sort) {
        List<TileUpgrade> tileUpgrades = tileUpgradeRepository.findAll(Sort.by(sort));
        List<GetTileUpgradeResponse> tileUpgradeDtos = new ArrayList<>();

        tileUpgrades.forEach(tileUpgrade -> {
            tileUpgradeDtos.add(new GetTileUpgradeResponse(
                    tileUpgrade.getTerrain().getId().getPosX(),
                    tileUpgrade.getTerrain().getId().getPosY(),
                    tileUpgrade.getType(),
                    tileUpgrade.getFoodBonus(),
                    tileUpgrade.getProductionBonus(),
                    tileUpgrade.isLandUpgrade(),
                    tileUpgrade.isWaterUpgrade()
            ));
        });

        return tileUpgradeDtos;
    }

    public GetTileUpgradeResponse createTileUpgrade(TileUpgrades type, int posX, int posY) {
        TileUpgrade tileUpgrade = switch (type) {
            case CITY -> new City();
            case FARM -> new Farm();
            case FISH_BOAT -> new FishBoat();
            case MILL -> new Mill();
        };

        tileUpgrade.setTerrain(
                terrainService.getByPosition(posX, posY)
        );

        tileUpgradeRepository.save(tileUpgrade);

        return new GetTileUpgradeResponse(
                tileUpgrade.getTerrain().getId().getPosX(),
                tileUpgrade.getTerrain().getId().getPosY(),
                tileUpgrade.getType(),
                tileUpgrade.getFoodBonus(),
                tileUpgrade.getProductionBonus(),
                tileUpgrade.isLandUpgrade(),
                tileUpgrade.isWaterUpgrade()
        );
    }

    public void deleteTileUpgrade(Long id) {
        TileUpgrade tileUpgrade = findById(id);
        tileUpgrade.getTerrain().setTileUpgrade(null);
        tileUpgradeRepository.delete(findById(id));
    }
}
