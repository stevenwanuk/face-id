package com.sven.exceptions;

public class BmaIdNotFoundException extends FacialIdApplicationException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BmaIdNotFoundException(final String message)
    {
        super(message);
    }

    public BmaIdNotFoundException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

}
