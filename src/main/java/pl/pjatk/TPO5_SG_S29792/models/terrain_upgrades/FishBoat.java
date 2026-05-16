package pl.pjatk.TPO5_SG_S29792.models.terrain_upgrades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("FISH_BOAT")
@Getter @Setter
@NoArgsConstructor
public class FishBoat extends TerrainUpgrade {
    @Override
    public boolean isWaterUpgrade() { return true; }

    @Override
    public int getFoodCost() { return 1; }

    @Override
    public int getProductionCost() { return 3; }
}
