package unicauca.taller05.aplicacion.in;

public interface CUEspacioFisicoIn {

    /**
     * Busca un espacio fisico por patron en el nombre y capacidad minima
     * Punto 1.1 del taller
     * @param patron patron a buscar en el nombre
     * @param capacidadMin capacidad minima del espacio fisico
     */
    void buscarEspacioFisicoPorNombreYCapacacidad(String patron, int capacidadMin);

    /**
     * Actualiza el estado de un espacio fisico a activo o inactivo
     * Punto 3.2 del taller
     * @param id id del espacio fisico a actualizar
     * @param estado nuevo estado del espacio fisico
     * @return retorna true si se actualizo el estado correctamente, false en caso contrario
     */
    boolean actualizarEstado(Integer id, boolean estado);

    /**
     * Lista los espacios fisicos
     * Punto 6.5 del taller
     */
    void listarEspaciosFisicos();
}
