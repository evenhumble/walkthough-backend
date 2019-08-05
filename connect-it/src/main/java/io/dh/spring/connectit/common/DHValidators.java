package io.dh.spring.connectit.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.*;
import java.util.Objects;
import java.util.Set;

public class DHValidators {

    private final static Logger logger = LoggerFactory.getLogger(DHValidators.class);

    private DHValidators(){}

    public DHValidators verifyNotNull(Object o, String msg){
        Objects.requireNonNull(o,msg);
        return this;
    }

    public DHValidators verifyNotNull(Integer id){
        Objects.requireNonNull(id,"id should be not null!");
        return this;
    }

    public static DHValidators validator(){
        return new DHValidators();
    }


    public static Validator defaultValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    public static <T> void validateEntity(T instance){

        Set<ConstraintViolation<T>> violations = defaultValidator().validate(instance);
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<T> violation : violations) {
            logger.error(violation.getMessage());
            sb.append(violation.getMessage());
            sb.append(",");
        }

        throw  new ValidationException(sb.toString().substring(0,
                sb.toString().length()-1));

    }
}
