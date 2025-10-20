package unicauca.taller05.infraestructura.output.persistencia.repositorios;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Curso> obtenerCursoPorId(Integer id) {
        return cursoRepositoryJPA.findById(id).map(entity -> modelMapper.map(entity, Curso.class));
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return cursoRepositoryJPA.existsById(id);
    }
}