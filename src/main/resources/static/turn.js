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
