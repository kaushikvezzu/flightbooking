package com.example.flightbooking.exceptions;

public class RecordNotFoundException extends RuntimeException
{
    public RecordNotFoundException(String str)
    {
        super(str);
    }


}
