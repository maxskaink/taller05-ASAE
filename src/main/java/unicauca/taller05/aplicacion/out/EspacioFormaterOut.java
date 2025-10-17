package unicauca.taller05.aplicacion.out;

public interface EspacioFormaterOut {
    /**
     * Ocurre cuando se intenta crear un espacio físico que ya existe.
     */
    void retornarErrorEspacioYaExiste(String mensaje);

    /**
     * Ocurre cuando no se encuentra el espacio físico solicitado.
     */
    void retornarErrorEspacioNoEncontrado(String mensaje);

    /**
     * Ocurre cuando la capacidad proporcionada es inválida.
     */
    void retornarErrorReglaNegocio(String mensaje);

    /**
     * Ocurre cuando se proporcionan parámetros inválidos o nulos.
     */
    void retornarErrorParametroInvalido(String mensaje);
}