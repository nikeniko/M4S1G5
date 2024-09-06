package nicolas.demo.repositories;

import nicolas.demo.entities.Postazione;
import nicolas.demo.entities.Prenotazione;
import nicolas.demo.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate data);

    List<Prenotazione> findByUtenteAndDataPrenotazione(Utente utente, LocalDate data);
}
