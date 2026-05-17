package pl.pjatk.TPO5_SG_S29792.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.TPO5_SG_S29792.dto.GetUnitResponse;
import pl.pjatk.TPO5_SG_S29792.dto.PostUnitRequest;
import pl.pjatk.TPO5_SG_S29792.dto.UnitOrderRequest;
import pl.pjatk.TPO5_SG_S29792.models.units.Unit;
import pl.pjatk.TPO5_SG_S29792.services.UnitService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class UnitController {
    private final UnitService unitService;

    @GetMapping("/unit")
    public List<GetUnitResponse> getUnits(
            @RequestParam(defaultValue = "name") String sort
    ) {
        return unitService.getAll(sort);
    }

    @PostMapping("/unit")
    public ResponseEntity<GetUnitResponse> createUnit(@RequestBody PostUnitRequest requestDto) {
        GetUnitResponse created = unitService.createUnit(
                requestDto.getType(),
                requestDto.getPosX(),
                requestDto.getPosY()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<Void> moveUnit(@PathVariable Long id, @RequestBody UnitOrderRequest order) {
        unitService.moveUnit(id, order.getTargetX(), order.getTargetY());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }
}
