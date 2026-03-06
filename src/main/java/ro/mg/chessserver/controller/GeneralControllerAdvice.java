package ro.mg.chessserver.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.sqlite.SQLiteException;


@RestControllerAdvice
public class GeneralControllerAdvice {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(exception = UsernameNotFoundException.class)
    public Map<String, String> handleUsernamePasswordNotFound(UsernameNotFoundException exception) throws IOException {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());
        return errorResponse;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(SQLiteException.class)
    public Map<String, String> handleDuplicateUsernameOrEmail(SQLiteException exception) throws IOException {
        Map<String, String> errorResponse = new HashMap<>();
        String message = exception.getMessage();
        errorResponse.put("error", message.substring(message.indexOf("(") + 1, message.indexOf(")")));
        return errorResponse;
    }
}
