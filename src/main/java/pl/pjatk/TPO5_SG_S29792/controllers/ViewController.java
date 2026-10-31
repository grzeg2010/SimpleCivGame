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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;
import pl.pjatk.TPO5_SG_S29792.services.TerrainService;
import pl.pjatk.TPO5_SG_S29792.services.TileUpgradeService;
import pl.pjatk.TPO5_SG_S29792.services.UnitService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cheat-console")
public class ViewController {

    private final TerrainService terrainService;
    private final UnitService unitService;
    private final TileUpgradeService tileUpgradeService;

    @GetMapping
    public String console(Model model) {
        model.addAttribute("tiles", terrainService.getAll("id.posX").getTiles());
        model.addAttribute("units", unitService.getAll("posX"));
        model.addAttribute("upgrades", tileUpgradeService.getAll("terrain.id.posX"));
        model.addAttribute("unitTypes", UnitType.values());
        model.addAttribute("upgradeTypes", TileUpgrades.values());
        return "console";
    }

    @PostMapping("/unit")
    public String createUnit(@RequestParam UnitType type,
                             @RequestParam int posX,
                             @RequestParam int posY) {
        unitService.createUnit(type, posX, posY);
        return "redirect:/cheat-console";
    }

    @PutMapping("/unit/{id}")
    public String moveUnit(@PathVariable Long id,
                           @RequestParam int posX,
                           @RequestParam int posY) {
        unitService.moveUnit(id, posX, posY);
        return "redirect:/cheat-console";
    }

    @DeleteMapping("/unit/{id}")
    public String deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return "redirect:/cheat-console";
    }

    @PostMapping("/tile-upgrade")
    public String createTileUpgrade(@RequestParam TileUpgrades type,
                                    @RequestParam int posX,
                                    @RequestParam int posY) {
        tileUpgradeService.createTileUpgrade(type, posX, posY);
        return "redirect:/cheat-console";
    }

    @DeleteMapping("/tile-upgrade/{id}")
    public String deleteTileUpgrade(@PathVariable Long id) {
        tileUpgradeService.deleteTileUpgrade(id);
        return "redirect:/cheat-console";
    }
}
