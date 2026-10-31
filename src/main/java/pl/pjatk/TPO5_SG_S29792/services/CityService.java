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
import org.springframework.stereotype.Service;
import pl.pjatk.TPO5_SG_S29792.models.Civilization;
import pl.pjatk.TPO5_SG_S29792.models.tile_upgrades.City;
import pl.pjatk.TPO5_SG_S29792.models.tile_upgrades.TileUpgrade;
import pl.pjatk.TPO5_SG_S29792.repositories.CivilizationRepository;
import pl.pjatk.TPO5_SG_S29792.repositories.TileUpgradeRepository;

@Service
@RequiredArgsConstructor
public class CityService {
    private final TileUpgradeRepository tileUpgradeRepository;
    private final CivilizationRepository civilizationRepository;

    public TileUpgrade findTileById(Long id) {
        return tileUpgradeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tile upgrade not found"));
    }

    public void increaseGrowthPoints(Long id) {
        City city = (City) findTileById(id);
        if (city.getCivilization() == null) return;
        if (city.getCivilization().getFoodPoints() > 0) {
            city.setGrowthPoints(city.getGrowthPoints() + 1);

            if (city.getGrowthPoints() == 6) {
                increaseSize(city);
                city.setGrowthPoints(0);
            }
        }

        tileUpgradeRepository.save(city);
        civilizationRepository.save(city.getCivilization());
    }

    private void increaseSize(City city) {
        Civilization civilization = city.getCivilization();
        city.setSize(city.getSize() + 1);

        civilization.setFoodPoints(
                civilization.getFoodPoints() - 1
        );

        city.setProductionBonus(
                city.getProductionBonus() + 1
        );

        civilization.setProductionPoints(
                civilization.getProductionPoints() + 1
        );
    }

    public void deleteCity(Long id) {
        City city = (City) findTileById(id);
        Civilization civilization = city.getCivilization();
        if (civilization == null) return;
        civilization.setFoodPoints(
                civilization.getFoodPoints() + city.getFoodCost() + city.getSize()
        );

        civilization.setProductionPoints(
                civilization.getProductionPoints() + city.getProductionCost() - city.getProductionBonus()
        );

        civilizationRepository.save(civilization);
    }
}
