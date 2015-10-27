/*
 * Copyright : (C) 2012 Atos
 *
 * The program(s) herein may be used and/or copied only with the
 * written permission of Atos or in accordance with the terms
 * and conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */
package edu.sample.context;

import org.springframework.context.ApplicationContext;

/**
 * 
 * This class provides application-wide access to the Spring ApplicationContext.
 * 
 * The ApplicationContext is injected by the class "ApplicationContextProvider".
 * 
 * @author Atos
 */
public final class SpringApplicationContext {

    /** The ctx. */
    private static ApplicationContext ctx;

    /**
     * Injected from the class "ApplicationContextProvider" which is
     * automatically
     * 
     * loaded during Spring-Initialization.
     * 
     * @param applicationContext
     *            the new application context
     */
    protected static void setApplicationContext(final ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    /**
     * Get access to the Spring ApplicationContext from everywhere in your
     * Application.
     * 
     * @return the application context
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
}
