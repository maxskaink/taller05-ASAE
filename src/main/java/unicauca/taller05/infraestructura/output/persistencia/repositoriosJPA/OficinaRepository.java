package unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.taller05.infraestructura.output.persistencia.entidades.OficinaEntity;

import java.util.Optional;

@Repository
public interface OficinaRepository extends JpaRepository<OficinaEntity, Integer> {
	Optional<OficinaEntity> findByNombre(String nombre);
}
