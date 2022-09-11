package dislinkt.jobofferservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JobOfferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobOfferServiceApplication.class, args);
	}

}
