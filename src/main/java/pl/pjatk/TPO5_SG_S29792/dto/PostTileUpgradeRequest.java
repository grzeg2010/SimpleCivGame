package pl.pjatk.TPO5_SG_S29792.dto;

import lombok.Data;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;

@Data
public class PostTileUpgradeRequest {
    private int posX;
    private int posY;
    private TileUpgrades type;
}
