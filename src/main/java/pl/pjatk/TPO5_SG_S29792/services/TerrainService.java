package pl.pjatk.TPO5_SG_S29792.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pjatk.TPO5_SG_S29792.dto.GetTerrainResponse;
import pl.pjatk.TPO5_SG_S29792.dto.GetTileResponse;
import pl.pjatk.TPO5_SG_S29792.dto.GetTileUpgradeResponse;
import pl.pjatk.TPO5_SG_S29792.models.terrain.Terrain;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;
import pl.pjatk.TPO5_SG_S29792.models.tile_upgrades.TileUpgrade;
import pl.pjatk.TPO5_SG_S29792.repositories.TerrainRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TerrainService {
    private final TerrainRepository terrainRepository;

    public Terrain getByPosition(int posX, int posY) {
        return terrainRepository
                .findById(new TerrainId(posX, posY))
                .orElseThrow(() -> new IllegalArgumentException("Terrain not found"));
    }

    public GetTerrainResponse getAll(String sort) {
        List<Terrain> tiles = terrainRepository.findAll(Sort.by(sort));
        List<GetTileResponse> tileDtos = new ArrayList<>();

        tiles.forEach(tile -> {
            tileDtos.add(new GetTileResponse(
                    tile.getId().getPosX(),
                    tile.getId().getPosY(),
                    tile.getType(),
                    null // TODO complete
            ));
        });

        return new GetTerrainResponse(tileDtos);
    }
}
