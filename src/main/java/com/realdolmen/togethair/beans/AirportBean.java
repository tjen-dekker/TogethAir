package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;
import com.realdolmen.togethair.domain.Country;
import com.realdolmen.togethair.domain.Region;
import com.realdolmen.togethair.services.AirportServiceBean;
import org.apache.shiro.SecurityUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class AirportBean {

    @Inject
    private AirportServiceBean serviceBean;
    
    @Size(min=3, max=85, message = "city is empty or too long")
    @Pattern(regexp = "[a-zA-Z]+(?:[ '-][a-zA-Z]+)*", message = "city contains illegal characters")
    private String cityName;
    
    @Size(min=3, max=85, message = "country is empty or too long")
    @Pattern(regexp = "[a-zA-Z]+(?:[ '-][a-zA-Z]+)*", message = "country contains illegal characters")
    private String countryName;
    
    @Size(min=3, max=85, message = "airport name is empty or too long")
    private String airportName;
    
    @Size(min=3, message = "airport code has to be 3 capital letters")
    @Pattern(regexp = "([A-Z]){3}", message = "airport code has to be 3 capital letters")
    private String airportCode;
    private String regionString;
    private Region region;
    private Airport airport;
    private City city;
    private Country country;


    private List<String> allAirportNames = new ArrayList<>();
    private List<String> allCountryNames = new ArrayList<>();
    private List<String> allCityNames = new ArrayList<>();
    private List<Region> allRegionNames = new ArrayList<>();


    private String normalizeInput(String input) {
        String[] words = input.split("([ -])");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if (i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }


    @PostConstruct
    public void init() {
        city = new City();
        airport = new Airport();
        country = new Country();
        allAirportNames = serviceBean.allAirportNames();
        allCityNames = serviceBean.allCityNames();
        allCountryNames = serviceBean.allCountryNames();
//        Region[] enumConstants = Region.values();
//        for (Region enumConstant : enumConstants) {
//            allRegionNames.add(enumConstant);
//        }
    }


    public void createAirport() {

        try {
            if (allCountryNames.contains(normalizeInput(countryName))) {
                Country country = serviceBean.countryByName(countryName);

//            serviceBean.updateCountry(country);
//            country.setId(null);
                city.setCountry(country);
            } else {
                this.country.setName(countryName);
                region = Region.WESTERN_EUROPE;
                this.country.setRegion(region);
                city.setCountry(this.country);
            }
    
            if (allCityNames.contains(normalizeInput(cityName))) {
                City city = serviceBean.cityByName(cityName);

//            serviceBean.updateCity(city);
//            city.setId(null);
                airport.setCity(city);
            } else {
                city.setName(cityName);
                airport.setCity(city);
            }
            airport.setCode(airportCode);
            airport.setName(airportName);
    
            this.serviceBean.saveAirport(airport);
    
            facesMessage("Airport was added successfully");
        }
        catch (javax.persistence.PersistenceException e){
            facesError("something is wrong with our database, please come back later.");
        }
        catch(Exception e){
            facesError(e.getMessage());
        }

    }

    public String getRegionString() {
        return regionString;
    }

    public void setRegionString(String regionString) {
        this.regionString = regionString;
    }

    private void facesMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
    
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }



    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<String> getAllAirportNames() {
        return allAirportNames;
    }

    public void setAllAirportNames(List<String> allAirportNames) {
        this.allAirportNames = allAirportNames;
    }

    public List<String> getAllCountryNames() {
        return allCountryNames;
    }

    public void setAllCountryNames(List<String> allCountryNames) {
        this.allCountryNames = allCountryNames;
    }

    public List<String> getAllCityNames() {
        return allCityNames;
    }

    public void setAllCityNames(List<String> allCityNames) {
        this.allCityNames = allCityNames;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Region> getAllRegionNames() {
        return allRegionNames;
    }

    public void setAllRegionNames(List<Region> allRegionNames) {
        this.allRegionNames = allRegionNames;
    }
}
