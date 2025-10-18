package unicauca.taller05.infraestructura.input.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unicauca.taller05.aplicacion.in.CUFranjaHorariaIn;
import org.modelmapper.ModelMapper;
import unicauca.taller05.infraestructura.input.DTORespuesta.FranjaHorariaDTORespuesta;

import java.util.List;

@RestController
@RequestMapping("/api/franjas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FranjaHorariaController {

    private final CUFranjaHorariaIn cuFranjaHorariaIn;
    private final ModelMapper modelMapper;

    @GetMapping("/{idCurso}")
    public List<FranjaHorariaDTORespuesta> obtenerPorIdCursoJPQL(
            @PathVariable Integer idCurso
    ){
        return cuFranjaHorariaIn.obtenerFranjaPorIdCursoJPQL(idCurso).stream()
                .map(franja -> modelMapper.map(franja, FranjaHorariaDTORespuesta.class)).toList();
    }

    @GetMapping("/curso/{idCurso}")
    public List<FranjaHorariaDTORespuesta> obtenerPorIdCurso(
            @PathVariable Integer idCurso
    ){
        return cuFranjaHorariaIn.obtenerFranjasPorIdCurso(idCurso).stream()
                .map(franja -> modelMapper.map(franja, FranjaHorariaDTORespuesta.class)).toList();
    }

}