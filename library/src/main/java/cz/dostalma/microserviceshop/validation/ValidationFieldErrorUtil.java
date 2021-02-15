package cz.dostalma.microserviceshop.validation;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationFieldErrorUtil {

    public ValidationFieldErrorUtil() {
    }

    /**
     * Add field errors to the binding result that have been added as object errors.
     * @param bindingResult BindingResult object with which to work
     */
    public void addFieldErrorsFromObjectErrors(BindingResult bindingResult) {
        List<ObjectError> newErrorsList = new ArrayList<>();

        for (ObjectError objectError : bindingResult.getAllErrors()) {
            if (!(objectError instanceof FieldError) && objectError.getArguments() != null) {
                for (Object object : objectError.getArguments()) {
                    if (!(object instanceof DefaultMessageSourceResolvable)) {
                        String field = object.toString();
                        FieldError newError = new FieldError(objectError.getObjectName(), field,
                                objectError.getDefaultMessage() != null ? objectError.getDefaultMessage() : "");
                        newErrorsList.add(newError);
                    }
                }
            }
        }

        newErrorsList.forEach(bindingResult::addError);
    }

}
