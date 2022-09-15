package com.austin.Oshop.exceptions;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 09/Aug/2022 - 12:06 PM
 * @project supportPortal
 */
public class EmailAlreadyExistException extends Exception{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
