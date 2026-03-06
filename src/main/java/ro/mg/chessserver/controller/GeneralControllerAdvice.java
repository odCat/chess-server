package ro.mg.chessserver.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GeneralControllerAdvice {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(exception = UsernameNotFoundException.class)
    public void handleUsernamePasswordNotFound(HttpServletResponse response, UsernameNotFoundException exception) throws IOException {
        response.getWriter().write(exception.getMessage());
    }
}
