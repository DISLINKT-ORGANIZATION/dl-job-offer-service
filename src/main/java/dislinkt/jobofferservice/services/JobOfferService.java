package dislinkt.jobofferservice.services;

import java.util.List;

import dislinkt.jobofferservice.dtos.JobOfferDto;

public interface JobOfferService {

	JobOfferDto createJobOffer(JobOfferDto jobOfferDto);
	
	List<JobOfferDto> search(String query);
	
	List<JobOfferDto> getJobOffers();
	
	JobOfferDto getJobOffer(Long jobOfferId);
}
