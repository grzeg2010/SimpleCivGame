function buildUpgrade(type, cssClass) {
    if (!selectedUnitData || selectedUnitData.type !== "WORKER") return;

    const { posX, posY } = selectedUnitData;

    fetch('/data/tile-upgrade', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ posX, posY, type })
    })
    .then(res => {
        if (!res.ok) return;
        document.querySelector(`.play-tile.x${posX}.y${posY}`)?.classList.add(cssClass);
    });
}

document.getElementById("btn-build-farm").addEventListener("click", () => buildUpgrade("FARM", "upgrade-farm"));
document.getElementById("btn-build-mill").addEventListener("click", () => buildUpgrade("MILL", "upgrade-mill"));
document.getElementById("btn-build-boat").addEventListener("click", () => buildUpgrade("FISH_BOAT", "upgrade-fish-boat"));
