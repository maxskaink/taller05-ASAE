package unicauca.taller05;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import unicauca.taller05.models.Asignatura;
import unicauca.taller05.models.Curso;
import unicauca.taller05.models.Docente;
import unicauca.taller05.models.EspacioFisico;
import unicauca.taller05.models.FranjaHorario;
import unicauca.taller05.models.Oficina;
import unicauca.taller05.repositories.AsignaturaRepository;
import unicauca.taller05.repositories.CursoRepository;
import unicauca.taller05.repositories.DocenteRepository;
import unicauca.taller05.repositories.FranjaHorarioRepository;

@SpringBootApplication
@Transactional
public class Taller05Application implements CommandLineRunner {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private FranjaHorarioRepository franjaHorarioRepository;

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
    /**
     * Crea un curso con una asignatura y varios docentes.
     * @param nombre nombre del curso
     * @param idAsignatura id de la asignatura
     * @param idsDocentes id de los docenntes
     */
    public void crearCurso(String nombre,int idAsignatura, int[]  idsDocentes) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Creando curso: " + nombre + " con asignatura ID: " + idAsignatura);
        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setAsignatura(asignaturaRepository.getReferenceById(idAsignatura));
        List<Docente> docentes = new ArrayList<>();
        for (int idDocente : idsDocentes) {
            Docente docente = docenteRepository.getReferenceById(idDocente);
            docentes.add(docente);
        }
        curso.setDocentes(docentes);
        System.out.println("Docentes asignados al curso:");
        for (Docente docente : docentes) {
            System.out.println(" - " + docente.getNombre() + " " + docente.getApellido());
        }
        cursoRepository.save(curso);
    }

    public void consultarFranjaByDocente(int idDocente) {
    System.out.println("----------------------------------------------------------");
    System.out.println("Consultando franjas horarias del docente con ID: " + idDocente);
    Docente docente = docenteRepository.getReferenceById(idDocente);
    
    System.out.println("Docente: " + docente.getNombre() + " " + docente.getApellido());

    for (Curso curso : docente.getCursos()) {
        System.out.println("  Curso: " + curso.getNombre());
        for (FranjaHorario franja : curso.getFranjas()) {
            System.out.println("    Franja horaria:");
            System.out.println("      Día: " + franja.getDia());
            System.out.println("      Hora inicio: " + franja.getHoraInicio());
            System.out.println("      Hora fin: " + franja.getHoraFin());

            EspacioFisico espacio = franja.getEspacioFisico();
            if (espacio != null) {
                System.out.println("      Espacio físico: " + espacio.getNombre());
                System.out.println("      Capacidad: " + espacio.getCapacidad());
            } else {
                System.out.println("      Espacio físico: No asignado");
            }
        }
    }
}

    @Override
    public void run(String... args) throws Exception {
        //consultarFranjaByDocente(1);
        
        crearDocenteConPersonaYOficina("Miguel", "Burnbano", "Burnbano@gmail.com", "122", "norte");
        //crearCurso("Curso1", 1, new int[]{1});
    }

}
