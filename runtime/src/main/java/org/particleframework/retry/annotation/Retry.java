/*
 * Copyright 2018 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.particleframework.retry.annotation;


import org.particleframework.aop.Around;
import org.particleframework.context.annotation.AliasFor;
import org.particleframework.context.annotation.Type;
import org.particleframework.retry.DefaultRetryInterceptor;

import javax.validation.constraints.Digits;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * AOP Advice that can be applied to any method
 *
 * @author graemerocher
 * @since 1.0
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Around
@Type(DefaultRetryInterceptor.class)
public @interface Retry {
    /**
     * @return The exception types to include (defaults to all)
     */
    Class<? extends Throwable>[] includes() default {};

    /**
     * @return The exception types to exclude (defaults to none)
     */
    Class<? extends Throwable>[] excludes() default {};

    /**
     * @return The maximum number of retry attempts
     */
    @Digits(integer = 4, fraction = 0)
    String attempts() default "3";

    /**
     * @return The delay between retry attempts
     */
    String delay() default "1s";

    /**
     * @return The maximum overall delay
     */
    String maxDelay() default "";

    /**
     * @return The multiplier to use to calculate the delay
     */
    @Digits(integer = 2, fraction = 2)
    String multiplier() default "1.0";
}
