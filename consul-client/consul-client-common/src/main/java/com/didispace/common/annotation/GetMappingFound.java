package com.didispace.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * @author fangzheng
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(
        consumes = {"application/json; charset=UTF-8"},
        produces = {"application/json; charset=UTF-8"},
        method = {RequestMethod.GET}
)
public @interface GetMappingFound {
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] value() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] path() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] params() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] headers() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] consumes() default {};
}
