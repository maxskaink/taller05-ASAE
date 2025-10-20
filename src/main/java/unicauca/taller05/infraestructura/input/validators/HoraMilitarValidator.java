package unicauca.taller05.infraestructura.input.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HoraMilitarValidator implements ConstraintValidator<HoraMilitar, String> {
    private static final String REGEX_HORA = "^([01]\\d|2[0-3]):[0-5]\\d$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.matches(REGEX_HORA);
    }
}