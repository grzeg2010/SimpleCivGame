package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("MOUNTAIN")
@Data
@NoArgsConstructor
public class Mountain extends Terrain {

}
