package unicauca.taller05.infraestructura.output.persistencia.repositorios;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import unicauca.taller05.aplicacion.out.DocenteRepositoryOut;
import unicauca.taller05.dominio.modelos.Docente;
import unicauca.taller05.infraestructura.output.persistencia.entidades.DocenteEntity;
import unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA.DocenteRepository;
import unicauca.taller05.infraestructura.output.persistencia.repositoriosJPA.OficinaRepository;
import unicauca.taller05.infraestructura.output.persistencia.entidades.OficinaEntity;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DocenteRepositoryAdapter implements DocenteRepositoryOut {

    private final DocenteRepository docenteRepositoryJPA;
    private final ModelMapper modelMapper;
    private final OficinaRepository oficinaRepository;

    @Override
    public Docente crearDocente(Docente docenteACrear) {
        // Map domain model to entity
        DocenteEntity entity = modelMapper.map(docenteACrear, DocenteEntity.class);

        // If the docente has an oficina with a nombre, try to reuse existing OficinaEntity
        if (entity.getOficina() != null && entity.getOficina().getNombre() != null) {
            String nombreOficina = entity.getOficina().getNombre();
            Optional<OficinaEntity> oficinaExistente = oficinaRepository.findByNombre(nombreOficina);
            oficinaExistente.ifPresent(entity::setOficina);
        }

        DocenteEntity saved = docenteRepositoryJPA.save(entity);
        return modelMapper.map(saved, Docente.class);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return docenteRepositoryJPA.existsByCorreo(correo);
    }
}
