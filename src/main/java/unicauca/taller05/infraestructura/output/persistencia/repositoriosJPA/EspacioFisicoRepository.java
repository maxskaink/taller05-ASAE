package unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unicauca.taller05.infraestructura.output.persistencia.entidades.EspacioFisicoEntity;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface EspacioFisicoRepository extends JpaRepository<EspacioFisicoEntity, Integer> {
    @Query("""
         select (count(f) > 0)
         from EspacioFisicoEntity e
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
    /**
     * Cambia el estado de un espacio fisico
     * @param id
     * @return si el registro con ese id existía y fue actualizado devuelve 1, si no existía  devuelve 0
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE EspacioFisico SET estado = NOT estado WHERE id = :id", nativeQuery = true)
    int switchEstado(@Param("id") Integer id);

    List<EspacioFisicoEntity> findByNombreStartingWithIgnoreCaseAndCapacidadGreaterThanEqualOrderByNombreAsc(
            String nombre, Integer capacidad);

}
