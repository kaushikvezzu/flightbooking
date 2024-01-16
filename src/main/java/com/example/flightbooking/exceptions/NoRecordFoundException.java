package com.example.flightbooking.exceptions;

public class NoRecordFoundException extends RuntimeException
{
    public NoRecordFoundException(String s)
    {
        super(s);
    }
}
