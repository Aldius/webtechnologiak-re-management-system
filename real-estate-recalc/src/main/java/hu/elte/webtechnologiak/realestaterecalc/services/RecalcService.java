package hu.elte.webtechnologiak.realestaterecalc.services;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.AppraisalRepository;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.RealEstateRepository;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcAppraisalCurrencies;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcGeoCodes;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcReCurrencies;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcReMarketValue;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcTotalAppraisedValue;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcWeatherDiscValue;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal.CalcWeatherDiscValueCurrencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class RecalcService {

	private static final List<Algorithm> recalcAlgs = new ArrayList<>();

	private static final List<Algorithm> currencyAlgs = new ArrayList<>();

	static {
		recalcAlgs.add(new CalcTotalAppraisedValue());
		recalcAlgs.add(new CalcAppraisalCurrencies());
		recalcAlgs.add(new CalcReMarketValue());
		recalcAlgs.add(new CalcGeoCodes());
		recalcAlgs.add(new CalcReCurrencies());
		recalcAlgs.add(new CalcWeatherDiscValue());
		recalcAlgs.add(new CalcWeatherDiscValueCurrencies());

		currencyAlgs.add(new CalcAppraisalCurrencies());
		currencyAlgs.add(new CalcReCurrencies());
		currencyAlgs.add(new CalcWeatherDiscValueCurrencies());
	}

	private final AppraisalRepository appraisalRepository;

	private final RealEstateRepository realEstateRepository;

	@Autowired
	public RecalcService( final AppraisalRepository appraisalRepository, final RealEstateRepository realEstateRepository ) {
		this.appraisalRepository = appraisalRepository;
		this.realEstateRepository = realEstateRepository;
	}

	public void runAllAlgorithms() throws IOException {
		runAlgs(recalcAlgs);
	}

	public void runCurrencyAlgorithms() throws IOException {
		runAlgs(currencyAlgs);
	}

	private void runAlgs( final List<Algorithm> algorithms ) throws IOException {
		final List<Appraisal> activeAppraisals = appraisalRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);

		for (final Appraisal activeAppraisal : activeAppraisals) {
			for (final Algorithm recalcAlg : algorithms) {
				recalcAlg.execute(activeAppraisal);
			}
		}

		appraisalRepository.saveAll(activeAppraisals);

		final List<RealEstate> activeRealEstates = realEstateRepository.findAllByStatus(BaseEntity.ACTIVE_ENTITY_STATUS);

		for (final RealEstate activeRealEstate : activeRealEstates) {
			for (final Algorithm recalcAlg : algorithms) {
				recalcAlg.execute(activeRealEstate);
			}
		}

		realEstateRepository.saveAll(activeRealEstates);
	}

}
