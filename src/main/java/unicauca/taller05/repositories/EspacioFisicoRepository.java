package unicauca.taller05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unicauca.taller05.models.EspacioFisico;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface EspacioFisicoRepository extends JpaRepository<EspacioFisico, Integer> {

    
    List<EspacioFisico> findByFranjaHorariosCursoNombreContainingIgnoreCaseAndCapacidadGreaterThanEqualOrderByNombreAsc(String nombre, int capacidad);
    List<EspacioFisico> findByFranjaHorariosCursoNombre(String nombre);
    List<EspacioFisico> findByFranjaHorariosCursoId(int id);

    @Query("""
         select (count(f) > 0)
         from EspacioFisico e
           join e.franjaHorarios f
         where e.id = :espacioId
           and f.dia = :dia
           and f.horaInicio < :horaFin
           and f.horaFin > :horaInicio
         """)
    boolean estaOcupadoEnHorario(@Param("dia") String dia,
                                 @Param("horaInicio") LocalTime horaInicio,
                                 @Param("horaFin") LocalTime horaFin,
                                 @Param("espacioId") Integer espacioId);

    @Query(value = "UPDATE EspacioFisico SET estado = NOT estado WHERE id = :id", nativeQuery = true)
    int switchEstado(@Param("id") Integer id);

}
