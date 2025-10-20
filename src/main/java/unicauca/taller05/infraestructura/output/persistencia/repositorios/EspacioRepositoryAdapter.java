package unicauca.taller05.infraestructura.output.persistencia.repositorios;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import unicauca.taller05.aplicacion.out.EspacioRepositoryOut;
import unicauca.taller05.dominio.modelos.EspacioFisico;
import unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA.EspacioFisicoRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EspacioRepositoryAdapter implements EspacioRepositoryOut {

    private final EspacioFisicoRepository espacioRepositoryJPA;
    private final ModelMapper modelMapper;

    @Override
    public List<EspacioFisico> listarEspacioFisicoPorNombreYCapacidad(String nombre, int capacidadMinima) {
        return espacioRepositoryJPA.findByNombreStartingWithIgnoreCaseAndCapacidadGreaterThanEqualOrderByNombreAsc(
                nombre,
                capacidadMinima
        ).stream().map(entity -> modelMapper.map(entity, EspacioFisico.class)).toList();
    }

    @Override
    public boolean actualizarEstado(Integer id) {
        if(espacioRepositoryJPA.switchEstado(id) > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<EspacioFisico> listarEspaciosFisicos() {
        return espacioRepositoryJPA.findAll().stream().map(entity -> modelMapper.map(entity, EspacioFisico.class)).toList();
    }

    @Override
    public boolean existsById(Integer id) {
        return espacioRepositoryJPA.existsById(id);
    }
    
}
