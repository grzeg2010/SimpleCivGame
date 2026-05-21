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
