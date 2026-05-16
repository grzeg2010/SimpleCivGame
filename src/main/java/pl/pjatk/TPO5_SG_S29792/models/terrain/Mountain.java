package pl.pjatk.TPO5_SG_S29792.models.terrain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("MOUNTAIN")
@Getter @Setter
@NoArgsConstructor
public class Mountain extends Terrain {

}
