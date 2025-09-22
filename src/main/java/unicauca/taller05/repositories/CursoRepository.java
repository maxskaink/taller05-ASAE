package unicauca.taller05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.taller05.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
