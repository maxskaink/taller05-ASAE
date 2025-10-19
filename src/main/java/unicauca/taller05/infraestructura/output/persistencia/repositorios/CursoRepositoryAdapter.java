package unicauca.taller05.infraestructura.output.persistencia.repositorios;

import java.util.List;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import unicauca.taller05.aplicacion.out.CursoRepositoryOut;
import unicauca.taller05.dominio.modelos.Asignatura;
import unicauca.taller05.dominio.modelos.Curso;
import unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA.CursoRepository;

@Repository
@AllArgsConstructor
public class CursoRepositoryAdapter implements CursoRepositoryOut {

    private final CursoRepository cursoRepositoryJPA;
    private final ModelMapper modelMapper;

    @Override
    public List<Curso> obtenerCursoPorNombreAsignatura(String nombreAsignatura) {
        
        var cursosEntity = cursoRepositoryJPA.findByAsignaturaNombreContainingIgnoreCase(nombreAsignatura);
    return cursosEntity.stream().map(entity -> {
        Curso curso = new Curso();
        curso.setId(entity.getId());
        curso.setNombre(entity.getNombre());
        if (entity.getAsignatura() != null) {
            var asignatura = new Asignatura();
            asignatura.setId(entity.getAsignatura().getId());
            asignatura.setNombre(entity.getAsignatura().getNombre());
            asignatura.setCodigo(entity.getAsignatura().getCodigo());
            curso.setAsignatura(asignatura);
        }
        return curso;
    }).toList();
    }
}