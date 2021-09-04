package com.monese.bank.web.advice;

import com.monese.bank.exception.AccountNotFoundException;
import com.monese.bank.exception.ValidationException;
import com.monese.bank.web.model.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, ServletWebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorInfo errorInfo = createErrorInfo(request, ex, status);

        log.warn("Invalid input: {} [{}]", errorInfo.getMessage(), errorInfo.getPath(), ex);

        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex, ServletWebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorInfo errorInfo = createErrorInfo(request, ex, status);

        log.warn("Account not found: {} [{}]", errorInfo.getMessage(), errorInfo.getPath(), ex);

        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), status, request);
    }

    private ErrorInfo createErrorInfo(RequestAttributes request, Throwable ex, HttpStatus status) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setStatus(status.value());
        errorInfo.setError(status.getReasonPhrase());
        errorInfo.setMessage(findRootCause(ex).getMessage());
        errorInfo.setPath(getCurrentPath(request));
        errorInfo.setTimestamp(LocalDateTime.now().toString());

        return errorInfo;
    }

    private String getCurrentPath(RequestAttributes request) {
        if (request instanceof ServletWebRequest) {
            return ((ServletWebRequest)request).getRequest().getRequestURI();
        } else {
            return ((WebRequest)request).getContextPath();
        }
    }

    public Throwable findRootCause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }
}
