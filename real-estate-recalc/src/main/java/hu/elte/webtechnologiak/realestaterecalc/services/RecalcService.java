package hu.elte.webtechnologiak.realestaterecalc.services;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Recalc;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.RecalcRepository;
import hu.elte.webtechnologiak.realestaterecalc.services.exceptions.RecalcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class RecalcService {
    private RecalcRepository recalcRepository;

    @Autowired
    public RecalcService(RecalcRepository recalcRepository) {
        this.recalcRepository = recalcRepository;
    }

    public Iterable<Recalc> findAll() {
        return recalcRepository.findAll();
    }


    public Recalc findById(long id) throws RecalcException {
        return recalcRepository.findById(id)
                .orElseThrow(() -> new RecalcException("Recalc by id " + id + " not found!"));
    }

    public Recalc addRecalc(Recalc recalc) {
        return recalcRepository.save(recalc);
    }

    public Recalc updateRecalc(Recalc recalc) throws RecalcException {
        //Recalc current = findById(recalc.getId());

        return recalcRepository.save(recalc);
    }

    public Recalc deleteRecalc(long id) throws RecalcException {
        Recalc current = findById(id);
        return recalcRepository.save(current);
    }
}
