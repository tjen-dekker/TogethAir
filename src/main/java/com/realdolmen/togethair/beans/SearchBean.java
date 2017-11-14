package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.TravelClass;
import com.realdolmen.togethair.services.PriceCalculationService;
import com.realdolmen.togethair.services.SearchServiceBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GWTBF10 on 8/11/2017.
 */
@ManagedBean
@SessionScoped
public class SearchBean {
	@Inject
	private SearchServiceBean searchService;
	
	@Inject
	private PriceCalculationService priceCalculationService;

	private List<FlightDTO> searchResults = new ArrayList<>();
	
	@Size(min=3, max=85, message = "city where you want to fly <b>from</b> is empty or too long")
	@Pattern(regexp = "[a-zA-Z]+(?:[ '-][a-zA-Z]+)*", message = "city where you want to fly <b>from</b> contains illegal characters")
	private String fromCityName;
	
	private long flightId;
	
	@Size(min=3, max=85, message = "city where you want to fly <b>to</b> is not the correct length")
	@Pattern(regexp = "[a-zA-Z]+(?:[ '-][a-zA-Z]+)*", message = "city where you want to fly <b>to</b> contains illegal characters")
	private String toCityName;
	
	private Date date1;
	private Date date2;
	
	private TravelClass travelClass = TravelClass.ECONOMY;
	
	@Min(value = 1,message = "minimum 1 passenger")
	@Max(value = 15,message = "maximum 20 passengers")
	private int minNrOfSeats=1;
    private boolean firstTimeSearch =true;
	private boolean firstTimeBook =true;
    private List<String> allCityNames;
    private List<Airport> allAirports;
	
    @PostConstruct
    private void init(){
    	allCityNames = searchService.getAllCityNames();
    }
    
	public void search(AjaxBehaviorEvent event) {
		if(isFirstTimeSearch())
			setFirstTimeSearch(false);
		
		
		LocalDate localDate1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault()).toLocalDate();
		LocalDate today= LocalDate.now();
		
		if(!allCityNames.contains(fromCityName) || !allCityNames.contains(toCityName)){
			facesError("no flights are currently flying on "+fromCityName);
			searchResults.clear();
		}
		else if(localDate1.isBefore(today) || localDate2.isBefore(today)){
			facesError("not allowed to search flights in the past");
			searchResults.clear();
		}
		else if(date2.before(date1)){
			facesError("second date needs to be later then or equal to the first date");
			searchResults.clear();
		}
		else
		{
			searchResults = searchService.findFromToBetweenDates(fromCityName,toCityName,date1,date2, travelClass, minNrOfSeats);
			
			//calculate actual price for cheapest seats from baseprice
			for (FlightDTO flight : searchResults) {
				flight.setPriceOfCheapestSeat(priceCalculationService.calculateTotalPrice(flight.getPriceOfCheapestSeat(),
						flight.getPriceOverridePercentage(),flight.getVolumeDiscounts(),minNrOfSeats));
			}
		}
	}


	
	public void warning(AjaxBehaviorEvent event){
		if(isFirstTimeBook())
			setFirstTimeBook(false);
	}
	
	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public List<Airport> getAllAirports() {
		return allAirports;
	}

	public void setAllAirports(List<Airport> allAirports) {
		this.allAirports = allAirports;
	}

	public List<String> getAllCityNames() {
		return allCityNames;
	}
	
	public void setAllCityNames(List<String> allCityNames) {
		this.allCityNames = allCityNames;
	}
	
	public String getFromCityName() {
		return fromCityName;
	}
	
	public boolean isFirstTimeBook() {
		return firstTimeBook;
	}
	
	public void setFirstTimeBook(boolean firstTimeBook) {
		this.firstTimeBook = firstTimeBook;
	}
	
	public long getFlightId() {
		return flightId;
	}
	
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	
	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}
	
	public String getToCityName() {
		return toCityName;
	}
	
	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}
	
	public Date getDate1() {
		return date1;
	}
	
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	
	public Date getDate2() {
		return date2;
	}
	
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	public TravelClass getTravelClass() {
		return travelClass;
	}
	
	public void setTravelClass(TravelClass travelClass) {
		this.travelClass = travelClass;
	}
	
	public int getMinNrOfSeats() {
		return minNrOfSeats;
	}
	
	public void setMinNrOfSeats(int minNrOfSeats) {
		this.minNrOfSeats = minNrOfSeats;
	}
	
	public List<FlightDTO> getSearchResults() {
		return searchResults;
	}
	
	public void setSearchResults(List<FlightDTO> searchResults) {
		this.searchResults = searchResults;
	}

	public boolean isFirstTimeSearch() {
		return firstTimeSearch;
	}

	public void setFirstTimeSearch(boolean firstTimeSearch) {
		this.firstTimeSearch = firstTimeSearch;
	}
}
