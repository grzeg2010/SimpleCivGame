package pl.pjatk.TPO5_SG_S29792.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;

@Data
@AllArgsConstructor
public class GetTileUpgradeResponse {
    private int posX;
    private int posY;
    private TileUpgrades type;
    private int foodBonus;
    private int productionBonus;
    private boolean isLandUpgrade;
    private boolean isWaterUpgrade;
}
