package pl.pjatk.TPO5_SG_S29792.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TerrainId implements Serializable {
    private int posX;
    private int posY;
}
