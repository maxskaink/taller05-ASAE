package unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import unicauca.taller05.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;

@Repository
public interface FranjaHorarioRepository extends JpaRepository<FranjaHorariaEntity, Integer> {
    @Modifying
    @Query("DELETE FROM FranjaHorariaEntity fh WHERE fh.curso.id = :cursoId")
    int eliminarFranjasPorCurso(@Param("cursoId") Integer cursoId);

    @Query("""
           SELECT DISTINCT fh
           FROM FranjaHorariaEntity fh
           JOIN FETCH fh.curso c
           JOIN FETCH fh.espacioFisico ef
           WHERE c.id = :cursoId
           """)
    List<FranjaHorariaEntity> obtenerFranjasPorIdCursoConJoin(@Param("cursoId") Integer cursoId);

    List<FranjaHorariaEntity> findByCursoId(Integer cursoId);


    @Query("""
    SELECT DISTINCT fh
    FROM FranjaHorariaEntity fh
    JOIN FETCH fh.curso c
    JOIN c.docentes d
    JOIN FETCH fh.espacioFisico ef
    WHERE d.id = :docenteId
    """)
    List<FranjaHorariaEntity> obtenerFranjasPorDocenteIdConJoin(@Param("docenteId") Integer docenteId);


    
}
