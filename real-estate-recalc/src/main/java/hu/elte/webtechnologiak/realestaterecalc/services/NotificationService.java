package hu.elte.webtechnologiak.realestaterecalc.services;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.dto.AppraisalDto;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.dto.RealEstateDto;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.AppraisalRepository;
import hu.elte.webtechnologiak.realestaterecalc.model.repositories.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.ZoneId;
import java.util.Date;
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

	public RealEstate addRealEstate( final RealEstateDto realEstateDto ) {
		final RealEstate realEstate = new RealEstate();
		realEstate.setUniqueId(realEstateDto.getUniqueId());
		realEstate.setCountry(realEstateDto.getCountry());
		realEstate.setCity(realEstateDto.getCity());
		realEstate.setStreet(realEstateDto.getStreet());
		realEstate.setStreetNumber(realEstateDto.getStreetNumber());
		realEstate.setZipCode(realEstateDto.getZipCode());

		//TODO get lat and long

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

	public RealEstate removeRealEstate( final RealEstateDto realEstateDto ) {
		final Optional<RealEstate> repositoryByUniqueId = realEstateRepository.findByUniqueId(realEstateDto.getUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			final RealEstate realEstateLoaded = repositoryByUniqueId.get();
			realEstateLoaded.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
			return realEstateRepository.save(realEstateLoaded);
		} else {
			throw new IllegalStateException("Real estate was not found!");
		}
	}

	public Appraisal addAppraisal( final AppraisalDto appraisalDto ) {
		final Optional<RealEstate> repositoryByUniqueId = realEstateRepository.findByUniqueId(appraisalDto.getRealEstateUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			final RealEstate realEstate = repositoryByUniqueId.get();

			final Appraisal appraisal = new Appraisal();
			appraisal.setUniqueId(appraisalDto.getUniqueId());
			appraisal.setRealEstateUniqueId(appraisalDto.getRealEstateUniqueId());
			appraisal.setAppraisalDate(appraisalDto.getAppraisalDate());
			appraisal.setAppraisedMarketValueOfBuildingsOccy(appraisalDto.getAppraisedMarketValueOfBuildingsOccy());
			appraisal.setAppraisedMarketValueOfBuildingsCcy(appraisalDto.getAppraisedMarketValueOfBuildingsCcy());
			appraisal.setAppraisedMarketValueOfLandOccy(appraisalDto.getAppraisedMarketValueOfLandOccy());
			appraisal.setAppraisedMarketValueOfLandCcy(appraisalDto.getAppraisedMarketValueOfLandCcy());
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

	public Appraisal removeAppraisal( final AppraisalDto appraisalDto ) {
		final Optional<Appraisal> repositoryByUniqueId = appraisalRepository.findByUniqueId(appraisalDto.getUniqueId());
		if (repositoryByUniqueId.isPresent()) {
			final Appraisal appraisalLoaded = repositoryByUniqueId.get();
			appraisalLoaded.setStatus(BaseEntity.INACTIVE_ENTITY_STATUS);
			return appraisalRepository.save(appraisalLoaded);
		} else {
			throw new IllegalStateException("Appraisal was not found!");
		}
	}



}
