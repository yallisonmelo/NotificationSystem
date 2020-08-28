package br.com.yfsmsystem.notificationsystem.exception.handler;

import br.com.yfsmsystem.notificationsystem.exception.NotificationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class NotificationHandlerExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotificationNotFoundException.class)
    public final ResponseEntity<Object> notFoundException(NotificationNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
