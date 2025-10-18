package unicauca.taller05.infraestructura.input.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unicauca.taller05.aplicacion.in.CUEspacioFisicoIn;
import org.modelmapper.ModelMapper;
import unicauca.taller05.infraestructura.input.DTORespuesta.EspacioFisicoDTORespuesta;

import java.util.List;

@RestController
@RequestMapping("/api/espacio")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EspacioFisicoController {

    private final CUEspacioFisicoIn cuEspacioHorariaIn;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public List<EspacioFisicoDTORespuesta> listEspaciosFisicos(){
        return cuEspacioHorariaIn.listarEspaciosFisicos().stream()
                .map(espacio -> modelMapper.map(espacio, EspacioFisicoDTORespuesta.class)).toList();
    }

    @GetMapping("/patron/{patron}")
    public List<EspacioFisicoDTORespuesta> obtenerEspacioFisicoPorPatronYCapacidad(
            @PathVariable String patron,
            @RequestParam(name = "capacidad", defaultValue = "0") int capacidadMinima
    ) {
        return cuEspacioHorariaIn.buscarEspacioFisicoPorNombreYCapacacidad(patron,capacidadMinima).stream()
                .map(espacio -> modelMapper.map(espacio, EspacioFisicoDTORespuesta.class)).toList();
    }

}