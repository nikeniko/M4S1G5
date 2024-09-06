package nicolas.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import nicolas.demo.entities.Postazione;
import nicolas.demo.enums.TipoPostazione;

@Entity
@Data
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codiceUnivoco;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;

    private int posti;
    private int Occupati;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
}

