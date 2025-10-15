package unicauca.taller05.aplicacion.out;

import unicauca.taller05.dominio.modelos.EspacioFisico;

import java.util.List;

public interface EspacioRepositoryOut {

    /**
     * lista espacios físicos, ordenado ascendientemente por el campo nombre,
     * que comiencen por un patrón de búsqueda, ignorando mayúsculas y minúsculas
     * y que la capacidad sea mayor o igual que un parámetro de búsqueda.
     * @param nombre patron de busqueda
     * @param capacidadMinima capacidad minima de los espacios a buscar
     * @return Espacios fisicos que concuerden con lo necesitado.
     */
    List<EspacioFisico> listarEspacioFisicoPorNombreYCapacidad(String nombre, int capacidadMinima);
}
