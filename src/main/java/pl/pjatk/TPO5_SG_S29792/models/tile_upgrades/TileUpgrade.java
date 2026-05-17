package pl.pjatk.TPO5_SG_S29792.models.tile_upgrades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.models.terrain.Terrain;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "upgrade_type")
@Getter @Setter
@NoArgsConstructor
public class TileUpgrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int foodBonus;
    private int productionBonus;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "terrain_pos_x", referencedColumnName = "posX"),
            @JoinColumn(name = "terrain_pos_y", referencedColumnName = "posY")
    })
    private Terrain terrain;

    public boolean isLandUpgrade() {return false;}
    public boolean isWaterUpgrade() {return false;}
    public int getFoodCost() {return 5;}
    public int getProductionCost() {return 5;}
    public TileUpgrades getType() { return null; }
}

