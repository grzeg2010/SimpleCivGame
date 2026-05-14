package pl.pjatk.TPO5_SG_S29792.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pjatk.TPO5_SG_S29792.models.Unit;
import pl.pjatk.TPO5_SG_S29792.repositories.UnitRepository;

import java.util.List;

@Service
public class UnitService {
    private final UnitRepository repo;

    public UnitService(UnitRepository repo) {
        this.repo = repo;
    }

    public List<Unit> getAll(String sort) {
        return repo.findAll(Sort.by(sort));
    }
}
