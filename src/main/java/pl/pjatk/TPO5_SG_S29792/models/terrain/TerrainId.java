package pl.pjatk.TPO5_SG_S29792.models.terrain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerrainId implements Serializable {
    private int posX;
    private int posY;
}
