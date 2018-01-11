package com.sven.exceptions;

public class StorageException extends FacialIdApplicationException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public StorageException(final String message) {
        super(message);
    }

    public StorageException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
