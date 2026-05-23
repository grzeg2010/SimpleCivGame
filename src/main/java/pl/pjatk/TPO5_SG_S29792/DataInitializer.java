package pl.pjatk.TPO5_SG_S29792;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;
import pl.pjatk.TPO5_SG_S29792.models.terrain.*;
import pl.pjatk.TPO5_SG_S29792.repositories.TerrainRepository;
import pl.pjatk.TPO5_SG_S29792.services.UnitService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final TerrainRepository terrainRepository;
    private final UnitService unitService;

    @Override
    public void run(ApplicationArguments args) {
        if (terrainRepository.count() > 0) return;

        List<Terrain> tiles = new ArrayList<>();
        String[][] map = {
            {"LAND","LAND","SEA", "SEA", "SEA", "LAND"},
            {"SEA", "SEA", "SEA", "SEA", "LAND","LAND"},
            {"SEA", "SEA", "SEA", "LAND","LAND","LAND"},
            {"LAND","LAND","LAND","LAND","LAND","LAND"},
            {"MOUNTAIN","LAND","LAND","LAND","LAND","LAND"},
            {"MOUNTAIN","MOUNTAIN","MOUNTAIN","LAND","LAND","LAND"},
            {"LAND","MOUNTAIN","MOUNTAIN","MOUNTAIN","LAND","LAND"},
            {"LAND","LAND","LAND","LAND","LAND","LAND"},
        };

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                Terrain tile = switch (map[x][y]) {
                    case "SEA" -> new Sea();
                    case "MOUNTAIN" -> new Mountain();
                    default -> new Land();
                };
                tile.setId(new TerrainId(x, y));
                tiles.add(tile);
            }
        }

        terrainRepository.saveAll(tiles);
        unitService.createUnit(UnitType.SETTLER, 4, 3);
    }
}
