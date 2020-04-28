package hu.elte.webtechnologiak.realestaterecalc.services;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.AppraisalRepository;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Service
@SessionScope
public class NotificationService {

	private final AppraisalRepository appraisalRepository;

	private final RealEstateRepository realEstateRepository;

	@Autowired
	public NotificationService( final RealEstateRepository realEstateRepository, final AppraisalRepository appraisalRepository ) {
		this.realEstateRepository = realEstateRepository;
		this.appraisalRepository = appraisalRepository;
	}

	public RealEstate addRealEstate( final RealEstate realEstate ) {
		realEstate.setStatus(BaseEntity.ACTIVE_ENTITY_STATUS);
		return realEstateRepository.save(realEstate);
	}

	public RealEstate getRealEstate( final RealEstate realEstate ) {
		final Optional<RealEstate> repositoryByUniqueId = realEstateRepository.findByUniqueId(realEstate.getUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			return repositoryByUniqueId.get();
		} else {
			throw new IllegalStateException("Real estate was not found!");
		}
	}

	public RealEstate removeRealEstate( final RealEstate realEstate ) {
		final Optional<RealEstate> repositoryByUniqueId = realEstateRepository.findByUniqueId(realEstate.getUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			final RealEstate realEstateLoaded = repositoryByUniqueId.get();
			realEstateLoaded.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
			return realEstateRepository.save(realEstateLoaded);
		} else {
			throw new IllegalStateException("Real estate was not found!");
		}
	}

	public Appraisal addAppraisal( final Appraisal appraisal ) {
		final Optional<RealEstate> repositoryByUniqueId = realEstateRepository.findByUniqueId(appraisal.getRealEstateUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			final RealEstate realEstate = repositoryByUniqueId.get();
			appraisal.setRealEstate(realEstate);
			appraisal.setStatus(BaseEntity.ACTIVE_ENTITY_STATUS);
			return appraisalRepository.save(appraisal);
		} else {
			throw new IllegalStateException("Real estate was not found!");
		}
	}

	public Appraisal getAppraisal( final Appraisal appraisal) {
		final Optional<Appraisal> repositoryByUniqueId = appraisalRepository.findByUniqueId(appraisal.getUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			return repositoryByUniqueId.get();
		} else {
			throw new IllegalStateException("Appraisal was not found!");
		}
	}

	public Appraisal removeAppraisal( final Appraisal appraisal ) {
		final Optional<Appraisal> repositoryByUniqueId = appraisalRepository.findByUniqueId(appraisal.getUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			final Appraisal appraisalLoaded = repositoryByUniqueId.get();
			appraisalLoaded.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
			return appraisalRepository.save(appraisal);
		} else {
			throw new IllegalStateException("Appraisal was not found!");
		}
	}



}
