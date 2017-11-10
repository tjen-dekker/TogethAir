package com.realdolmen.togethair.converters;

import com.realdolmen.togethair.DTO.SeatDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean @FacesConverter("seatConverter")
public class SeatConverter implements Converter{
    @Override
    public SeatDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return new SeatDTO(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((SeatDTO)o).getLocation();
    }

}
