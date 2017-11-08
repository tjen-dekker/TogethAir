package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.services.SearchServiceBean;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by GWTBF10 on 8/11/2017.
 */
@Named
@SessionScoped
public class SearchBean {
	@Inject
	private SearchServiceBean searchService;
	
	
}
