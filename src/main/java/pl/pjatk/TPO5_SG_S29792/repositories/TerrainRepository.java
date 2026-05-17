package pl.pjatk.TPO5_SG_S29792.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.TPO5_SG_S29792.models.terrain.Terrain;
import pl.pjatk.TPO5_SG_S29792.models.terrain.TerrainId;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, TerrainId> {}
