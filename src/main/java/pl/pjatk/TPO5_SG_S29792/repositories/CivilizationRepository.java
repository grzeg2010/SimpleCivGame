package pl.pjatk.TPO5_SG_S29792.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.TPO5_SG_S29792.models.Civilization;

@Repository
public interface CivilizationRepository extends JpaRepository<Civilization, Long> {}
