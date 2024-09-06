package nicolas.demo;

import nicolas.demo.entities.Postazione;
import nicolas.demo.entities.Prenotazione;
import nicolas.demo.entities.Utente;
import nicolas.demo.repositories.PostazioneRepository;
import nicolas.demo.repositories.PrenotazioneRepository;
import nicolas.demo.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional
    public Prenotazione prenotaPostazione(String username, Long postazioneId, LocalDate data) {
        Utente utente = utenteRepository.findById(username).orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        List<Prenotazione> prenotazioniUtente = prenotazioneRepository.findByUtenteAndDataPrenotazione(utente, data);
        if (!prenotazioniUtente.isEmpty()) {
            throw new IllegalArgumentException("L'utente ha già una prenotazione per questa data.");
        }

        Postazione postazione = postazioneRepository.findById(postazioneId).orElseThrow(() -> new IllegalArgumentException("Postazione non trovata"));
        List<Prenotazione> prenotazioniPostazione = prenotazioneRepository.findByPostazioneAndDataPrenotazione(postazione, data);
        if (!prenotazioniPostazione.isEmpty()) {
            throw new IllegalArgumentException("La postazione è già prenotata per questa data.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);
        prenotazione.setDataPrenotazione(data);

        return prenotazioneRepository.save(prenotazione);
    }
}

