package pl.pjatk.TPO5_SG_S29792.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.TPO5_SG_S29792.dto.GetTileUpgradeResponse;
import pl.pjatk.TPO5_SG_S29792.dto.PostTileUpgradeRequest;
import pl.pjatk.TPO5_SG_S29792.dto.PutCityRequest;
import pl.pjatk.TPO5_SG_S29792.services.TileUpgradeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class TileUpgradeController {
    private final TileUpgradeService tileUpgradeService;

    @GetMapping("/tile-upgrade")
    public ResponseEntity<List<GetTileUpgradeResponse>> getTileUpgrades(
            @RequestParam(defaultValue = "id") String sort
    ) {
        return ResponseEntity.ok(tileUpgradeService.getAll(sort));
    }

    @PostMapping("/tile-upgrade")
    public ResponseEntity<GetTileUpgradeResponse> createTileUpgrade(@RequestBody PostTileUpgradeRequest requestDto) {
        GetTileUpgradeResponse created = tileUpgradeService.createTileUpgrade(
                requestDto.getType(),
                requestDto.getPosX(),
                requestDto.getPosY()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/tile-upgrade/{id}")
    public ResponseEntity<Void> deleteTileUpgrade(@PathVariable Long id) {
        tileUpgradeService.deleteTileUpgrade(id);
        return ResponseEntity.noContent().build();
    }
}
