package unicauca.taller05.infraestructura.bd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.taller05.infraestructura.bd.models.Administrativo;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Integer> {
}
