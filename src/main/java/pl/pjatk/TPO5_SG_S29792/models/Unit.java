package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "unit_type")
@Data
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int posX;
    private int posY;

    public boolean canSwim() { return false; }
    public boolean canWalk() { return false; }
    public int getMovementRange() { return 1; }
}
