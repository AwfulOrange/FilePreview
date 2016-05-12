package cn.edu.zucc.chenxg.model.exceptions;

import java.util.ArrayList;
import java.util.List;

public class PreexistingEntityException extends Exception {
    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}

