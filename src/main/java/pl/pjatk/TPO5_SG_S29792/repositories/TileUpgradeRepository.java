package pl.pjatk.TPO5_SG_S29792.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.TPO5_SG_S29792.models.tile_upgrades.TileUpgrade;

import java.util.Optional;

@Repository
public interface TileUpgradeRepository extends JpaRepository<TileUpgrade, Long> {
    Optional<TileUpgrade> findByTerrainIdPosXAndTerrainIdPosY(int posX, int posY);
}
