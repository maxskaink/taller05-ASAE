package unicauca.taller05;

import jakarta.transaction.Transactional;

import java.time.LocalTime;
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
import unicauca.taller05.repositories.*;

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

    @Autowired
    private EspacioFisicoRepository espacioFisicoRepository;

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
     * Crea una franja horaria y la asocia a un curso y espacio físico existentes.
     * Utiliza getReferenceById para obtener las entidades relacionadas sin cargarlas completamente.
     * @param dia día de la semana para la franja horaria
     * @param horaInicio hora de inicio de la franja
     * @param horaFin hora de fin de la franja
     * @param cursoId ID del curso existente
     * @param espacioFisicoId ID del espacio físico existente
     * @return FranjaHorario creada y guardada
     */
    private FranjaHorario crearFranjaHoraria(String dia, LocalTime horaInicio, LocalTime horaFin,
                                             Integer cursoId, Integer espacioFisicoId) {
        // Obtener referencias a las entidades existentes sin cargarlas completamente
        Curso curso = cursoRepository.getReferenceById(cursoId);
        EspacioFisico espacioFisico = espacioFisicoRepository.getReferenceById(espacioFisicoId);
        
        // Crear la nueva franja horaria
        FranjaHorario franjaHorario = new FranjaHorario();
        franjaHorario.setDia(dia);
        franjaHorario.setHoraInicio(horaInicio);
        franjaHorario.setHoraFin(horaFin);
        franjaHorario.setCurso(curso);
        franjaHorario.setEspacioFisico(espacioFisico);
        
        // Guardar y retornar la franja horaria
        return franjaHorarioRepository.save(franjaHorario);
    }

    /**
     * Consulta las franjas horarias de un curso utilizando EAGER LOADING con anotaciones.
     * Muestra los datos de las franjas, docentes, curso y espacios físicos asociados.
     * El eager loading se configura directamente en las entidades con @OneToMany(fetch = FetchType.EAGER)
     * @param cursoId ID del curso a consultar
     * @return Lista de franjas horarias con datos completos
     */
    private List<FranjaHorario> consultarFranjasHorariasDelCurso(Integer cursoId) {
        System.out.println("=== EJECUTANDO CONSULTA CON EAGER LOADING (ANOTACIONES) ===");
        System.out.println("Cargando curso con ID: " + cursoId + " usando fetch = FetchType.EAGER...");
        
        // Con EAGER LOADING configurado en las anotaciones, findById carga automáticamente
        // todas las entidades relacionadas marcadas con FetchType.EAGER
        Curso curso = cursoRepository.findById(cursoId).orElse(null);
        
        if (curso == null) {
            System.out.println("Curso no encontrado con ID: " + cursoId);
            return new ArrayList<>();
        }
        
        System.out.println("✓ Datos cargados con EAGER LOADING - Relaciones cargadas automáticamente\n");
        
        System.out.println("=== INFORMACIÓN DEL CURSO (Cargado con EAGER) ===");
        System.out.println("ID: " + curso.getId());
        System.out.println("Nombre: " + curso.getNombre());
        if (curso.getAsignatura() != null) {
            System.out.println("Asignatura: " + curso.getAsignatura().getNombre() + 
                            " (" + curso.getAsignatura().getCodigo() + ") - EAGER LOADED");
        }
        
        System.out.println("\n=== DOCENTES DEL CURSO (Cargados con EAGER) ===");
        if (curso.getDocentes() != null && !curso.getDocentes().isEmpty()) {
            for (Docente docente : curso.getDocentes()) {
                System.out.println("- " + docente.getNombre() + " " + docente.getApellido() + 
                                " (" + docente.getCorreo() + ") - EAGER LOADED");
                if (docente.getOficina() != null) {
                    System.out.println("  Oficina: " + docente.getOficina().getNombre() + 
                                    " - " + docente.getOficina().getUbicacion() + " - EAGER LOADED");
                }
            }
        } else {
            System.out.println("No hay docentes asignados al curso");
        }
        
        System.out.println("\n=== FRANJAS HORARIAS (Cargadas con EAGER) ===");
        if (curso.getFranjas() != null && !curso.getFranjas().isEmpty()) {
            for (FranjaHorario franja : curso.getFranjas()) {
                System.out.println("Franja ID: " + franja.getId() + " - EAGER LOADED");
                System.out.println("Día: " + franja.getDia());
                System.out.println("Hora: " + franja.getHoraInicio() + " - " + franja.getHoraFin());
                
                if (franja.getEspacioFisico() != null) {
                    EspacioFisico espacio = franja.getEspacioFisico();
                    System.out.println("Espacio Físico: " + espacio.getNombre() + " - EAGER LOADED");
                    System.out.println("Capacidad: " + espacio.getCapacidad() + " personas");
                }
                System.out.println("---");
            }
            
            System.out.println("✓ TOTAL DE FRANJAS CARGADAS CON EAGER: " + curso.getFranjas().size());
            return curso.getFranjas();
        } else {
            System.out.println("No hay franjas horarias para este curso");
            return new ArrayList<>();
        }
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
    public Curso crearCurso(String nombre,int idAsignatura, int[] idsDocentes) {
    System.out.println("----------------------------------------------------------");
    System.out.println("Creando curso: " + nombre + " con asignatura ID: " + idAsignatura);

    Curso curso = new Curso();
    curso.setNombre(nombre);
    curso.setAsignatura(asignaturaRepository.getReferenceById(idAsignatura));

    for (int idDocente : idsDocentes) {
        Docente docente = docenteRepository.getReferenceById(idDocente);

        // mantener ambos lados sincronizados
        curso.getDocentes().add(docente);
        docente.getCursos().add(curso);

        System.out.println(" - " + docente.getNombre() + " " + docente.getApellido());
    }

    return cursoRepository.save(curso);
}


    public void consultarFranjaByDocente(int idDocente) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Consultando franjas horarias del docente con ID: " + idDocente);
        Docente docente = docenteRepository.getReferenceById(idDocente);
        
        System.out.println("Docente: " + docente.getNombre() + " " + docente.getApellido());
        if(docente.getCursos()==null){
            System.out.println("No tiene cursos asignados");
            return;
        }
        for (Curso curso : docente.getCursos()) {
            System.out.println("  Curso: " + curso.getNombre());
            if(curso.getFranjas()==null){
                System.out.println("Este curso no tiene franjas horarias relacionadas");
                return; 
            }
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
        System.out.println("=== INICIANDO PRUEBAS ===\n");

        // 1. Crear datos base necesarios
        System.out.println("1. Creando datos base...");
        
        // Crear asignatura
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Arquitecturas de Software");
        asignatura.setCodigo("ASAE");
        asignatura = asignaturaRepository.save(asignatura);
        System.out.println("Asignatura creada: " + asignatura.getNombre());

        // Crear espacio físico
        EspacioFisico espacioFisico = new EspacioFisico();
        espacioFisico.setNombre("Aula 201");
        espacioFisico.setCapacidad(30);
        espacioFisico = espacioFisicoRepository.save(espacioFisico);
        System.out.println("Espacio físico creado: " + espacioFisico.getNombre());

        // 2. Crear docente con oficina usando el método
        System.out.println("\n2. Probando método crearDocenteConPersonaYOficina...");
        Docente docente = crearDocenteConPersonaYOficina(
                "Juan Carlos", 
                "Pérez", 
                "juan.perez@unicauca.edu.co",
                "Oficina 101", 
                "Primer piso"
        );
        System.out.println("Docente creado: " + docente.getNombre() + " " + docente.getApellido());
        System.out.println("Oficina asignada: " + docente.getOficina().getNombre());

        // 3. Crear curso y asociar docente
        System.out.println("\n3. Creando curso...");
        Curso curso = crearCurso("curso1", 1, new int[] {1});
        System.out.println("Curso creado: " + curso.getNombre());

        // 4. Probar método crearFranjaHoraria
        System.out.println("\n4. Probando método crearFranjaHoraria...");
        FranjaHorario franja1 = crearFranjaHoraria(
                "Lunes", 
                LocalTime.of(8, 0), 
                LocalTime.of(10, 0),
                curso.getId(), 
                espacioFisico.getId()
        );
        System.out.println("Franja horaria 1 creada: " + franja1.getDia() + " " + 
                          franja1.getHoraInicio() + " - " + franja1.getHoraFin());

        FranjaHorario franja2 = crearFranjaHoraria(
                "Miércoles", 
                LocalTime.of(14, 0), 
                LocalTime.of(16, 0),
                curso.getId(), 
                espacioFisico.getId()
        );
        System.out.println("Franja horaria 2 creada: " + franja2.getDia() + " " + 
                          franja2.getHoraInicio() + " - " + franja2.getHoraFin());

        // 5. Probar método consultarFranjasHorariasDelCurso
        System.out.println("\n5. Probando método consultarFranjasHorariasDelCurso...");
        List<FranjaHorario> franjas = consultarFranjasHorariasDelCurso(curso.getId());
        System.out.println("Total de franjas encontradas: " + franjas.size());

        ///6, Consultar franja por docente
        System.out.println("\n Probando metodo consultarFranjaByDocente");
        consultarFranjaByDocente(curso.getDocentes().get(0).getId());


        System.out.println("\n=== PRUEBAS COMPLETADAS ===");
    }

}
