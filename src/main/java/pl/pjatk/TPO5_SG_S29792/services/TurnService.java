package pl.pjatk.TPO5_SG_S29792.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjatk.TPO5_SG_S29792.dto.*;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.enums.UnitType;
import pl.pjatk.TPO5_SG_S29792.models.tile_upgrades.TileUpgrade;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnService {
    private final TileUpgradeService tileUpgradeService;
    private final UnitService unitService;
    private final CityService cityService;

    public GetTurnResponse processTurn(PostTurnRequest req) {
        List<GetUnitResponse> movedUnits = new ArrayList<>();
        List<GetTileUpgradeResponse> builtUpgrades = new ArrayList<>();
        List<Long> deletedUnits = new ArrayList<>();
        List<Long> deletedUpgrades = new ArrayList<>();

        req.getOrders().forEach(order -> {
            switch(order.getAction()) {
                case MOVE -> movedUnits.add(unitService.moveUnit(
                        order.getUnitId(), order.getTargetX(), order.getTargetY()
                ));
                case BUILD_CITY -> {
                    GetTileUpgradeResponse tileUpgrade = callTileUpgradeBuild(TileUpgrades.CITY, order);
                    if (tileUpgrade != null) builtUpgrades.add(tileUpgrade);
                }
                case BUILD_FARM -> {
                    GetTileUpgradeResponse tileUpgrade = callTileUpgradeBuild(TileUpgrades.FARM, order);
                    if (tileUpgrade != null) builtUpgrades.add(tileUpgrade);
                }
                case BUILD_MILL -> {
                    GetTileUpgradeResponse tileUpgrade = callTileUpgradeBuild(TileUpgrades.MILL, order);
                    if (tileUpgrade != null) builtUpgrades.add(tileUpgrade);
                }
                case BUILD_FISH_BOAT -> {
                    GetTileUpgradeResponse tileUpgrade = callTileUpgradeBuild(TileUpgrades.FISH_BOAT, order);
                    if (tileUpgrade != null) builtUpgrades.add(tileUpgrade);
                }
                case REMOVE_UNIT -> {
                    unitService.deleteUnit(order.getUnitId());
                    deletedUnits.add(order.getUnitId());
                }
                case REMOVE_UPGRADE -> {
                    TileUpgrade targetTile = tileUpgradeService.findByPosition(
                            order.getTargetX(), order.getTargetY()
                    );
                    tileUpgradeService.deleteTileUpgrade(targetTile.getId());
                    deletedUpgrades.add(targetTile.getId());
                }
            }
        });

        List<GetTileUpgradeResponse> tileUpgrades = tileUpgradeService.getAll("id");
        tileUpgrades.forEach(upgrade -> {
            if(upgrade.getType() == TileUpgrades.CITY) {
                cityService.increaseGrowthPoints(upgrade.getId());
            }
        });

        return new GetTurnResponse(
                movedUnits,
                builtUpgrades,
                deletedUnits,
                deletedUpgrades
        );
    }

    private GetTileUpgradeResponse callTileUpgradeBuild(TileUpgrades tileUpgrade, UnitOrderRequest order) {
        if (
                (tileUpgrade == TileUpgrades.CITY && unitService.findById(order.getUnitId()).getType() == UnitType.SETTLER)
                        || (tileUpgrade != TileUpgrades.CITY && unitService.findById(order.getUnitId()).getType() == UnitType.WORKER)
        ) {
            return tileUpgradeService.createTileUpgrade(
                    tileUpgrade, order.getTargetX(), order.getTargetY()
            );
        } else return null;
    }
}
