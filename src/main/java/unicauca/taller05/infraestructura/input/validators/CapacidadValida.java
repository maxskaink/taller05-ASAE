package unicauca.taller05.infraestructura.input.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE }) // Aplica al DTO completo
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CapacidadValidator.class)
public @interface CapacidadValida {
    String message() default "La capacidad del espacio físico no es suficiente para la matrícula estimada del curso";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}