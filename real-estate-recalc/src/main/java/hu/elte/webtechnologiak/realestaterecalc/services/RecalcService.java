package hu.elte.webtechnologiak.realestaterecalc.services;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.AppraisalRepository;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.RealEstateRepository;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcTotalAppraisedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class RecalcService {

	private static final List<Algorithm> recalcAlgs = new ArrayList<>();

	static {
		recalcAlgs.add(new CalcTotalAppraisedValue());
	}

	private final AppraisalRepository appraisalRepository;

	private final RealEstateRepository realEstateRepository;

	@Autowired
	public RecalcService( AppraisalRepository appraisalRepository, RealEstateRepository realEstateRepository ) {
		this.appraisalRepository = appraisalRepository;
		this.realEstateRepository = realEstateRepository;
	}

	public void runAllAlgorithms() {
		final List<Appraisal> activeAppraisals = appraisalRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);

		for (final Appraisal activeAppraisal : activeAppraisals) {
			for (final Algorithm recalcAlg : recalcAlgs) {
				recalcAlg.execute(activeAppraisal);
			}
		}

		appraisalRepository.saveAll(activeAppraisals);

		final List<RealEstate> activeRealEstates = realEstateRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);

		for (final RealEstate activeRealEstate : activeRealEstates) {
			for (final Algorithm recalcAlg : recalcAlgs) {
				recalcAlg.execute(activeRealEstate);
			}
		}
		
		realEstateRepository.saveAll(activeRealEstates);
	}

}
