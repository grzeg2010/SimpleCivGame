package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("LAND")
@Data
@NoArgsConstructor
public class Land extends Terrain {
    @Override
    public boolean isPassableBy(Unit unit) { return unit.canWalk(); }
}
