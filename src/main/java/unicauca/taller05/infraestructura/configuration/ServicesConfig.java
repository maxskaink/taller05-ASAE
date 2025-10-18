package unicauca.taller05.infraestructura.configuration;

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

@Configuration
public class ServicesConfig {

    @Bean
    public CUFranjaHorariaIn configFranjaHorariaService(
            FranjaHorariaFormaterOut franjaFormater,
            FranjaHorariaRepositoryOut franjaRepository
    ) {
        return new FranjaHorariaService(franjaFormater,franjaRepository);
    }

    @Bean
    public CUEspacioFisicoIn configEspacioFisicoService(
            EspacioFormaterOut espacioFormaterOut,
            EspacioRepositoryOut espacioRepository
    ) {
        return new EspacioFisicoService(espacioFormaterOut,espacioRepository);
    }
}
