package unicauca.taller05.dominio.casosUso;

import lombok.AllArgsConstructor;
import unicauca.taller05.aplicacion.in.CUEspacioFisicoIn;
import unicauca.taller05.aplicacion.out.EspacioFormaterOut;
import unicauca.taller05.aplicacion.out.EspacioRepositoryOut;
import unicauca.taller05.dominio.modelos.EspacioFisico;

import java.util.List;

@AllArgsConstructor
public class EspacioFisicoService implements CUEspacioFisicoIn {

    private final EspacioFormaterOut espacioFormaterOut;
    private final EspacioRepositoryOut espacioRepository;

    @Override
    public List<EspacioFisico> buscarEspacioFisicoPorNombreYCapacacidad(String patron, int capacidadMin) {

        if(patron == null){
            espacioFormaterOut.retornarErrorParametroInvalido("EL patron no puede ser nulo");
            return List.of();
        }
        if(capacidadMin < 0){
            espacioFormaterOut.retornarErrorParametroInvalido("La capacidad minima no puede ser negativa");
            return List.of();
        }

        var espaciosEncontrados = espacioRepository.listarEspacioFisicoPorNombreYCapacidad( patron, capacidadMin);
        if(espaciosEncontrados.isEmpty()){
            espacioFormaterOut.retornarErrorEspacioNoEncontrado("No se ha encontrado espacios con el patron " + patron + " y la capacidad minima " + capacidadMin);
        }
        return espaciosEncontrados;
    }

    /**
     * Actualiza el estado de un espacio fisico
     * @param id Id del espacio fisico
     * @return true si se actualizo el estado, false en caso contrario
     */
    @Override
    public boolean actualizarEstado(Integer id) {
        if(id == null || id <= 0){
            espacioFormaterOut.retornarErrorParametroInvalido("El id no puede ser nulo o menor o igual a cero");
            return false;
        }
        if(!espacioRepository.actualizarEstado(id) ){
            espacioFormaterOut.retornarErrorEspacioNoEncontrado("No se pudo actualizar el estado del espacio con id: " + id);
        }   
        return  true;
    }

    @Override
    public List<EspacioFisico> listarEspaciosFisicos() {

        var espacios = espacioRepository.listarEspaciosFisicos();

        if(espacios.isEmpty()){
            espacioFormaterOut.retornarErrorEspacioNoEncontrado("No se han encontrado espacios fisicos");
        }
        return espacios;
    }
}
