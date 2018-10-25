package pl.jaceksudak.chmieleo.excavator.exceptions;

public class ExcavatorPropertiesApplierException extends RuntimeException {

    public ExcavatorPropertiesApplierException(Exception e) {
        super(e.getMessage(), e);
    }

    public ExcavatorPropertiesApplierException(Exception e, String message) {
        super(message, e);
    }

    public ExcavatorPropertiesApplierException(String message) {
        super(message);
    }
}
