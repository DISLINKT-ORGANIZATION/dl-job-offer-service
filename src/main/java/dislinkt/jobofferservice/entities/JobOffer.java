package dislinkt.jobofferservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "job_offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", unique = false, nullable = false)
	@NonNull
	private String title;

	@Column(name = "description", unique = false, nullable = false)
	@NonNull
	private String description;

	@Column(name = "company_name", unique = false, nullable = false)
	private String companyName;

	@ManyToOne
	private JobPosition jobPosition;

	@Column(name = "seniority_level", unique = false, nullable = false)
	private SeniorityLevel seniorityLevel;

	@Column(name = "company_link", unique = false, nullable = false)
	private String companyLink;

	@Column(name = "job_position_link", unique = false, nullable = false)
	private String jobPositionLink;

	public JobOffer(@NonNull String title, @NonNull String description, String companyName, JobPosition jobPosition,
			SeniorityLevel seniorityLevel, String companyLink, String jobPositionLink) {
		super();
		this.title = title;
		this.description = description;
		this.companyName = companyName;
		this.jobPosition = jobPosition;
		this.seniorityLevel = seniorityLevel;
		this.companyLink = companyLink;
		this.jobPositionLink = jobPositionLink;
	}

	public JobOffer(@NonNull String title, @NonNull String description, String companyName,
			SeniorityLevel seniorityLevel, String companyLink, String jobPositionLink) {
		super();
		this.title = title;
		this.description = description;
		this.companyName = companyName;
		this.seniorityLevel = seniorityLevel;
		this.companyLink = companyLink;
		this.jobPositionLink = jobPositionLink;
	}

}
