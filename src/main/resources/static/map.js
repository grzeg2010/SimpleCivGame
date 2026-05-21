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
