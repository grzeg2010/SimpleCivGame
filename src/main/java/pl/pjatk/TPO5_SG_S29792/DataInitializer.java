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

package pl.pjatk.TPO5_SG_S29792;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;
import pl.pjatk.TPO5_SG_S29792.models.terrain.*;
import pl.pjatk.TPO5_SG_S29792.repositories.TerrainRepository;
import pl.pjatk.TPO5_SG_S29792.services.UnitService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final TerrainRepository terrainRepository;
    private final UnitService unitService;

    @Override
    public void run(ApplicationArguments args) {
        if (terrainRepository.count() > 0) return;

        List<Terrain> tiles = new ArrayList<>();
        String[][] map = {
            {"LAND","LAND","SEA", "SEA", "SEA", "LAND"},
            {"SEA", "SEA", "SEA", "SEA", "LAND","LAND"},
            {"SEA", "SEA", "SEA", "LAND","LAND","LAND"},
            {"LAND","LAND","LAND","LAND","LAND","LAND"},
            {"MOUNTAIN","LAND","LAND","LAND","LAND","LAND"},
            {"MOUNTAIN","MOUNTAIN","MOUNTAIN","LAND","LAND","LAND"},
            {"LAND","MOUNTAIN","MOUNTAIN","MOUNTAIN","LAND","LAND"},
            {"LAND","LAND","LAND","LAND","LAND","LAND"},
        };

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                Terrain tile = switch (map[x][y]) {
                    case "SEA" -> new Sea();
                    case "MOUNTAIN" -> new Mountain();
                    default -> new Land();
                };
                tile.setId(new TerrainId(x, y));
                tiles.add(tile);
            }
        }

        terrainRepository.saveAll(tiles);
        unitService.createUnit(UnitType.SETTLER, 4, 3);
    }
}
