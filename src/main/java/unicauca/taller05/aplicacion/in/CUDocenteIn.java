package unicauca.taller05.aplicacion.in;

import unicauca.taller05.dominio.modelos.Docente;

public interface CUDocenteIn {

    /**
     * Crea el docente asociandolo a una oficina de trabajo, guardando tambien la oficina
     * Punto 6.2 del taller
     * @param docenteACrear docente a crear
     */
    Docente crearDocente(Docente docenteACrear);
}
