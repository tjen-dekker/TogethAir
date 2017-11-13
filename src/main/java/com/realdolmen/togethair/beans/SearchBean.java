package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.TravelClass;
import com.realdolmen.togethair.services.SearchServiceBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
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
	
	private List<FlightDTO> searchResults = new ArrayList<>();
	
	private String fromCityName;
	private String toCityName;
	private Date date1;
	private Date date2;
	private TravelClass travelClass = TravelClass.ECONOMY;
	private int minNrOfSeats;
	
	public void search(AjaxBehaviorEvent event) {
		searchResults = searchService.findFromToBetweenDates(fromCityName,toCityName,date1,date2, travelClass, minNrOfSeats);
		System.out.print(event.toString());
	}
	
	public String getFromCityName() {
		return fromCityName;
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
}
