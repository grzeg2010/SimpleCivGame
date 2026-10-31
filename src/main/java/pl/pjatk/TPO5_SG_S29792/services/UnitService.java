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

package pl.pjatk.TPO5_SG_S29792.services;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
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

    public Unit findById(Long id) {
        return unitRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found"));
    }

    public List<GetUnitResponse> getAll(String sort) {
        List<Unit> units = unitRepository.findAll(Sort.by(sort));
        List<GetUnitResponse> unitsDtos = new ArrayList<>();

        units.forEach(unit -> {
            unitsDtos.add(mapGetUnitResponse(unit));
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

        return mapGetUnitResponse(unit);
    }

    public GetUnitResponse moveUnit(Long id, int targetX, int targetY) {
        Unit unit = findById(id);

        unit.setPosX(targetX);
        unit.setPosY(targetY);

        unitRepository.save(unit);

        return mapGetUnitResponse(unit);
    }

    public void deleteUnit(Long id) {
        unitRepository.delete(findById(id));
    }

    private static @NonNull GetUnitResponse mapGetUnitResponse(Unit unit) {
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
}
