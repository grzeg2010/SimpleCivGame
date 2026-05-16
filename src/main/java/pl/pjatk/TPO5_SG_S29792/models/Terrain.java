package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "terrain_type")
@Data
@NoArgsConstructor
public class Terrain {
    @EmbeddedId
    private TerrainId id;

    @OneToOne(mappedBy = "terrain")
    private LandUpgrade landUpgrade;

    public boolean isPassableBy(Unit unit) { return false; }
}
