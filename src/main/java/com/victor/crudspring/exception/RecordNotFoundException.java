package com.victor.crudspring.exception;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Registro não encotrado com o id: " + id);
    }
}
