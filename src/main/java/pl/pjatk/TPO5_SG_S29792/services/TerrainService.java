package pl.pjatk.TPO5_SG_S29792.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjatk.TPO5_SG_S29792.models.terrain.Terrain;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;
import pl.pjatk.TPO5_SG_S29792.repositories.TerrainRepository;

@Service
@RequiredArgsConstructor
public class TerrainService {
    private final TerrainRepository terrainRepository;

    public Terrain getByPosition(int posX, int posY) {
        return terrainRepository
                .findById(new TerrainId(posX, posY))
                .orElseThrow(() -> new IllegalArgumentException("Terrain not found"));
    }
}
