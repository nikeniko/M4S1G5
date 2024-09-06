package nicolas.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utente {

    @Id
    private String id;

    private String nome_Cognome;
    private String email;
}
