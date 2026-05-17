package pl.pjatk.TPO5_SG_S29792.dto;

import lombok.Data;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;

@Data
public class PostUnitRequest {
    private UnitType type;
    private int posX;
    private int posY;
}
