package pl.pjatk.TPO5_SG_S29792.models.tile_upgrades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;

@Entity
@DiscriminatorValue("MILL")
@Getter @Setter
@NoArgsConstructor
public class Mill extends TileUpgrade {
    @Override
    public boolean isLandUpgrade() { return true; }

    @Override
    public int getFoodCost() { return 2; }

    @Override
    public int getProductionCost() { return 1; }

    @Override
    public TileUpgrades getType() { return TileUpgrades.MILL; }
}
