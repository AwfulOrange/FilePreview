package cn.edu.zucc.chenxg.model.exceptions;

import java.util.ArrayList;
import java.util.List;

public class NonexistentEntityException extends Exception {
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}


