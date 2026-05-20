package pl.pjatk.TPO5_SG_S29792.models.tile_upgrades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.models.Civilization;

@Entity
@DiscriminatorValue("CITY")
@Getter @Setter
@NoArgsConstructor
public class City extends TileUpgrade {
    private int growthPoints;
    private int size;

    @ManyToOne
    private Civilization civilization;

    @Override
    public boolean isLandUpgrade() {
        return true;
    }

    @Override
    public int getFoodCost() {
        return 3;
    }

    @Override
    public int getProductionCost() {
        return 3;
    }

    @Override
    public TileUpgrades getType() { return TileUpgrades.CITY; }
}
