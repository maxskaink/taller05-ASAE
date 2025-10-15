package unicauca.taller05.dominio.manejadores;

import java.util.Optional;

public abstract class ManejadorBase<T> implements IManejador<T> {

    protected IManejador<T> next;

    public ManejadorBase<T> setSiguiente(IManejador<T> next){
        this.next = next;
        return (ManejadorBase<T>) next;
    }

    protected Optional<T> next(T ctx) {
        if (next != null)
            return next.manejar(ctx);
        return Optional.of(ctx);
    }

}
