package com.sparta.testing.browser_annotation;

/**
 * Enum representing the different types of browsers that can be used for testing.
 * This enum is used in conjunction with the {@link com.sparta.testing.browser_annotation.Browser} annotation
 * to specify the desired browser for a test.
 */
public enum BrowserType {
    /** Google Chrome browser. */
    CHROME,

    /** Mozilla Firefox browser. */
    FIREFOX,

    /** Microsoft Edge browser. */
    EDGE,

    /** Apple Safari browser. */
    SAFARI,

    /** Opera browser. */
    OPERA,

    /** The default browser for the operating system, determined at runtime. */
    DEFAULT
}
