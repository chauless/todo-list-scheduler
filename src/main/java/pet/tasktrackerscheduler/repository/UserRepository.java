package pet.tasktrackerscheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.tasktrackerscheduler.scheduler.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
