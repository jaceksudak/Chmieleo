package pl.jaceksudak.chmieleo.excavator.exceptions;

public class ExcavatorActionException extends RuntimeException {

    public ExcavatorActionException(Exception e) {
        super(e.getMessage(), e);
    }

    public ExcavatorActionException(Exception e, String message) {
        super(message, e);
    }

    public ExcavatorActionException(String message) {
        super(message);
    }
}
