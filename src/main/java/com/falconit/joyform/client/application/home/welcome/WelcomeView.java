package com.falconit.joyform.client.application.home.welcome;


import com.falconit.joyform.client.ui.NavigatedView;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import javax.inject.Inject;

public class WelcomeView extends NavigatedView implements WelcomePresenter.MyView {
    interface Binder extends UiBinder<Widget, WelcomeView> {
    }

    @Inject
    WelcomeView(Binder uiBinder) {
        initWidget( uiBinder.createAndBindUi(this) );
        //loadProjects();
        //loadProcesses();
        //loadForms();
        //processMappings();
        //processVariables();
    }

  
}
