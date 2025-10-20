package unicauca.taller05.infraestructura.input.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import unicauca.taller05.aplicacion.in.CUDocenteIn;
import unicauca.taller05.dominio.modelos.Docente;
import unicauca.taller05.infraestructura.input.DTOPeticion.DocenteDTOPeticion;
import unicauca.taller05.infraestructura.input.DTORespuesta.DocenteDTORespuesta;

@RestController
@RequestMapping("/api/docente")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DocenteController {
    private final CUDocenteIn cuDocenteIn;
    private final ModelMapper modelMapper;

    @PostMapping("")
    public DocenteDTORespuesta crearDocente(@RequestBody DocenteDTOPeticion docenteDTOPeticion) {
        Docente docente = modelMapper.map(docenteDTOPeticion, Docente.class);
        Docente docenteCreado = cuDocenteIn.crearDocente(docente);

        return modelMapper.map(docenteCreado, DocenteDTORespuesta.class);
    }

}
