package share.base.exceptions;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
public @interface Description {
    String description();
    int code();
}
