package pl.pjatk.TPO5_SG_S29792.models.units;

import jakarta.persistence.*;
import lombok.*;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "unit_type")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private int posX;
    private int posY;

    public boolean canSwim() { return false; }
    public boolean canWalk() { return false; }
    public int getMovementRange() { return 1; }
    public UnitType getType() { return null; }
}
