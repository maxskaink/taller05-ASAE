package unicauca.taller05.aplicacion.in;

import unicauca.taller05.dominio.modelos.EspacioFisico;

import java.util.List;

public interface CUEspacioFisicoIn {

    /**
     * Busca un espacio fisico por patron en el nombre y capacidad minima
     * Punto 1.1 del taller
     * @param patron patron a buscar en el nombre
     * @param capacidadMin capacidad minima del espacio fisico
     */
    List<EspacioFisico> buscarEspacioFisicoPorNombreYCapacacidad(String patron, int capacidadMin);

    /**
     * Actualiza el estado de un espacio fisico a activo o inactivo
     * Punto 3.2 del taller
     * @param id id del espacio fisico a actualizar
     * @return retorna true si se actualizo el estado correctamente, false en caso contrario
     */
    boolean actualizarEstado(Integer id);

    /**
     * Lista los espacios fisicos
     * Punto 6.5 del taller
     */
    List<EspacioFisico> listarEspaciosFisicos();
}
