package pl.pjatk.TPO5_SG_S29792.models.terrain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.models.tile_upgrades.TileUpgrade;
import pl.pjatk.TPO5_SG_S29792.models.units.Unit;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "terrain_type")
@Getter @Setter
@NoArgsConstructor
public class Terrain {
    @EmbeddedId
    private TerrainId id;

    @OneToOne(mappedBy = "terrain")
    private TileUpgrade tileUpgrade;

    public boolean isPassableBy(Unit unit) { return false; }
}
