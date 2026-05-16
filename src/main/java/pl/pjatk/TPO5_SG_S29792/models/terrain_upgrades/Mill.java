package pl.pjatk.TPO5_SG_S29792.models.terrain_upgrades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("MILL")
@Getter @Setter
@NoArgsConstructor
public class Mill extends TerrainUpgrade {
    @Override
    public boolean isLandUpgrade() { return true; }

    @Override
    public int getFoodCost() { return 2; }

    @Override
    public int getProductionCost() { return 1; }
}
