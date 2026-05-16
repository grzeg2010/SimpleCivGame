package pl.pjatk.TPO5_SG_S29792.dto;

import pl.pjatk.TPO5_SG_S29792.enums.UnitType;

public class GetUnitResponse {
    private Long id;
    private UnitType type;
    private int posX;
    private int posY;
    private int movementRange;
    private boolean canSwim;
    private boolean canWalk;
}
