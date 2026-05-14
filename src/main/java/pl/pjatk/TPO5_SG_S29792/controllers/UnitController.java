package pl.pjatk.TPO5_SG_S29792.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.TPO5_SG_S29792.models.Unit;
import pl.pjatk.TPO5_SG_S29792.services.UnitService;

import java.util.List;

@RestController
@RequestMapping("/data")
public class UnitController {
    private final UnitService service;

    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Unit> getUnits(@RequestParam(defaultValue = "name") String sort) {
        return service.getAll(sort);
    }
}
