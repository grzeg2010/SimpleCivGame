package pl.pjatk.TPO5_SG_S29792.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pjatk.TPO5_SG_S29792.models.terrain.Terrain;

import java.util.List;

@Data
@AllArgsConstructor
public class GetTerrainResponse {
    private List<GetTileResponse> tiles;
}
