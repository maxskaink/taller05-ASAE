package unicauca.taller05.aplicacion.out;

public interface CursoFormaterOut {

    void retornarErrorCursoNoEncontrado(String mensaje);

    void retornarErrorReglaNegocio(String mensaje);

    void retornarErrorCursoYaExiste(String mensaje);

    void retornarErrorOperacionInvalida(String mensaje);

    void retornarErrorParametroInvalido(String mensaje);
}
