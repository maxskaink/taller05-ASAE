package unicauca.taller05.infraestructura.input.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unicauca.taller05.aplicacion.out.CursoRepositoryOut;
import unicauca.taller05.aplicacion.out.EspacioRepositoryOut;
import unicauca.taller05.infraestructura.input.DTOPeticion.FranjaHorariaDTOPeticion;

@Component
public class CapacidadValidator implements ConstraintValidator<CapacidadValida, FranjaHorariaDTOPeticion> {

    private final EspacioRepositoryOut espacioFisicoRepository;
    private final CursoRepositoryOut cursoRepository;

    @Autowired
    public CapacidadValidator(EspacioRepositoryOut espacioFisicoRepository, CursoRepositoryOut cursoRepository) {
        this.espacioFisicoRepository = espacioFisicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public boolean isValid(FranjaHorariaDTOPeticion dto, ConstraintValidatorContext context) {

        var espacioOptional = espacioFisicoRepository.obtenerEspacioFisicoPorId(dto.getIdEspacioFisico());

        if(espacioOptional.isEmpty()){
            return false;
        }

        var espacio = espacioOptional.get();

        var cursoOptional = cursoRepository.obtenerCursoPorId(dto.getIdCurso());

        if(cursoOptional.isEmpty()){
            return false;
        }

        var curso = cursoOptional.get();

        return espacio.getCapacidad() >= curso.getCapacidad();

    }
}
