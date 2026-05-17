package pl.pjatk.TPO5_SG_S29792.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pjatk.TPO5_SG_S29792.enums.TerrainType;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;

@Data
@AllArgsConstructor
public class GetTileResponse {
    private int posX;
    private int posY;
    private TerrainType terrainType;
    @Nullable private TileUpgrades upgradeType;
}
