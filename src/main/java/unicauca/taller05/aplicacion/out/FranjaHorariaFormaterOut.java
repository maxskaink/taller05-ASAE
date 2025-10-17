package unicauca.taller05.aplicacion.out;

/**
 * Puerto de salida para formatear y propagar errores relacionados con las operaciones
 * de la entidad FranjaHoraria.
 */
public interface FranjaHorariaFormaterOut {

    void retornarErrorFranjaYaExiste(String mensaje);

    void retornarErrorEntidadNoExiste(String mensaje);

    void retornarErrorConflictoDeHorario(String mensaje);

    void retornarErrorEliminarFranja(String mensaje);

    void retornarErrorParametroInvalido(String mensaje);
}
