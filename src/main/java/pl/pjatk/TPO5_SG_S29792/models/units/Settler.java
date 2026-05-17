package pl.pjatk.TPO5_SG_S29792.models.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;

@Entity
@DiscriminatorValue("SETTLER")
@Getter @Setter
@NoArgsConstructor
public class Settler extends Unit {
    @Override
    public boolean canWalk() { return true; }

    @Override
    public UnitType getType() {
        return UnitType.SETTLER;
    }
}
