package com.example.a1449877.notelist.model;

/**
 * Exception class for database errors.
 *
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class DatabaseException extends Throwable {
    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(Exception e) {
        super(e);
    }
}
