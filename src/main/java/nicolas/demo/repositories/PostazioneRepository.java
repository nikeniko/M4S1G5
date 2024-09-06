package nicolas.demo.repositories;

import nicolas.demo.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
}
