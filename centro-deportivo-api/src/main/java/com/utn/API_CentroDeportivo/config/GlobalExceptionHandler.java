package com.utn.API_CentroDeportivo.config;

import com.utn.API_CentroDeportivo.model.dto.response.ErrorResponseDTO;
import com.utn.API_CentroDeportivo.model.exception.FieldAlreadyExistsException;
import com.utn.API_CentroDeportivo.model.exception.MemberAlreadyEnrolledException;
import com.utn.API_CentroDeportivo.model.exception.MemberNotFoundException;
import com.utn.API_CentroDeportivo.model.exception.SportActivityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UsernameNotFoundException ex, Locale locale) {
        Map<String, String> details = new HashMap<>();
        details.put("username", extractUsername(ex.getMessage()));
        details.put("reason", messageSource.getMessage("error.user.not.found.reason", null, locale));
        return buildErrorResponse(HttpStatus.NOT_FOUND,
                messageSource.getMessage("error.user.not.found", null, locale),
                details,
                "USER_NOT_FOUND");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                messageSource.getMessage("error.validation.failed", null, locale),
                errors,
                "VALIDATION_FAILED");
    }

    @ExceptionHandler(FieldAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleFieldAlreadyExistsException(FieldAlreadyExistsException ex, Locale locale) {
        Map<String, String> details = new HashMap<>();
        details.put("field", ex.getField());
        details.put("value", extractFieldValue(ex.getMessage()));
        details.put("reason", messageSource.getMessage("error.field.already.exists.reason", null, locale));
        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                messageSource.getMessage("error.field.already.exists", null, locale),
                details,
                "FIELD_ALREADY_EXISTS");
    }

    @ExceptionHandler(MemberAlreadyEnrolledException.class)
    public ResponseEntity<ErrorResponseDTO> handleMemberAlreadyEnrolledException(MemberAlreadyEnrolledException ex, Locale locale) {
        Map<String, String> details = new HashMap<>();
        details.put("memberId", extractMemberId(ex.getMessage()));
        details.put("reason", messageSource.getMessage("error.member.already.enrolled.reason", null, locale));
        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                messageSource.getMessage("error.member.already.enrolled", null, locale),
                details,
                "MEMBER_ALREADY_ENROLLED");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleMemberNotFoundException(MemberNotFoundException ex, Locale locale) {
        Map<String, String> details = new HashMap<>();
        details.put("memberId", extractMemberId(ex.getMessage()));
        details.put("reason", messageSource.getMessage("error.member.not.found.reason", null, locale));
        return buildErrorResponse(HttpStatus.NOT_FOUND,
                messageSource.getMessage("error.member.not.found", null, locale),
                details,
                "MEMBER_NOT_FOUND");
    }

    @ExceptionHandler(SportActivityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleSportActivityNotFoundException(SportActivityNotFoundException ex, Locale locale) {
        Map<String, String> details = new HashMap<>();
        details.put("activityId", extractActivityId(ex.getMessage()));
        details.put("reason", messageSource.getMessage("error.sport.activity.not.found.reason", null, locale));
        return buildErrorResponse(HttpStatus.NOT_FOUND,
                messageSource.getMessage("error.sport.activity.not.found", null, locale),
                details,
                "SPORT_ACTIVITY_NOT_FOUND");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, Locale locale) {
        // Sin logging, solo devolvemos una respuesta genérica
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                messageSource.getMessage("error.generic", null, locale),
                null,
                "GENERIC_ERROR");
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(HttpStatus status, String message, Map<String, String> details, String code) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .code(code)
                .message(message)
                .details(details != null ? details : new HashMap<>())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    private String extractFieldValue(String message) {
        return message != null && message.contains(": ") ? message.split(": ")[1].trim() : "unknown";
    }

    private String extractUsername(String message) {
        return message != null && message.contains(": ") ? message.split(": ")[1].trim() : "unknown";
    }

    private String extractMemberId(String message) {
        return message != null && message.contains(": ") ? message.split(": ")[1].trim() : "unknown";
    }

    private String extractActivityId(String message) {
        return message != null && message.contains(": ") ? message.split(": ")[1].trim() : "unknown";
    }
}