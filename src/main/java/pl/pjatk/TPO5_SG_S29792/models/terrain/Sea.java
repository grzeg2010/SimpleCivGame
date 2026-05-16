package pl.pjatk.TPO5_SG_S29792.models.terrain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.models.units.Unit;

@Entity
@DiscriminatorValue("SEA")
@Getter @Setter
@NoArgsConstructor
public class Sea extends Terrain {
    public boolean isPassableBy(Unit unit) { return unit.canSwim(); }
}
