package unicauca.taller05.infraestructura.output.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unicauca.taller05.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;

@Repository
public interface FranjaHorarioRepository extends JpaRepository<FranjaHorariaEntity, Integer> {
    @Modifying
    @Query("DELETE FROM FranjaHorariaEntity fh WHERE fh.curso.id = :cursoId")
    int eliminarFranjasPorCurso(@Param("cursoId") Integer cursoId);
}
