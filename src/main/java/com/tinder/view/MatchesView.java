package com.tinder.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinder.model.MatchesService;
import com.tinder.model.TinderAPI;
import com.tinder.model.webservice.data.MatchesDTO;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@Service
public class MatchesView extends VerticalLayout implements View {
	private static final long serialVersionUID = 3241054721587420815L;
	private String userToken;
	private Navigator navigator;

	public MatchesView() {}

	@Autowired
	private TinderAPI tinderAPI;

	@Autowired
	private MatchesService matchesService;

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}

    @Override
    public void enter(ViewChangeEvent event) {
    	MatchesDTO matchesDTO = tinderAPI.getAllMatches(userToken);

    	if (this.getComponentCount()==0)
			addComponent(matchesService.showMatches(navigator, matchesDTO.getMatchList()));
    }
}