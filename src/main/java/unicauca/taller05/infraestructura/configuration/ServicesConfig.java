package unicauca.taller05.infraestructura.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unicauca.taller05.aplicacion.in.CUEspacioFisicoIn;
import unicauca.taller05.aplicacion.in.CUFranjaHorariaIn;
import unicauca.taller05.aplicacion.out.EspacioFormaterOut;
import unicauca.taller05.aplicacion.out.EspacioRepositoryOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaFormaterOut;
import unicauca.taller05.aplicacion.out.FranjaHorariaRepositoryOut;
import unicauca.taller05.dominio.casosUso.EspacioFisicoService;
import unicauca.taller05.dominio.casosUso.FranjaHorariaService;
import unicauca.taller05.aplicacion.in.CUDocenteIn;
import unicauca.taller05.aplicacion.out.DocenteFormaterOut;
import unicauca.taller05.aplicacion.out.DocenteRepositoryOut;
import unicauca.taller05.dominio.casosUso.DocenteService;

@Configuration
public class ServicesConfig {

    @Bean
    public CUFranjaHorariaIn configFranjaHorariaService(
            FranjaHorariaFormaterOut franjaFormater,
            FranjaHorariaRepositoryOut franjaRepository,
            ModelMapper modelMapper  
    ) {
        return new FranjaHorariaService(franjaFormater,franjaRepository, modelMapper);
    }

    @Bean
    public CUDocenteIn configDocenteService(
            DocenteFormaterOut docenteFormaterOut,
            DocenteRepositoryOut docenteRepositoryOut
    ) {
        return new DocenteService(docenteFormaterOut, docenteRepositoryOut);
    }

    @Bean
    public CUEspacioFisicoIn configEspacioFisicoService(
            EspacioFormaterOut espacioFormaterOut,
            EspacioRepositoryOut espacioRepository
    ) {
        return new EspacioFisicoService(espacioFormaterOut,espacioRepository);
    }
}
