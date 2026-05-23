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
