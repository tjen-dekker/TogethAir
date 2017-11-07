package com.realdolmen.domain;

import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.Seat;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FlightSeatRelationTest {

    private Flight f;
    private Set<Seat> seats;

    public FlightSeatRelationTest(){
        f = new Flight();
        seats = new HashSet<>();

        Seat s1 = new Seat();
        Seat s2 = new Seat();
        Seat s3 = new Seat();


        s1.setLocation("21A");
        s2.setLocation("21B");
        s3.setLocation("21C");

        seats.add(s1);
        seats.add(s2);
        seats.add(s3);

    }

    @Test
    public void testFlightCreatesSeats(){
        f.setSeats(seats);

        assertEquals(seats.size(),f.getSeats().size());
        assertEquals(3,f.getSeats().size());
        for(Seat s : f.getSeats()){
            assertEquals(f,s.getFlight());
        }

    }

    @Test
    public void testChangingSeatsReferenceDoesntChangeFlightSeats(){
        f.setSeats(seats);
        int length = f.getSeats().size();
        seats.add(new Seat()); //changing reference shouldnt be able to change the flight
        assertNotEquals(seats.size(),length);

    }


    @Test
    public void testChangingFlightReferenceInSeatDoesntChangeFlight(){
        f.setSeats(seats);
        Seat s = f.getSeats().iterator().next();
        Flight n = s.getFlight();
        Flight anotherOne = new Flight();
        n = anotherOne;
        assertEquals(f,s.getFlight());
    }



}
