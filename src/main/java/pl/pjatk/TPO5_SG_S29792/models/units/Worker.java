package pl.pjatk.TPO5_SG_S29792.models.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;

@Entity
@DiscriminatorValue("WORKER")
@Getter @Setter
@NoArgsConstructor
public class Worker extends Unit {
    @Override
    public boolean canSwim() { return true; }

    @Override
    public boolean canWalk() { return true; }

    @Override
    public int getMovementRange() { return 2; }

    @Override
    public UnitType getType() {
        return UnitType.WORKER;
    }
}
