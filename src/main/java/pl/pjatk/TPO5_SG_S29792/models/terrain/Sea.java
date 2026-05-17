package pl.pjatk.TPO5_SG_S29792.models.terrain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.enums.TerrainType;
import pl.pjatk.TPO5_SG_S29792.models.units.Unit;

@Entity
@DiscriminatorValue("SEA")
@Getter @Setter
@NoArgsConstructor
public class Sea extends Terrain {
    @Override
    public boolean isPassableBy(Unit unit) { return unit.canSwim(); }

    @Override
    public TerrainType getType() { return TerrainType.SEA; }
}
