package dislinkt.jobofferservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dislinkt.jobofferservice.entities.JobPosition;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
	
	Optional<JobPosition> findById(Long id);
	
	Optional<JobPosition> findByTitle(String title);

}