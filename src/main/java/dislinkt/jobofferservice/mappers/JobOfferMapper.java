package dislinkt.jobofferservice.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislinkt.jobofferservice.dtos.JobOfferDto;
import dislinkt.jobofferservice.entities.JobOffer;
import dislinkt.jobofferservice.entities.SeniorityLevel;

@Service
public class JobOfferMapper {

	public JobOfferDto toDto(JobOffer jobOffer) {
		return new JobOfferDto(jobOffer.getId(), jobOffer.getTitle(), jobOffer.getDescription(),
				jobOffer.getJobPosition().getTitle(), jobOffer.getCompanyName(),
				jobOffer.getSeniorityLevel().getValue(), jobOffer.getCompanyLink(), jobOffer.getJobPositionLink());
	}

	public JobOffer toEntity(JobOfferDto dto) {
		return new JobOffer(dto.getTitle(), dto.getDescription(), dto.getCompanyName(),
				SeniorityLevel.valueOfInt(dto.getSeniorityLevel()), dto.getCompanyLink(), dto.getJobPositionLink());
	}

	public List<JobOfferDto> toCollectionDto(Collection<JobOffer> jobOffers) {
		return jobOffers.stream().map(this::toDto).collect(Collectors.toList());
	}
}
