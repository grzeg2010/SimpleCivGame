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

document.getElementById("btn-found-city").addEventListener("click", () => {
    if (!selectedUnitData || selectedUnitData.type !== "SETTLER") return;

    const { id, posX, posY } = selectedUnitData;

    fetch('/data/tile-upgrade', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ posX, posY, type: 'CITY' })
    })
    .then(res => {
        if (!res.ok) return;
        return fetch(`/data/unit/${id}`, { method: 'DELETE' });
    })
    .then(res => {
        if (!res || !res.ok) return;

        document.querySelector(`.unit[data-id="${id}"]`)?.remove();
        document.querySelector(`.play-tile.x${posX}.y${posY}`)?.classList.add('upgrade-city');
        unitDataMap.delete(id);
        pendingOrders.delete(id);
        clearHighlights();
        selectedUnit = null;
        selectedUnitData = null;
        unitName.textContent = "Unit name";
        document.getElementById("btn-found-city").hidden = true;
    });
});
