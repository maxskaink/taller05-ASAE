package unicauca.taller05.infraestructura.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unicauca.taller05.dominio.modelos.FranjaHoraria;
import unicauca.taller05.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "franjaMapperWithoutCourse")
    public ModelMapper modelMapperWithoutCourse() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setDeepCopyEnabled(false)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setImplicitMappingEnabled(false) // clave para evitar que se creen los mapeos implícitos antes de tu skip
                .setPreferNestedProperties(false); // opcional, previene recursión

        TypeMap<FranjaHorariaEntity, FranjaHoraria> typeMap =
                modelMapper.createTypeMap(FranjaHorariaEntity.class, FranjaHoraria.class);

        // aplicas los skips antes de habilitar los mapeos implícitos
        typeMap.addMappings(mapper -> mapper.skip(FranjaHoraria::setCurso));

        // luego, si quieres permitir los demás mapeos automáticos:
        typeMap.implicitMappings();

        // puedes volver a activar los implícitos globalmente si lo deseas
        modelMapper.getConfiguration().setImplicitMappingEnabled(true);

        return modelMapper;
    }
}
