package hu.elte.webtechnologiak.realestatedocumenthandling.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class UploadExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleError(MaxUploadSizeExceededException e) {
        return ResponseEntity.badRequest().body("File size exceeds 15 Megabytes!");
    }
}
