package com.falconit.joyform.client.application.profile.personal;



import com.falconit.joyform.client.ui.NavigatedView;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import javax.inject.Inject;

public class PersonalView extends NavigatedView implements PersonalPresenter.MyView {
    interface Binder extends UiBinder<Widget, PersonalView> {
    }

    @Inject
    PersonalView(Binder uiBinder) {
        initWidget( uiBinder.createAndBindUi( this ) );
       
        //MaterialIcon ic; ic.setIconSize(IconSize.LARGE);
    }

  
}
