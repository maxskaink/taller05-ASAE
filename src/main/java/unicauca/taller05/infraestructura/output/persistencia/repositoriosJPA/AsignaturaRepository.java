package unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.taller05.infraestructura.output.persistencia.entidades.AsignaturaEntity;

@Repository
public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Integer> {
}
