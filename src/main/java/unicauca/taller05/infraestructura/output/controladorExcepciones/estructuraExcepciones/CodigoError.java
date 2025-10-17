package unicauca.taller05.infraestructura.output.controladorExcepciones.estructuraExcepciones;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CodigoError {

    // ==== ERRORES GENERALES ====
    ERROR_GENERICO("GC-0001", "Error genérico del sistema"),
    ENTIDAD_YA_EXISTE("GC-0002", "Entidad ya existente"),
    ENTIDAD_NO_ENCONTRADA("GC-0003", "Entidad no encontrada"),
    VIOLACION_REGLA_DE_NEGOCIO("GC-0004", "Violación de regla de negocio"),
    CREDENCIALES_INVALIDAS("GC-0005", "Credenciales inválidas"),
    USUARIO_DESHABILITADO("GC-0006", "Usuario deshabilitado"),
    OPERACION_INVALIDA("GC-0007", "Intento de realizar una operacion invalida"),
    PARAMETRO_INVALIDO("GC-0008", "Parámetro proporcionado inválido o nulo"),


    // ==== ERRORES DE FRANJA HORARIA ====
    CONFLICTO_HORARIO("FH-0001", "Conflicto entre franjas horarias");

    private final String codigo;
    private final String llaveMensaje;
}
