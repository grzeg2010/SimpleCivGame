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

 let unitsSortCol = 'id';

function loadUnitsTable(sort) {
    unitsSortCol = sort;
    fetch(`/data/unit?sort=${sort}`)
        .then(res => res.json())
        .then(units => {
            const tbody = document.getElementById('units-tbody');
            tbody.innerHTML = '';
            units.forEach(u => {
                const tr = document.createElement('tr');
                tr.innerHTML = `<td>${u.id}</td><td>${u.type}</td><td>${u.posX}</td><td>${u.posY}</td><td>${u.movementRange}</td><td>${u.canWalk}</td><td>${u.canSwim}</td>`;
                tbody.appendChild(tr);
            });

            document.querySelectorAll('#units-table th').forEach(th => {
                const base = th.dataset.col === 'canWalk' ? 'Walk' :
                             th.dataset.col === 'canSwim' ? 'Swim' :
                             th.dataset.col === 'movementRange' ? 'Move' :
                             th.dataset.col.charAt(0).toUpperCase() + th.dataset.col.slice(1);
                th.textContent = base + (th.dataset.col === unitsSortCol ? ' ▲' : '');
            });
        });
}

document.getElementById('btn-units').addEventListener('click', () => {
    loadUnitsTable(unitsSortCol);
    document.getElementById('units-modal').hidden = false;
});

document.getElementById('btn-close-units').addEventListener('click', () => {
    document.getElementById('units-modal').hidden = true;
});

document.querySelectorAll('#units-table th').forEach(th => {
    th.addEventListener('click', () => loadUnitsTable(th.dataset.col));
});
