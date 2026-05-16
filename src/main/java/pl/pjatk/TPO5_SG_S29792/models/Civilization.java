package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Civilization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int foodPoints;
    private int productionPoints;
}
