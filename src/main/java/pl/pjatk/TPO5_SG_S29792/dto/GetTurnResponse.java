package pl.pjatk.TPO5_SG_S29792.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetTurnResponse {
    private List<GetUnitResponse> units;
    private List<GetTileUpgradeResponse> terrainUpgrades;
    private List<Long> deletedUnits;
    private List<Long> deletedUpgrades;
}
