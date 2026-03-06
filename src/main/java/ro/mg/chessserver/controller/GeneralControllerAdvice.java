package ro.mg.chessserver.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GeneralControllerAdvice {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(exception = UsernameNotFoundException.class)
    public Map<String, String> handleUsernamePasswordNotFound(HttpServletResponse response, UsernameNotFoundException exception) throws IOException {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());
        return errorResponse;
    }
}
