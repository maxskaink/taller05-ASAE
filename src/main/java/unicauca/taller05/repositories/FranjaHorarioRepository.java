package unicauca.taller05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unicauca.taller05.models.FranjaHorario;

@Repository
public interface FranjaHorarioRepository extends JpaRepository<FranjaHorario, Integer> {
    @Modifying
    @Query("DELETE FROM FranjaHorario fh WHERE fh.curso.id = :cursoId")
    int eliminarFranjasPorCurso(@Param("cursoId") Integer cursoId);
}
