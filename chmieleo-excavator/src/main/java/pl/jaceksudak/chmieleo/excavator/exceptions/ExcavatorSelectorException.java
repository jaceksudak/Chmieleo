package pl.jaceksudak.chmieleo.excavator.exceptions;

public class ExcavatorSelectorException extends RuntimeException {

    public ExcavatorSelectorException(Exception e) {
        super(e.getMessage(), e);
    }

    public ExcavatorSelectorException(Exception e, String message) {
        super(message, e);
    }

    public ExcavatorSelectorException(String message) {
        super(message);
    }
}
