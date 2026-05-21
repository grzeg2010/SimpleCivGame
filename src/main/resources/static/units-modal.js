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
