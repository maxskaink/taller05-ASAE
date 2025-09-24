package unicauca.taller05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unicauca.taller05.models.Curso;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    @Query("""
           select distinct c
           from Curso c
           left join fetch c.franjas f
           left join fetch f.espacioFisico e
           where c.id = :cursoId
           """)
    Optional<Curso> obtenerCursoConFranjasYEspacio(@Param("cursoId") Integer cursoId);

}