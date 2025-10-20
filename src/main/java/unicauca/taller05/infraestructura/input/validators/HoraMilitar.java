package unicauca.taller05.infraestructura.input.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HoraMilitarValidator.class)
public @interface HoraMilitar {
    String message() default "La hora debe estar en formato militar (00:00 a 23:59)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
