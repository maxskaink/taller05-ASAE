package unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import unicauca.taller05.infraestructura.output.persistencia.entidades.CursoEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
    @Query("""
           select distinct c
           from CursoEntity c
           left join fetch c.franjas f
           left join fetch f.espacioFisico e
           where c.id = :cursoId
           """)
    Optional<CursoEntity> obtenerCursoConFranjasYEspacio(@Param("cursoId") Integer cursoId);

    List<CursoEntity> findByAsignaturaNombreIgnoreCase(String nombre);
    List<CursoEntity> findByAsignaturaNombreContainingIgnoreCase(String nombre);
}