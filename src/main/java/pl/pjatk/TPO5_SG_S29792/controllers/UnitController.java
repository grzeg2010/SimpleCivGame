/*
 * SimpleCivGame: Turn-based strategy game inspired by Sid Meier's Civilization V, built with Spring Boot.
 * Copyright (C) 2026 Grzegorz Śnieżko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
            @RequestParam(defaultValue = "id") String sort
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
