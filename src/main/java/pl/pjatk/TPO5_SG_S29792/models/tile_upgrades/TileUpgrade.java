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

package pl.pjatk.TPO5_SG_S29792.models.tile_upgrades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pjatk.TPO5_SG_S29792.enums.TileUpgrades;
import pl.pjatk.TPO5_SG_S29792.models.terrain.Terrain;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "upgrade_type")
@Getter @Setter
@NoArgsConstructor
public class TileUpgrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int foodBonus;
    private int productionBonus;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "terrain_pos_x", referencedColumnName = "posX"),
            @JoinColumn(name = "terrain_pos_y", referencedColumnName = "posY")
    })
    private Terrain terrain;

    public boolean isLandUpgrade() {return false;}
    public boolean isWaterUpgrade() {return false;}
    public int getFoodCost() {return 5;}
    public int getProductionCost() {return 5;}
    public TileUpgrades getType() { return null; }
}

