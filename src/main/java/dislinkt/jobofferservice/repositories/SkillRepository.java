package dislinkt.jobofferservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dislinkt.jobofferservice.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	
	Optional<Skill> findById(Long id);
	
}