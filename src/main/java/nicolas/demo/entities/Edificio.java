package nicolas.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String indirizzo;
    private String citta;
}
