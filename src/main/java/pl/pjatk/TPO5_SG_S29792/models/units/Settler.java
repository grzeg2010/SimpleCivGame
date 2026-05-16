package pl.pjatk.TPO5_SG_S29792.models.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SETTLER")
@Data
@NoArgsConstructor
public class Settler extends Unit {
    @Override
    public boolean canWalk() { return true; }
}
