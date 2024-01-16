package com.example.flightbooking.api;

import com.example.flightbooking.exceptions.DataAccessException;
import com.example.flightbooking.exceptions.NoRecordFoundException;
import com.example.flightbooking.exceptions.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandlerAdvice
{
    private Logger log;

    public ApplicationExceptionHandlerAdvice() {
        this.log = LoggerFactory.getLogger(ApplicationExceptionHandlerAdvice.class);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFound(RecordNotFoundException e)
    {
        log.warn(e.getMessage());
        return ResponseEntity.status(404).body(Map.of("Bad Request", e.getMessage()));
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<?> handleNoRecordsFound(NoRecordFoundException e)
    {
        log.warn(e.getMessage());
        return ResponseEntity.status(404).body(Map.of("Result", e.getMessage()));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(SQLException e)
    {
        log.warn(e.getMessage());
        return ResponseEntity.status(404).body(Map.of("Recheck Required", e.getMessage()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException e)
    {
        log.warn(e.getMessage());
        return ResponseEntity.status(404).body(Map.of("Error", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e)
    {
        log.warn(e.getMessage());
        return ResponseEntity.status(404).body(Map.of("Argument Error", e.getMessage()));
    }

}
