package pl.pjatk.TPO5_SG_S29792.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostTurnRequest {
    private List<UnitOrderRequest> orders;
}
