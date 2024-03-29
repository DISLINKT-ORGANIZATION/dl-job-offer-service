package dislinkt.jobofferservice.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import dislinkt.jobofferservice.model.EventKafka;
import dislinkt.jobofferservice.model.EventType;
import dislinkt.jobofferservice.dtos.JobOfferDto;
import dislinkt.jobofferservice.entities.JobOffer;
import dislinkt.jobofferservice.entities.JobPosition;
import dislinkt.jobofferservice.exceptions.EntityNotFound;
import dislinkt.jobofferservice.mappers.JobOfferMapper;
import dislinkt.jobofferservice.repositories.JobOfferRepository;
import dislinkt.jobofferservice.repositories.JobPositionRepository;
import dislinkt.jobofferservice.repositories.specification.JobOfferSpecification;
import dislinkt.jobofferservice.services.JobOfferService;

@Service
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferRepository jobOfferRepository;

	@Autowired
	private JobPositionRepository jobPositionRepository;

	@Autowired
	private JobOfferMapper jobOfferMapper;

	@Autowired
	private KafkaTemplate<String, EventKafka> eventKafkaTemplate;

	public JobOfferDto createJobOffer(JobOfferDto jobOfferDto) {
		Optional<JobPosition> jobPositionOptional = jobPositionRepository.findByTitle(jobOfferDto.getJobPositionName());
		if (jobPositionOptional.isEmpty()) {
			throw new EntityNotFound("Job position not found.");
		}
		JobPosition jobPosition = jobPositionOptional.get();
		JobOffer newJobOffer = jobOfferMapper.toEntity(jobOfferDto);
		newJobOffer.setJobPosition(jobPosition);
		JobOffer savedJobOffer = jobOfferRepository.save(newJobOffer);

		EventKafka event = new EventKafka(new Date(),
				"Created new job offer " + savedJobOffer.getTitle() + " by agent " + jobOfferDto.getUsername() + ".",
				EventType.CREATED_JOB_OFFER);
		eventKafkaTemplate.send("dislinkt-events", event);

		return jobOfferMapper.toDto(savedJobOffer);
	}

	public List<JobOfferDto> search(String query) {
		JobOfferSpecification spec = new JobOfferSpecification(query);
		return jobOfferMapper.toCollectionDto(jobOfferRepository.findAll(spec));
	}

	public List<JobOfferDto> getJobOffers() {
		return jobOfferMapper.toCollectionDto(jobOfferRepository.findAll());
	}

	public JobOfferDto getJobOffer(Long jobOfferId) {
		Optional<JobOffer> jobOfferOptional = jobOfferRepository.findById(jobOfferId);
		if (jobOfferOptional.isEmpty()) {
			throw new EntityNotFound("Job offer not found.");
		}
		JobOffer jobOffer = jobOfferOptional.get();
		return jobOfferMapper.toDto(jobOffer);
	}

}
