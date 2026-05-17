package pl.pjatk.TPO5_SG_S29792.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.TPO5_SG_S29792.dto.GetTerrainResponse;
import pl.pjatk.TPO5_SG_S29792.dto.GetTileUpgradeResponse;
import pl.pjatk.TPO5_SG_S29792.services.TerrainService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class TerrainController {
    private final TerrainService terrainService;

    @GetMapping("/terrain")
    public ResponseEntity<GetTerrainResponse> getTerrain(
            @RequestParam(defaultValue = "id.posX") String sort
    ) {
        return ResponseEntity.ok(terrainService.getAll(sort));
    }
}
