package pl.pjatk.TPO5_SG_S29792.models.units;

import jakarta.persistence.*;
import lombok.*;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "unit_type")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private int posX;
    private int posY;

    public boolean canSwim() { return false; }
    public boolean canWalk() { return false; }
    public int getMovementRange() { return 1; }
}
