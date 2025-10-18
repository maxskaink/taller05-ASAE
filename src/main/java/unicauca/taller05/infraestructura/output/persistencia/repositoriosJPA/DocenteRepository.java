package unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unicauca.taller05.infraestructura.output.persistencia.entidades.DocenteEntity;

import java.time.LocalTime;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, Integer> {
    @Query(
            value = """
                select (count(*) > 0)
                from FranjaHorario f
                join Curso c on c.id = f.curso_id
                join curso_docente cd on cd.curso_id = c.id
                join Persona d on d.id = cd.docente_id
                where d.id = :docenteId
                  and f.dia = :dia
                  and f.horaInicio < :horaFin
                  and f.horaFin > :horaInicio
                """,
            nativeQuery = true
    )
    boolean docenteOcupadoEnHorarioNativo(@Param("dia") String dia,
                                          @Param("horaInicio") LocalTime horaInicio,
                                          @Param("horaFin") LocalTime horaFin,
                                          @Param("docenteId") Integer docenteId);

}
