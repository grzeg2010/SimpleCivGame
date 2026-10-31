/*
 * SimpleCivGame: Turn-based strategy game inspired by Sid Meier's Civilization V, built with Spring Boot.
 * Copyright (C) 2026 Grzegorz Śnieżko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package pl.pjatk.TPO5_SG_S29792.services;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
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
    private final CityService cityService;

    public TileUpgrade findById(Long id) {
        return tileUpgradeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tile upgrade not found"));
    }

    public TileUpgrade findByPosition(int posX, int posY) {
        return tileUpgradeRepository
                .findByTerrainIdPosXAndTerrainIdPosY(posX, posY)
                .orElseThrow(() -> new IllegalArgumentException("Tile upgrade not found"));
    }

    public List<GetTileUpgradeResponse> getAll(String sort) {
        List<TileUpgrade> tileUpgrades = tileUpgradeRepository.findAll(Sort.by(sort));
        List<GetTileUpgradeResponse> tileUpgradeDtos = new ArrayList<>();

        tileUpgrades.forEach(tileUpgrade -> {
            tileUpgradeDtos.add(mapGetTileUpgradeResponse(tileUpgrade));
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

        return mapGetTileUpgradeResponse(tileUpgrade);
    }

    public void deleteTileUpgrade(Long id) {
        TileUpgrade tileUpgrade = findById(id);
        if(tileUpgrade.getType() == TileUpgrades.CITY) {
            cityService.deleteCity(id);
        }
        tileUpgrade.getTerrain().setTileUpgrade(null);
        tileUpgradeRepository.delete(tileUpgrade);
    }

    private static @NonNull GetTileUpgradeResponse mapGetTileUpgradeResponse(TileUpgrade tileUpgrade) {
        return new GetTileUpgradeResponse(
                tileUpgrade.getId(),
                tileUpgrade.getTerrain().getId().getPosX(),
                tileUpgrade.getTerrain().getId().getPosY(),
                tileUpgrade.getType(),
                tileUpgrade.getFoodBonus(),
                tileUpgrade.getProductionBonus(),
                tileUpgrade.isLandUpgrade(),
                tileUpgrade.isWaterUpgrade()
        );
    }
}
