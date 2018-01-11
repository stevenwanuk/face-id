package com.sven.exceptions;

public class FacialIdApplicationException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public FacialIdApplicationException(final String message) {
        super(message);
    }

    public FacialIdApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
