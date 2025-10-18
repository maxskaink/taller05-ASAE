package unicauca.taller05.aplicacion.out;


import unicauca.taller05.dominio.modelos.Docente;

public interface DocenteRepositoryOut {

    /**
     * Crea un docente en la base de datos
     * Punto 6.2 del taller.
     * @param docenteACrear docente a crear
     * @return docente creado
     */
    Docente crearDocente(Docente docenteACrear);

    /**
     * Verifica si existe un docente con el correo dado.
     * @param correo correo a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existePorCorreo(String correo);
}
