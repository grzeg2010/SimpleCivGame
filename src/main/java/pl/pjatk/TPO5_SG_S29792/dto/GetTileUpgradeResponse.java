package pl.pjatk.TPO5_SG_S29792.dto;

import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;

public class GetTileUpgradeResponse {
    private int posX;
    private int posY;
    private TileUpgrades type;
    private int foodBonus;
    private int productionBonus;
}
