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

 fetch('/data/terrain')
    .then(res => res.json())
    .then(response => {
        response.tiles.forEach(tile => {
            terrain[`${tile.posX},${tile.posY}`] = tile;
        });

        for (let y = 0; y < 6; y++) {
            const tileRow = document.createElement('div');
            tileRow.classList.add("tile-row");

            for (let x = 0; x < 8; x++) {
                const tile = document.createElement('div');
                tile.classList.add("play-tile", `x${x}`, `y${y}`);

                const tileData = terrain[`${x},${y}`];
                switch (tileData?.terrainType) {
                    case "LAND":     tile.classList.add("land");     break;
                    case "MOUNTAIN": tile.classList.add("mountain"); break;
                    case "SEA":      tile.classList.add("sea");      break;
                }

                switch (tileData?.upgradeType) {
                    case "CITY":      tile.classList.add("upgrade-city");      break;
                    case "FARM":      tile.classList.add("upgrade-farm");      break;
                    case "MILL":      tile.classList.add("upgrade-mill");      break;
                    case "FISH_BOAT": tile.classList.add("upgrade-fish-boat"); break;
                }

                tileRow.appendChild(tile);
            }

            playGrid.appendChild(tileRow);
        }

        return fetch("/data/unit").then(res => res.json());
    })
    .then(units => {
        units.forEach(unit => {
            unitDataMap.set(unit.id, unit);

            const tile = document.querySelector(`.x${unit.posX}.y${unit.posY}`);
            if (!tile) return;

            const unitElement = document.createElement('div');
            unitElement.classList.add("unit", unit.type.toLowerCase());
            unitElement.dataset.id = unit.id;
            unitElement.addEventListener("click", (e) => {
                e.stopPropagation();
                selectUnit(unitElement, unitDataMap.get(unit.id));
            });

            tile.appendChild(unitElement);
        });
    });
