package pl.pjatk.TPO5_SG_S29792.models.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("SCOUT")
@Getter @Setter
@NoArgsConstructor
public class Scout extends Unit {
    @Override
    public boolean canSwim() { return true; }

    @Override
    public boolean canWalk() { return true; }

    @Override
    public int getMovementRange() { return 2; }
}
