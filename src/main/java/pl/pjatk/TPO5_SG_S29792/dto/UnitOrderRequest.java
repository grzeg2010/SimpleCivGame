package pl.pjatk.TPO5_SG_S29792.dto;

import lombok.Data;
import pl.pjatk.TPO5_SG_S29792.enums.UnitAction;

@Data
public class UnitOrderRequest {
    private Long unitId;
    private UnitAction action;
    private int targetX;
    private int targetY;
}
