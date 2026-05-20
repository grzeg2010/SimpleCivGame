const playGrid = document.getElementById("play-grid");
const unitName = document.getElementById("unit-name");

let selectedUnit = null;
let selectedUnitData = null;
let moveMode = false;
const pendingOrders = new Map(); // unitId -> order
const unitDataMap = new Map();   // unitId -> current unit data

function clearHighlights() {
    document.querySelectorAll('.play-tile.reachable').forEach(t => t.classList.remove('reachable'));
    moveMode = false;
}

function selectUnit(unitElement, unit) {
    if (selectedUnit) selectedUnit.classList.remove("selected");
    clearHighlights();
    selectedUnit = unitElement;
    selectedUnitData = unit;
    unitElement.classList.add("selected");
    unitName.textContent = unit.type;
}

function highlightReachable() {
    if (!selectedUnitData) return;
    if (moveMode) { clearHighlights(); return; }

    moveMode = true;
    const unit = selectedUnitData;
    const visited = new Set();
    const queue = [{ x: unit.posX, y: unit.posY, steps: 0 }];
    visited.add(`${unit.posX},${unit.posY}`);

    while (queue.length > 0) {
        const { x, y, steps } = queue.shift();

        if (steps > 0) {
            document.querySelector(`.play-tile.x${x}.y${y}`)?.classList.add('reachable');
        }

        if (steps >= unit.movementRange) continue;

        for (const [dx, dy] of [[0,1],[0,-1],[1,0],[-1,0]]) {
            const nx = x + dx, ny = y + dy;
            const key = `${nx},${ny}`;
            if (visited.has(key)) continue;
            visited.add(key);

            const t = terrain[key];
            if (!t) continue;
            if (t.terrainType === 'MOUNTAIN') continue;
            if (t.terrainType === 'SEA' && !unit.canSwim) continue;
            if (t.terrainType === 'LAND' && !unit.canWalk) continue;

            queue.push({ x: nx, y: ny, steps: steps + 1 });
        }
    }
}

playGrid.addEventListener("click", (e) => {
    const tile = e.target.closest('.play-tile');
    if (!tile?.classList.contains('reachable')) return;

    const x = parseInt([...tile.classList].find(c => /^x\d+$/.test(c)).slice(1));
    const y = parseInt([...tile.classList].find(c => /^y\d+$/.test(c)).slice(1));

    const oldOrder = pendingOrders.get(selectedUnitData.id);
    if (oldOrder) {
        document.querySelector(`.play-tile.x${oldOrder.targetX}.y${oldOrder.targetY}`)?.classList.remove('target');
    }

    pendingOrders.set(selectedUnitData.id, { unitId: selectedUnitData.id, action: "MOVE", targetX: x, targetY: y });
    clearHighlights();
    tile.classList.add('target');
});

document.getElementById("btn-move").addEventListener("click", highlightReachable);

document.getElementById("btn-delete").addEventListener("click", () => {
    if (!selectedUnitData) return;

    fetch(`/data/unit/${selectedUnitData.id}`, { method: 'DELETE' })
        .then(res => {
            if (!res.ok) return;
            document.querySelector(`.unit[data-id="${selectedUnitData.id}"]`)?.remove();
            unitDataMap.delete(selectedUnitData.id);
            pendingOrders.delete(selectedUnitData.id);
            clearHighlights();
            document.querySelectorAll('.play-tile.target').forEach(t => t.classList.remove('target'));
            selectedUnit = null;
            selectedUnitData = null;
            unitName.textContent = "Unit name";
        });
});

document.getElementById("btn-next-turn").addEventListener("click", () => {
    fetch('/turn', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ orders: Array.from(pendingOrders.values()) })
    })
    .then(res => res.json())
    .then(response => {
        document.querySelectorAll('.play-tile.target').forEach(t => t.classList.remove('target'));
        pendingOrders.clear();

        response.deletedUnits?.forEach(id => {
            document.querySelector(`.unit[data-id="${id}"]`)?.remove();
            unitDataMap.delete(id);
            if (selectedUnitData?.id === id) {
                selectedUnit = null;
                selectedUnitData = null;
                unitName.textContent = "Unit name";
            }
        });

        response.units?.forEach(unit => {
            const unitEl = document.querySelector(`.unit[data-id="${unit.id}"]`);
            if (!unitEl) return;

            document.querySelector(`.play-tile.x${unit.posX}.y${unit.posY}`)?.appendChild(unitEl);
            unitDataMap.set(unit.id, unit);

            if (selectedUnitData?.id === unit.id) selectedUnitData = unit;
        });
    });
});

const terrain = {};

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

                switch (terrain[`${x},${y}`]?.terrainType) {
                    case "LAND":     tile.classList.add("land");     break;
                    case "MOUNTAIN": tile.classList.add("mountain"); break;
                    case "SEA":      tile.classList.add("sea");      break;
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
