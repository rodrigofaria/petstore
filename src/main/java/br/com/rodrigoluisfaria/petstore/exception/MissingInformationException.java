package br.com.rodrigoluisfaria.petstore.exception;

public class MissingInformationException extends RuntimeException {

    public MissingInformationException(Class clazz, String field) {
        super(clazz.getSimpleName() + " are missing the field: " + field);
    }
}
