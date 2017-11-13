package com.realdolmen.togethair.services;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import java.util.PropertyResourceBundle;

public class PricePropertiesProducer {

    @Produces
    public PropertyResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{priceCalculation}", PropertyResourceBundle.class);
    }
}
