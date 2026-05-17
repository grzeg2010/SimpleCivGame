package pl.pjatk.TPO5_SG_S29792.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pjatk.TPO5_SG_S29792.dto.GetUnitResponse;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;
import pl.pjatk.TPO5_SG_S29792.models.units.*;
import pl.pjatk.TPO5_SG_S29792.repositories.UnitRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {
    private final UnitRepository unitRepository;

    private Unit findById(Long id) {
        return unitRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found"));
    }

    public List<GetUnitResponse> getAll(String sort) {
        List<Unit> units = unitRepository.findAll(Sort.by(sort));
        List<GetUnitResponse> unitsDtos = new ArrayList<>();

        units.forEach(unit -> {
            unitsDtos.add(new GetUnitResponse(
                    unit.getId(),
                    unit.getType(),
                    unit.getPosX(),
                    unit.getPosY(),
                    unit.getMovementRange(),
                    unit.canSwim(),
                    unit.canWalk()
            ));
        });

        return unitsDtos;
    }

    public GetUnitResponse createUnit(UnitType type, int posX, int posY) {
        Unit unit = switch (type) {
            case SCOUT -> new Scout();
            case SETTLER -> new Settler();
            case WARRIOR -> new Warrior();
            case WORKER -> new Worker();
        };

        unit.setPosX(posX);
        unit.setPosY(posY);

        unit = unitRepository.save(unit);

        return new GetUnitResponse(
                unit.getId(),
                unit.getType(),
                unit.getPosX(),
                unit.getPosY(),
                unit.getMovementRange(),
                unit.canSwim(),
                unit.canWalk()
        );
    }

    public void moveUnit(Long id, int targetX, int targetY) {
        Unit unit = findById(id);

        unit.setPosX(targetX);
        unit.setPosY(targetY);

        unitRepository.save(unit);
    }

    public void deleteUnit(Long id) {
        unitRepository.delete(findById(id));
    }
}
