package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.domain.*;
import com.realdolmen.togethair.repositories.SeatRepository;
import com.realdolmen.togethair.services.FlightServiceBean;
import com.realdolmen.togethair.services.SearchServiceBean;
import com.realdolmen.togethair.services.UserServiceBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


@SessionScoped
@ManagedBean
public class FlightBean implements Serializable {


    @Inject
    private FlightServiceBean flightServiceBean;

    @Inject
    private UserServiceBean userServiceBean;

    @Inject
    private SearchServiceBean searchService;
    
    @Size(min=3, max=85, message = "city where the flight is flying <b>from</b> is empty or too long")
    @Pattern(regexp = "[a-zA-Z]+(?:[ '-][a-zA-Z]+)*", message = "city where the flight is flying <b>from</b> contains illegal characters")
    private String from;
    
    @Size(min=3, max=85, message = "city where the flight is flying <b>to</b> is empty or too long")
    @Pattern(regexp = "[a-zA-Z]+(?:[ '-][a-zA-Z]+)*", message = "city where the flight is flying <b>to</b> contains illegal characters")
    private String to;

    private Flight flight;

    private List<String> allAirports = new ArrayList<>();

    private String firstLetter;


    private int rowStart;

    private int rowEnd;

    private Float baseEconomy;

    private Float  baseBusiness;

    private Float  baseFirstClass;

    public FlightBean() {
    }

    @PostConstruct
    public void init() {
        flight = new Flight();
        List<String> airportNames = new ArrayList<>();
        List<Airport> allAirports = searchService.findAllAirports();
        for (Airport allAirport : allAirports) {
            airportNames.add(allAirport.getName());
        }

        setAllAirports(airportNames);
    }

    @RequiresRoles("partner:create")
    public void addFlight() {
        try{
            Airport toAirport = flightServiceBean.findAirportByName(to);
            flight.setTo(toAirport);
            Airport fromAirport = flightServiceBean.findAirportByName(from);
            flight.setFrom(fromAirport);
            flight.setFlightCompany(userServiceBean.usersCompany(SecurityUtils.getSubject().getPrincipal().toString()));
            initializeSeats();
            flightServiceBean.Create(flight);
            facesMessage("Flight has been created successfully");
        }
        catch (javax.persistence.PersistenceException e){
            facesError("something is wrong with our database, please come back later.");
        }
        catch(Exception e){
            facesError(e.getMessage());
        }

        initializeSeats();
        flightServiceBean.Create(flight);
        facesMessage("Flight has been created successfully");

        clear();
    }


    private void initializeSeats() {

        for (int i = rowStart; i <= rowEnd; i++) {
            firstLetter = "A";
            switch (i) {
                case 1:
                    break;
                case 2:
                    firstLetter = "B";
                    break;
                case 3:
                    firstLetter = "C";
                    break;
                case 4:
                    firstLetter = "D";
                    break;
                case 5:
                    firstLetter = "E";
                    break;
                case 6:
                    firstLetter = "F";
                    break;
                case 7:
                    firstLetter = "G";
                    break;
                case 8:
                    firstLetter = "H";
                    break;
                case 9:
                    firstLetter = "I";
                    break;
                case 10:
                    firstLetter = "J";
                    break;
                case 11:
                    firstLetter = "K";
                    break;
                case 12:
                    firstLetter = "L";
                    break;
                case 13:
                    firstLetter = "M";
                    break;
                case 14:
                    firstLetter = "N";
                    break;
                case 15:
                    firstLetter = "O";
                    break;
                case 16:
                    firstLetter = "P";
                    break;
                case 17:
                    firstLetter = "Q";
                    break;
                case 18:
                    firstLetter = "R";
                    break;
                case 19:
                    firstLetter = "S";
                    break;
                case 20:
                    firstLetter = "T";
                    break;
                case 21:
                    firstLetter = "U";
                    break;
                case 22:
                    firstLetter = "V";
                    break;
                case 23:
                    firstLetter = "W";
                    break;
                case 24:
                    firstLetter = "X";
                    break;
                case 25:
                    firstLetter = "Y";
                    break;
                case 26:
                    firstLetter = "Z";
                    break;


            }
            Seat seat = new Seat(firstLetter + "1", baseEconomy, TravelClass.ECONOMY, true, flight);
            flight.addSeat(seat);
            Seat seat1 = new Seat(firstLetter + "2", baseEconomy, TravelClass.ECONOMY, true, flight);
            flight.addSeat(seat1);
            Seat seat2 = new Seat(firstLetter + "3", baseEconomy, TravelClass.ECONOMY, true, flight);
            flight.addSeat(seat2);
            Seat seat3 = new Seat(firstLetter + "4", baseEconomy, TravelClass.ECONOMY, true, flight);
            flight.addSeat(seat3);
            Seat seat4 = new Seat(firstLetter + "5", baseEconomy, TravelClass.ECONOMY, true, flight);
            flight.addSeat(seat4);
            Seat seat5 = new Seat(firstLetter + "6", baseBusiness, TravelClass.BUSINESS, true, flight);
            flight.addSeat(seat5);
            Seat seat6 = new Seat(firstLetter + "7", baseBusiness, TravelClass.BUSINESS, true, flight);
            flight.addSeat(seat6);
            Seat seat7 = new Seat(firstLetter + "8", baseBusiness, TravelClass.BUSINESS, true, flight);
            flight.addSeat(seat7);
            Seat seat8 = new Seat(firstLetter + "9", baseFirstClass, TravelClass.FIRSTCLASS, true, flight);
            flight.addSeat(seat8);
        }


    }

    public void clear() {
        flight.setDepartureDateTime(new Date());
        flight.setDuration(0);
        flight.setFlightCode(0);
        flight.setFlightCompany(new FlightCompany());
        flight.setFrom(new Airport());
        flight.setTo(new Airport());
        flight.setVolumeDiscounts(new HashMap<>());
        flight.setSeats(new HashSet<>());
    }


    public Float getBaseEconomy() {
        return baseEconomy;
    }

    public void setBaseEconomy(Float baseEconomy) {
        this.baseEconomy = baseEconomy;
    }

    public Float getBaseBusiness() {
        return baseBusiness;
    }

    public void setBaseBusiness(Float baseBusiness) {
        this.baseBusiness = baseBusiness;
    }

    public Float getBaseFirstClass() {
        return baseFirstClass;
    }

    public void setBaseFirstClass(Float baseFirstClass) {
        this.baseFirstClass = baseFirstClass;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<String> getAllAirports() {
        return allAirports;
    }

    public void setAllAirports(List<String> allAirports) {
        this.allAirports = allAirports;
    }

    private void facesMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
    
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public int getRowStart() {
        return rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }


}
