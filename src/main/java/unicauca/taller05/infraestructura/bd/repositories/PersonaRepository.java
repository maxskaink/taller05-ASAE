package unicauca.taller05.infraestructura.bd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.taller05.infraestructura.bd.models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
