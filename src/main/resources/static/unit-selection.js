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

function clearHighlights() {
    document.querySelectorAll('.play-tile.reachable').forEach(t => t.classList.remove('reachable'));
    moveMode = false;
}

function selectUnit(unitElement, unit) {
    if (selectedUnit) selectedUnit.classList.remove("selected");
    clearHighlights();
    deselectCity();
    selectedUnit = unitElement;
    selectedUnitData = unit;
    unitElement.classList.add("selected");
    unitName.textContent = unit.type;
    document.getElementById("btn-move").hidden = false;
    document.getElementById("btn-delete").hidden = false;
    document.getElementById("btn-found-city").hidden = unit.type !== "SETTLER";
    const isWorker = unit.type === "WORKER";
    document.getElementById("btn-build-farm").hidden = !isWorker;
    document.getElementById("btn-build-mill").hidden = !isWorker;
    document.getElementById("btn-build-boat").hidden = !isWorker;
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
    if (!tile) return;

    if (tile.classList.contains('upgrade-city') && !tile.classList.contains('reachable')) {
        selectCity(tile);
        return;
    }

    if (!tile.classList.contains('reachable')) return;

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
