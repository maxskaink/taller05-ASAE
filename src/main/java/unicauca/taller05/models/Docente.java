package unicauca.taller05.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente extends Persona {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "oficina_id")
    private Oficina oficina;

}
