package unicauca.taller05.dominio.casosUso;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import unicauca.taller05.aplicacion.in.CUCursosIn;
import unicauca.taller05.aplicacion.out.CursoFormaterOut;
import unicauca.taller05.aplicacion.out.CursoRepositoryOut;
import unicauca.taller05.dominio.modelos.Curso;
@Service
@AllArgsConstructor
public class CursoService implements CUCursosIn{


    private final CursoFormaterOut cursoFormaterOut;
    private final CursoRepositoryOut cursoRepositoryOut;
    @Override
    public List<Curso> obtenerCursoPorAsignatura(String nombreAsignatura) {
        if(nombreAsignatura == null || nombreAsignatura.isEmpty()){
            cursoFormaterOut.retornarErrorParametroInvalido("El nombre de la asignatura no puede ser nulo o vacio");
            return List.of();
        }   
        var cursoEncontrado = cursoRepositoryOut.obtenerCursoPorNombreAsignatura(nombreAsignatura);
        if(cursoEncontrado.isEmpty()){
            cursoFormaterOut.retornarErrorCursoNoEncontrado("No se encontraron cursos para la asignatura: " + nombreAsignatura);
        }
        return cursoEncontrado;
    }
    
}
