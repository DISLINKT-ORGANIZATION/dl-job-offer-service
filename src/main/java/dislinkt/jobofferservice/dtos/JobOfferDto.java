package dislinkt.jobofferservice.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDto {

	private Long id;
	private String title;
	private String description;
	private String jobPositionName;
	private String companyName;
	private int seniorityLevel;
	private String companyLink;
	private String jobPositionLink;
}
