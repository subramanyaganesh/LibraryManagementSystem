package com.project.LibraryManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static <T> ResponseEntity<T> generateResponse(String message, HttpStatus status, Object responseObj,String classType) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put(classType, responseObj);

        return new ResponseEntity<>((T) map, status);
    }
}
