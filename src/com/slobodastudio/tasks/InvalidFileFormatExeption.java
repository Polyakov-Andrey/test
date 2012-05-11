
package com.slobodastudio.tasks;

import java.io.IOException;
import java.io.NotSerializableException;

/**
 * Thrown to indicate that a method has been passed an illegal or inappropriate
 * format file.
 *
 * @author Andrey Polyakov
 * @version 1.0
 * @serial exclude
 */
public class InvalidFileFormatExeption extends IOException {

    /**
     * Constructs an InvalidFileFormatExeption with the specified cause.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).
     */
    public InvalidFileFormatExeption(Throwable cause) {
        super(cause == null ? null : cause.toString());
        this.initCause(cause);
    }

    /**
     * Constructs an inappropriate with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidFileFormatExeption(String message) {
        super(message);
    }

    /**
     * Throws NotSerializableException, since InvalidFileFormatExeption
     * objects are not intended to be serializable.
     */
    private void writeObject(java.io.ObjectOutputStream out)
            throws NotSerializableException {
        throw new NotSerializableException("Not serializable.");
    }

    /**
     * Throws NotSerializableException, since InvalidFileFormatExeption
     * objects are not intended to be serializable.
     */
    private void readObject(java.io.ObjectInputStream in)
            throws NotSerializableException {
        throw new NotSerializableException("Not serializable.");
    }
}

