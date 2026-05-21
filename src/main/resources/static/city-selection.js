function selectCity(tile) {
    if (selectedUnit) {
        selectedUnit.classList.remove("selected");
        selectedUnit = null;
        selectedUnitData = null;
    }
    clearHighlights();

    const posX = parseInt([...tile.classList].find(c => /^x\d+$/.test(c)).slice(1));
    const posY = parseInt([...tile.classList].find(c => /^y\d+$/.test(c)).slice(1));
    selectedCity = { posX, posY };

    unitName.textContent = "CITY";
    unitName.style.backgroundColor = "seagreen";

    document.getElementById("btn-move").hidden = true;
    document.getElementById("btn-delete").hidden = true;
    document.getElementById("btn-found-city").hidden = true;
    document.getElementById("btn-build-farm").hidden = true;
    document.getElementById("btn-build-mill").hidden = true;
    document.getElementById("btn-build-boat").hidden = true;

    document.getElementById("btn-add-worker").hidden = false;
    document.getElementById("btn-add-warrior").hidden = false;
    document.getElementById("btn-add-settler").hidden = false;
    document.getElementById("btn-add-scout").hidden = false;
}

function deselectCity() {
    if (!selectedCity) return;
    selectedCity = null;
    unitName.style.backgroundColor = "";
    document.getElementById("btn-add-worker").hidden = true;
    document.getElementById("btn-add-warrior").hidden = true;
    document.getElementById("btn-add-settler").hidden = true;
    document.getElementById("btn-add-scout").hidden = true;
}

function spawnUnit(type) {
    if (!selectedCity) return;
    const { posX, posY } = selectedCity;

    fetch('/data/unit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ type, posX, posY })
    })
    .then(res => res.json())
    .then(unit => {
        unitDataMap.set(unit.id, unit);

        const tile = document.querySelector(`.play-tile.x${unit.posX}.y${unit.posY}`);
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
}

document.getElementById("btn-add-worker").addEventListener("click",  () => spawnUnit("WORKER"));
document.getElementById("btn-add-warrior").addEventListener("click", () => spawnUnit("WARRIOR"));
document.getElementById("btn-add-settler").addEventListener("click", () => spawnUnit("SETTLER"));
document.getElementById("btn-add-scout").addEventListener("click",   () => spawnUnit("SCOUT"));
