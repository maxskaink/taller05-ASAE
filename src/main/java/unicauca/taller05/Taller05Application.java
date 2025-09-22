package unicauca.taller05;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import unicauca.taller05.models.Docente;
import unicauca.taller05.models.Oficina;
import unicauca.taller05.repositories.CursoRepository;
import unicauca.taller05.repositories.DocenteRepository;

@SpringBootApplication
@Transactional
public class Taller05Application implements CommandLineRunner {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public static void main(String[] args) {
        SpringApplication.run(Taller05Application.class, args);
    }

    /**
     * Este método crea un Docente (que hereda de Persona) y la Oficina asignada.
     * Luego los almacena usando únicamente un save del repositorio de Docente, confiando en la
     * acción en cascada PERSIST desde Docente hacia Oficina.
     * @param nombre nombre del docente
     * @param apellido apellido del docente
     * @param correo correo del docente
     * @param nombreOficina nombre de la oficiona asignada al docente
     * @param ubicacionOficina ubicacion de la oficina asignada al docente
     * @return Docente creado
     */
    private Docente crearDocenteConPersonaYOficina(String nombre, String apellido, String correo,
                                                  String nombreOficina, String ubicacionOficina) {
        Oficina oficina = new Oficina();
        oficina.setNombre(nombreOficina);
        oficina.setUbicacion(ubicacionOficina);

        Docente docente = new Docente();

        docente.setNombre(nombre);
        docente.setApellido(apellido);
        docente.setCorreo(correo);

        docente.setOficina(oficina);

        return docenteRepository.save(docente);
    }

    /**
     * Elimina un curso y, gracias a la accion en cascada, se eliminan sus franjas horarias.
     * @param cursoId id del curso a eliminar
     */
    public void eliminarCurso(Integer cursoId) {
        cursoRepository.deleteById(cursoId);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
