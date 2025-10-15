package unicauca.taller05.dominio.manejadores;
import java.util.Optional;

public interface IManejador<T> {
    Optional<T> manejar(T fj);
}
