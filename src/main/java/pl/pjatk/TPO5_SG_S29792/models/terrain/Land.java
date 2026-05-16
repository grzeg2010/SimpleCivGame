package pl.pjatk.TPO5_SG_S29792.models.terrain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.models.units.Unit;

@Entity
@DiscriminatorValue("LAND")
@Getter @Setter
@NoArgsConstructor
public class Land extends Terrain {
    @Override
    public boolean isPassableBy(Unit unit) { return unit.canWalk(); }
}
