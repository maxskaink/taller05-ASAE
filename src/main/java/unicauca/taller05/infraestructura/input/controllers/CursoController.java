package unicauca.taller05.infraestructura.input.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import unicauca.taller05.aplicacion.in.CUCursosIn;
import unicauca.taller05.infraestructura.input.DTOPeticion.CursoDTOPeticion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CursoController {

    private final CUCursosIn cuCursosIn;
    private final ModelMapper modelMapper;

    @GetMapping("/asignatura/{nombre}")
    public List<CursoDTOPeticion> obtenerPorAsignatura(@PathVariable String nombre) {
        return cuCursosIn.obtenerCursoPorAsignatura(nombre).stream()
                .map(curso -> modelMapper.map(curso, CursoDTOPeticion.class))
                .toList();
    }
}

