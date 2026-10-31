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
