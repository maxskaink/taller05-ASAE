package unicauca.taller05.dominio.casosUso;

import unicauca.taller05.aplicacion.in.CUEspacioFisicoIn;
import unicauca.taller05.dominio.modelos.EspacioFisico;

import java.util.List;

public class EspacioFisicoService implements CUEspacioFisicoIn {
    @Override
    public List<EspacioFisico> buscarEspacioFisicoPorNombreYCapacacidad(String patron, int capacidadMin) {
        return List.of();
    }

    @Override
    public boolean actualizarEstado(Integer id, boolean estado) {
        return false;
    }

    @Override
    public List<EspacioFisico> listarEspaciosFisicos() {
        return List.of();
    }
}
