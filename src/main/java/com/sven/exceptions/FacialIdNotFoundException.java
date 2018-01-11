package com.sven.exceptions;

public class FacialIdNotFoundException extends FacialIdApplicationException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public FacialIdNotFoundException(final String message)
    {
        super(message);
    }

    public FacialIdNotFoundException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

}
