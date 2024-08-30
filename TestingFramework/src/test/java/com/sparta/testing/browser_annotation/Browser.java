package com.sparta.testing.browser_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify the browser type to be used for testing.
 * This annotation can be applied to a test class to define the browser
 * that the WebDriver will use during test execution.
 *
 * <p>If no value is specified, the default browser type will be used,
 * which is determined based on the operating system's default browser settings.</p>
 *
 * @value The browser type to be used for testing, defaulting to {@link BrowserType#DEFAULT}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Browser {
    BrowserType value() default BrowserType.DEFAULT;
}
