package pl.pjatk.TPO5_SG_S29792.models.terrain_upgrades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.models.terrain.Terrain;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "upgrade_type")
@Getter @Setter
@NoArgsConstructor
public class TerrainUpgrade {
    @EmbeddedId
    private TerrainId id;

    private int foodBonus;
    private int productionBonus;

    @OneToOne
    @MapsId
    private Terrain terrain;

    public boolean isLandUpgrade() {return false;}
    public boolean isWaterUpgrade() {return false;}
    public int getFoodCost() {return 5;}
    public int getProductionCost() {return 5;}
}

