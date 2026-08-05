package com.ammarkanani.banking_app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ammarkanani.banking_app.dto.response.ApiResponse;
import com.ammarkanani.banking_app.dto.response.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {

                ApiResponse<String> response = new ApiResponse<>(
                                false,
                                ex.getMessage(),
                                null);

                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(response);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ValidationErrorResponse> handleValidationException(
                        MethodArgumentNotValidException ex) {

                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult()
                                .getFieldErrors()
                                .forEach(error -> errors.put(
                                                error.getField(),
                                                error.getDefaultMessage()));

                ValidationErrorResponse response = new ValidationErrorResponse(
                                false,
                                "Validation Failed",
                                errors);

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(response);
        }

        @ExceptionHandler(DuplicateResourceException.class)
        public ResponseEntity<ApiResponse<String>> handleDuplicateResourceException(
                        DuplicateResourceException ex) {

                ApiResponse<String> response = new ApiResponse<>(
                                false,
                                ex.getMessage(),
                                null);

                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(response);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(
                        ResourceNotFoundException ex) {

                ApiResponse<String> response = new ApiResponse<>(
                                false,
                                ex.getMessage(),
                                null);

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(response);
        }

        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<ApiResponse<Object>> handleBusinessException(
                        BusinessException ex) {

                ApiResponse<Object> response = new ApiResponse<>(

                                false,
                                ex.getMessage(),
                                null);

                return ResponseEntity.badRequest().body(response);
        }

}
