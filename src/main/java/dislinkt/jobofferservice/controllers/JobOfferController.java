package dislinkt.jobofferservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislinkt.jobofferservice.dtos.JobOfferDto;
import dislinkt.jobofferservice.services.JobOfferService;

@RestController
@RequestMapping("/job-offer")
public class JobOfferController {

	@Autowired
	private JobOfferService jobOfferService;
	
	@PreAuthorize("hasRole('ROLE_AGENT')")
	@PostMapping("")
	public ResponseEntity<?> createJobOffer(@RequestBody JobOfferDto jobOfferDto) {
		JobOfferDto dto = jobOfferService.createJobOffer(jobOfferDto);
		return ResponseEntity.ok(dto);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_AGENT', 'ROLE_ADMINISTRATOR')")
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable String query) {
		List<JobOfferDto> offers = jobOfferService.search(query);
		return ResponseEntity.ok(offers);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_AGENT', 'ROLE_ADMINISTRATOR')")
	@GetMapping("")
	public ResponseEntity<?> getJobOffers() {
		List<JobOfferDto> offers = jobOfferService.getJobOffers();
		return ResponseEntity.ok(offers);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_AGENT', 'ROLE_ADMINISTRATOR')")
	@GetMapping("/{jobOfferId}")
	public ResponseEntity<?> getJobOffer(@PathVariable Long jobOfferId) {
		JobOfferDto dto = jobOfferService.getJobOffer(jobOfferId);
		return ResponseEntity.ok(dto);
	}
}
