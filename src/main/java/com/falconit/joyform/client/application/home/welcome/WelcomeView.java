package com.falconit.joyform.client.application.home.welcome;


import com.falconit.joyform.client.application.util.Constants;
import com.falconit.joyform.client.application.util.CookieHelper;
import com.falconit.joyform.client.place.NameTokens;
import com.falconit.joyform.client.ui.NavigatedView;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Widget;
import javax.inject.Inject;

public class WelcomeView extends NavigatedView implements WelcomePresenter.MyView {
    interface Binder extends UiBinder<Widget, WelcomeView> {
    }

    @Inject
    WelcomeView(Binder uiBinder) {
        initWidget( uiBinder.createAndBindUi(this) );
                
        if( CookieHelper.getMyCookie( Constants.COOKIE_USER_ID ) == null ){
            History.newItem( NameTokens.login );
        }
    }

  
}
