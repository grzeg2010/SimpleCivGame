package pl.pjatk.TPO5_SG_S29792.models.terrain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pjatk.TPO5_SG_S29792.models.units.Unit;

@Entity
@DiscriminatorValue("SEA")
@Data
@NoArgsConstructor
public class Sea extends Terrain {
    public boolean isPassableBy(Unit unit) { return unit.canSwim(); }
}
