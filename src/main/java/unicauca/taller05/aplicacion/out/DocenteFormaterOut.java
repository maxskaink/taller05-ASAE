package unicauca.taller05.aplicacion.out;

public interface DocenteFormaterOut {
    void retornarErrorDocenteYaExiste(String mensaje);

    void retornarErrorEntidadNoExiste(String mensaje);

    void retornarErrorReglaDeNegocio(String mensaje);

    void retornarErrorParametroInvalido(String mensaje);
}
