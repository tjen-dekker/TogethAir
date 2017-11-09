package com.realdolmen.togethair.Exceptions;

/**
 * Created by GWTBF10 on 9/11/2017.
 */
public class SeatAlreadyTakenException extends Exception {
	public SeatAlreadyTakenException() {}
	
	public SeatAlreadyTakenException(String message)
	{
		super(message);
	}
}
