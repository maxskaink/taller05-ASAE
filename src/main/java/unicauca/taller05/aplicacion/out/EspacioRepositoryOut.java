package unicauca.taller05.aplicacion.out;

import unicauca.taller05.dominio.modelos.EspacioFisico;
import java.util.List;

public interface EspacioRepositoryOut {

    /**
     * lista espacios físicos, ordenado ascendientemente por el campo nombre,
     * que comiencen por un patrón de búsqueda, ignorando mayúsculas y minúsculas
     * y que la capacidad sea mayor o igual que un parámetro de búsqueda.
     * Punto 1.1 del taller.
     * @param nombre patron de busqueda
     * @param capacidadMinima capacidad minima de los espacios a buscar
     * @return Espacios fisicos que concuerden con lo necesitado.
     */
    List<EspacioFisico> listarEspacioFisicoPorNombreYCapacidad(String nombre, int capacidadMinima);

    /**
     * Actualiza el estado de un espacio fisico a activo o inactivo
     * Se debe realizar con SQL nativo
     * Punto 3.2 del taller.
     * @param id id del espacio fisico a actualizar
     * @return retorna true si se actualizo el estado correctamente, false en caso contrario
     */
    boolean actualizarEstado(Integer id);

    /**
     * Lista todos los espacios fisicos
     * Punto 6.5 del taller.
     * @return lista de espacios fisicos
     */
    List<EspacioFisico> listarEspaciosFisicos();

}
