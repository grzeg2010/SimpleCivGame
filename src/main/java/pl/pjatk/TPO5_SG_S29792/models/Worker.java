package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("WORKER")
@Data
@NoArgsConstructor
public class Worker extends Unit {
    @Override
    public boolean canSwim() { return true; }

    @Override
    public boolean canWalk() { return true; }

    @Override
    public int getMovementRange() { return 2; }
}
