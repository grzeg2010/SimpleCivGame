package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("WARRIOR")
@Data
@NoArgsConstructor
public class Warrior extends Unit {
    private int strength;

    @Override
    public boolean canWalk() { return true; }
}
