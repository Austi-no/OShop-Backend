package com.austin.Oshop.utils;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.http.*;

import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 08/Aug/2022 - 1:23 AM
 * @project supportPortal
 */
@NoArgsConstructor
@Data
public class HttpResponse {

    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private Date timestamp;

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
        this.timestamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
}
