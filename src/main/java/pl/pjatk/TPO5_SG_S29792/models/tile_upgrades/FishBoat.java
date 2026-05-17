package pl.pjatk.TPO5_SG_S29792.models.tile_upgrades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;

@Entity
@DiscriminatorValue("FISH_BOAT")
@Getter @Setter
@NoArgsConstructor
public class FishBoat extends TileUpgrade {
    @Override
    public boolean isWaterUpgrade() { return true; }

    @Override
    public int getFoodCost() { return 1; }

    @Override
    public int getProductionCost() { return 3; }

    @Override
    public TileUpgrades getType() { return TileUpgrades.FISH_BOAT; }
}
