package unicauca.taller05;

import jakarta.transaction.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import unicauca.taller05.infraestructura.bd.models.Curso;
import unicauca.taller05.infraestructura.bd.models.Docente;
import unicauca.taller05.infraestructura.bd.models.EspacioFisico;
import unicauca.taller05.infraestructura.bd.models.FranjaHorario;
import unicauca.taller05.infraestructura.bd.models.Oficina;
import unicauca.taller05.infraestructura.output.persistencia.repositorios.*;

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
         if (curso.getFranjas() == null) {
        curso.setFranjas(new ArrayList<>());
        }
        curso.getFranjas().add(franjaHorario);   
        
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
        System.out.println("=== INICIANDO PRUEBAS CON DATOS INICIALES ===\n");

        // Esperar un poco para que los datos se carguen completamente
        Thread.sleep(1000);

        // 1. Verificar datos cargados inicialmente
        System.out.println("1. Verificando datos iniciales cargados...");
        System.out.println("   Total de cursos: " + cursoRepository.count());
        System.out.println("   Total de docentes: " + docenteRepository.count());
        System.out.println("   Total de franjas horarias: " + franjaHorarioRepository.count());
        System.out.println("   Total de asignaturas: " + asignaturaRepository.count());
        System.out.println("   Total de espacios físicos: " + espacioFisicoRepository.count());

        // 2. Probar método crearDocenteConPersonaYOficina
        System.out.println("\n2. Probando método crearDocenteConPersonaYOficina...");
        Docente nuevoDocente = crearDocenteConPersonaYOficina(
                "Roberto", 
                "Fernández", 
                "roberto.fernandez@unicauca.edu.co",
                "Oficina F-205", 
                "Bloque F - Segundo Piso"
        );
        System.out.println("   ✓ Nuevo docente creado: " + nuevoDocente.getNombre() + " " + nuevoDocente.getApellido());
        System.out.println("   ✓ Oficina asignada: " + nuevoDocente.getOficina().getNombre());

        // 3. Probar método crearCurso con el nuevo docente y docentes existentes
        System.out.println("\n3. Probando método crearCurso...");
        
        // Crear curso solo con el nuevo docente
        Curso nuevoCurso1 = crearCurso(
                "Sistemas Distribuidos - Grupo Avanzado", 
                5, // ID de Sistemas Distribuidos
                new int[]{nuevoDocente.getId()}
        );
        System.out.println("   ✓ Curso creado: " + nuevoCurso1.getNombre());
        
        // Crear curso con múltiples docentes (nuevo docente + docentes existentes)
        Curso nuevoCurso2 = crearCurso(
                "Arquitecturas Empresariales", 
                1, // ID de ASAE (reutilizamos la asignatura)
                new int[]{nuevoDocente.getId(), 1, 2} // Nuevo docente + María + Carlos
        );
        System.out.println("   ✓ Curso con múltiples docentes creado: " + nuevoCurso2.getNombre());

        // 4. Probar método crearFranjaHoraria con los nuevos cursos
        System.out.println("\n4. Probando método crearFranjaHoraria...");
        
        // Agregar franja al primer curso existente
        FranjaHorario nuevaFranja1 = crearFranjaHoraria(
                "Sábado",
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                1, // ASAE Grupo A
                6  // Sala de Conferencias
        );
        System.out.println("   ✓ Franja agregada a curso existente: " + nuevaFranja1.getDia() + " " +
                nuevaFranja1.getHoraInicio() + " - " + nuevaFranja1.getHoraFin());

        // Agregar franjas al nuevo curso creado
        FranjaHorario nuevaFranja2 = crearFranjaHoraria(
                "Lunes",
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                nuevoCurso1.getId(),
                4  // Aula 301
        );
        System.out.println("   ✓ Franja agregada a nuevo curso: " + nuevaFranja2.getDia() + " " +
                nuevaFranja2.getHoraInicio() + " - " + nuevaFranja2.getHoraFin());

        FranjaHorario nuevaFranja3 = crearFranjaHoraria(
                "Miércoles",
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                nuevoCurso2.getId(),
                2  // Laboratorio 1
        );
        System.out.println("   ✓ Franja agregada a curso con múltiples docentes: " + nuevaFranja3.getDia() + " " +
                nuevaFranja3.getHoraInicio() + " - " + nuevaFranja3.getHoraFin());

        // 5. Probar método consultarFranjasHorariasDelCurso con datos existentes
        System.out.println("\n5. Probando método consultarFranjasHorariasDelCurso...");
        List<FranjaHorario> franjas = consultarFranjasHorariasDelCurso(1); // ASAE Grupo A
        System.out.println("   ✓ Total de franjas encontradas para ASAE Grupo A: " + franjas.size());

        // 6. Consultar franjas del nuevo curso creado
        System.out.println("\n6. Consultando franjas del nuevo curso creado...");
        franjas = consultarFranjasHorariasDelCurso(nuevoCurso1.getId());
        System.out.println("   ✓ Total de franjas para '" + nuevoCurso1.getNombre() + "': " + franjas.size());

        // 7. Consultar franjas del curso con múltiples docentes
        System.out.println("\n7. Consultando franjas del curso con múltiples docentes...");
        franjas = consultarFranjasHorariasDelCurso(nuevoCurso2.getId());
        System.out.println("   ✓ Total de franjas para '" + nuevoCurso2.getNombre() + "': " + franjas.size());

        // 8. Consultar franjas de otro curso existente
        System.out.println("\n8. Consultando franjas de Base de Datos (Curso ID: 3)...");
        franjas = consultarFranjasHorariasDelCurso(3);
        System.out.println("   ✓ Total de franjas encontradas para Base de Datos: " + franjas.size());

        // 9. Probar consultarFranjaByDocente con docentes existentes
        System.out.println("\n9. Probando método consultarFranjaByDocente...");
        System.out.println("   Consultando franjas de María González (ID: 1)...");
        consultarFranjaByDocente(1);

        System.out.println("\n10. Consultando franjas de Carlos Rodríguez (ID: 2)...");
        consultarFranjaByDocente(2);

        // 11. Consultar franjas del nuevo docente creado
        System.out.println("\n11. Consultando franjas del nuevo docente Roberto Fernández...");
        consultarFranjaByDocente(nuevoDocente.getId());

        // 12. Resumen final
        System.out.println("\n12. RESUMEN FINAL DEL SISTEMA:");
        System.out.println("    📚 Total de cursos: " + cursoRepository.count());
        System.out.println("    👨‍🏫 Total de docentes: " + docenteRepository.count());
        System.out.println("    ⏰ Total de franjas horarias: " + franjaHorarioRepository.count());
        System.out.println("    🏢 Total de espacios físicos: " + espacioFisicoRepository.count());
        System.out.println("    📖 Total de asignaturas: " + asignaturaRepository.count());

        // 13. Verificar que todos los cursos tienen sus relaciones cargadas (EAGER LOADING)
        System.out.println("\n13. Verificación final de EAGER LOADING:");
        List<Curso> todosCursos = cursoRepository.findAll();
        for (Curso curso : todosCursos) {
            System.out.println("    📘 " + curso.getNombre());
            System.out.println("       - Franjas: " + (curso.getFranjas() != null ? curso.getFranjas().size() : 0));
            System.out.println("       - Docentes: " + (curso.getDocentes() != null ? curso.getDocentes().size() : 0));
            System.out.println("       - Asignatura: " + (curso.getAsignatura() != null ? curso.getAsignatura().getNombre() : "Sin asignatura"));
        }

        System.out.println("\n🎉 === PRUEBAS COMPLETADAS EXITOSAMENTE ===");

    }




}
