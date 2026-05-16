package pl.pjatk.TPO5_SG_S29792.models.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("WARRIOR")
@Getter @Setter
@NoArgsConstructor
public class Warrior extends Unit {
    private int strength;

    @Override
    public boolean canWalk() { return true; }
}
