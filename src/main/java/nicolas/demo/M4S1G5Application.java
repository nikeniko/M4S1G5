package nicolas.demo;

import nicolas.demo.entities.*;
import nicolas.demo.enums.TipoPostazione;
import nicolas.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class M4S1G5Application implements CommandLineRunner {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private PostazioneRepository postazioneRepository;

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private EdificioRepository edificioRepository;

	public static void main(String[] args) {
		SpringApplication.run(M4S1G5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Creare un edificio
		Edificio edificio = new Edificio();
		edificio.setNome("Edificio A");
		edificio.setIndirizzo("Via Roma 1");
		edificio.setCitta("Roma");
		edificio = edificioRepository.save(edificio);

		Postazione postazione = new Postazione();
		postazione.setCodiceUnivoco("P001");
		postazione.setDescrizione("Postazione 1");
		postazione.setTipo(TipoPostazione.PRIVATO);
		postazione.setPosti(1);
		postazione.setOccupati(0);
		postazione.setEdificio(edificio);
		postazione = postazioneRepository.save(postazione);

		Utente utente = new Utente();
		utente.setId("pgigi");
		utente.setNome_Cognome("Gigi Prestiggiacomo");
		utente.setEmail("Gigi.Prestiggiacomo@gmail.com");
		utenteRepository.save(utente);

		LocalDate dataPrenotazione = LocalDate.of(2024, 9, 6);
		try {
			Prenotazione prenotazione = prenotazioneService.prenotaPostazione("pgigi", postazione.getId(), dataPrenotazione);
			System.out.println("Prenotazione effettuata con successo: " + prenotazione);
		} catch (IllegalArgumentException e) {
			System.err.println("Errore nella prenotazione: " + e.getMessage());
		}
	}
}
