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
                    tile.getTileUpgrade() != null ? tile.getTileUpgrade().getType() : null
            ));
        });

        return new GetTerrainResponse(tileDtos);
    }
}
