package pl.pjatk.TPO5_SG_S29792.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.TPO5_SG_S29792.models.units.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer>{}
