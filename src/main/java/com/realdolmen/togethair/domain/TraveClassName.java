package com.realdolmen.togethair.domain;

/**
 * Created by GWTBF10 on 6/11/2017.
 */
public enum TraveClassName {
	BUSINESS,FIRST_CLASS,ECONOMY;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase().replace('_','-');
	}
}
