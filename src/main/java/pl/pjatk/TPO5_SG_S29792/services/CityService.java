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
