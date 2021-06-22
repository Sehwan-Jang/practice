package playground.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import playground.dto.ErrorDto;

@RestControllerAdvice
public class SimpleControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentExceptionError(IllegalArgumentException e) {
        logger.error("cause is : {} {}", e.getMessage(), this.getClass().getName());

        return ResponseEntity.badRequest()
                .body(new ErrorDto(e.getMessage()));
    }

}
