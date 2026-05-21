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
