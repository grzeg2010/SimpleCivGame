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
