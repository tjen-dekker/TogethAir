package com.realdolmen.togethair.services;

import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.domain.Seat;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;

/**
 * Created by GWTBF10 on 10/11/2017.
 */
@RequestScoped
public class PriceCalculationService implements Serializable{
	
	//@ManagedProperty("#{priceCalculation}")
	@Inject
	private PropertyResourceBundle bundle;
	
	public float calculateTotalPrice(Booking booking, boolean usedCreditCard){
		List<Passenger> passengers = booking.getPassengers();

		//get margin and discounts
		float marginOfProfit = Float.parseFloat(bundle.getString("marginOfProfitPercentage"))/100;
		float creditcardDiscount = Float.parseFloat(bundle.getString("creditcardDiscountPercentage"))/100;
		if(passengers.isEmpty()) return 0;
		float priceOverride = passengers.get(0).getSeat().getFlight().getPriceOverridePercentage()/100;
		float volumeDiscount=0;
		
		//check highest applicable volume discount
		Map<Integer, Integer> volumeDiscounts = passengers.get(0).getSeat().getFlight().getVolumeDiscounts();
		Set<Integer> volumeDiscountsKeyset = volumeDiscounts.keySet();
		int highestApplicable=0;
		for (Integer i : volumeDiscountsKeyset) {
			highestApplicable = (passengers.size() > i ? i : highestApplicable);
		}
		if(highestApplicable>0)
			volumeDiscount = volumeDiscounts.get(highestApplicable)/100;
		
		float totalPrice=0F;
		
		//baseprice for each seat
		for(Passenger p : passengers){totalPrice +=  p.getSeat().getPrice();}
		
		//adding our own margin
		totalPrice *= 1+marginOfProfit;

		//employee Override
		totalPrice*=priceOverride;
		
		//apply volume discount
		totalPrice*=1-volumeDiscount;
		
		//apply creditcardDiscount
		if(usedCreditCard) {
			totalPrice *= 1-creditcardDiscount;
		}
		
		
		return totalPrice;
	}
}
