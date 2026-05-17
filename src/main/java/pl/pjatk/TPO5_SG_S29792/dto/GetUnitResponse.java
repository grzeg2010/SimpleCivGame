package pl.pjatk.TPO5_SG_S29792.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;

@Data
@AllArgsConstructor
public class GetUnitResponse {
    private Long id;
    private UnitType type;
    private int posX;
    private int posY;
    private int movementRange;
    private boolean canSwim;
    private boolean canWalk;
}
