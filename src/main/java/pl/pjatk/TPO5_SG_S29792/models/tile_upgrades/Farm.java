package pl.pjatk.TPO5_SG_S29792.models.tile_upgrades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("FARM")
@Getter @Setter
@NoArgsConstructor
public class Farm extends TileUpgrade {
    @Override
    public boolean isLandUpgrade() { return true; }

    @Override
    public int getFoodCost() { return 1; }

    @Override
    public int getProductionCost() { return 2; }
}
