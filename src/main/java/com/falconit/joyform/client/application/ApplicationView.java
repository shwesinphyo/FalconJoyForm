package com.falconit.joyform.client.application;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */



import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.*;
import com.falconit.joyform.client.ThemeManager;
import com.falconit.joyform.client.place.NameTokens;

import javax.inject.Inject;
import java.util.Date;

import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
import com.google.gwt.user.client.History;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    public interface Binder extends UiBinder<Widget, ApplicationView> {
    }

//    private String link;
//    private String specification;
//    private final String THEME_COLOR = "#1565c0";
//    private final String MANIFEST_URL = "manifest.json";
//    private final String SERVICE_WORKER_URL = "service-worker.js";
//    private AppInstaller appInstaller;

    @UiField
    HTMLPanel menu;
    @UiField
    HTMLPanel main;

    @UiField
    MaterialFooter footer;
    @UiField
    MaterialFooterCopyright footerCopyRight;

    //@UiField
    //MaterialPanel panel, titlePanel;
    //@UiField
    //MaterialLabel title;//, description;
/*
    @UiField
    MaterialChip chipXml, chipJava, chipSpecification, chipInstallApp;
*/
    @UiField
    MaterialLabel footerCopyRightLabel;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(ApplicationPresenter.SLOT_MENU, menu);
        bindSlot(ApplicationPresenter.SLOT_MAIN, main);

        // Initializing the PWA Feature
//        if (PwaManager.isPwaSupported()) {
//            PwaManager.getInstance()
//                    .setThemeColor(THEME_COLOR)
//                    .setWebManifest(MANIFEST_URL)
//                    .setServiceWorker(new ServiceWorkerManager(SERVICE_WORKER_URL))
//                    .load();
//            appInstaller = new AppInstaller(() -> installAppOverlay.open());
//        } else {
//            MaterialToast.fireToast("PWA features are not supported into your browser");
//        }
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        
                
        String value = com.google.gwt.user.client.Window.Location.getParameter("menu");
        if( value != null && value.equalsIgnoreCase("no")){
            if( menu.getWidget(0) instanceof MaterialPanel){
                //Window.alert("Panel");
                 if( ((MaterialPanel)menu.getWidget(0)).getWidget(1) instanceof MaterialSideNavPush){
                     //Window.alert("MaterialSideNavPush");
                     ((MaterialSideNavPush)((MaterialPanel)menu.getWidget(0)).getWidget(1)).setShowOnAttach(false);
                 }
            }
            menu.setVisible( false );
            footer.setVisible(false);
        }else{
            ((MaterialSideNavPush)((MaterialPanel)menu.getWidget(0)).getWidget(1)).setShowOnAttach( true );
            ((MaterialSideNavPush)((MaterialPanel)menu.getWidget(0)).getWidget(1)).show();
        }
        
        //ThemeManager.register(footer);
        ThemeManager.register(footerCopyRight, ThemeManager.DARKER_SHADE);
        ThemeManager.initialize();

        try{
            if( History.getToken().equals(NameTokens.formediting)){
                footer.setVisible(false);
            }
        }catch(Exception ex){}
        
        /*
        chipJava.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        chipJava.addClickHandler(clickEvent -> {
            String java = "https://github.com/GwtMaterialDesign/gwt-material-demo/tree/master/src/main/java/gwt/material/design/demo/client/application/" + link + ".java";
            Window.open(java, "_blank", "");
        });

        chipXml.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        chipXml.addClickHandler(clickEvent -> {
            String xml = "https://github.com/GwtMaterialDesign/gwt-material-demo/tree/master/src/main/java/gwt/material/design/demo/client/application/" + link + ".ui.xml";
            Window.open(xml, "_blank", "");
        });

        chipSpecification.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        chipSpecification.addClickHandler(clickEvent -> Window.open(specification, "_blank", ""));
        chipInstallApp.getElement().getStyle().setCursor(Style.Cursor.POINTER);

        ThemeManager.register(chipXml, ThemeManager.DARKER_SHADE);
        ThemeManager.register(chipXml.getLetterLabel(), ThemeManager.LIGHTER_SHADE);
        ThemeManager.register(chipJava, ThemeManager.DARKER_SHADE);
        ThemeManager.register(chipJava.getLetterLabel(), ThemeManager.LIGHTER_SHADE);
        ThemeManager.register(chipSpecification, ThemeManager.DARKER_SHADE);
        ThemeManager.register(chipSpecification.getLetterLabel(), ThemeManager.LIGHTER_SHADE);
        ThemeManager.register(chipInstallApp, ThemeManager.DARKER_SHADE);
        ThemeManager.register(chipInstallApp.getLetterLabel(), ThemeManager.LIGHTER_SHADE);
*/
        //ThemeManager.register(titlePanel);

        footerCopyRightLabel.setText("© " + getFormat("yyyy").format(new Date()) + " Copyright falconIT" );
    }

/*    
    @UiHandler("chipInstallApp")
    void onInstallApp(ClickEvent e) {
        appInstaller.prompt();
    }
*/
    /*
    @UiHandler("imgGPlus")
    void onGPlus(ClickEvent e) {
        Window.open("https://plus.google.com/communities/108005250093449814286", "", "_blank");
    }
*/
    @Override
    public void setPageTitle(String title, String description, String link, String specification) {
//        this.title.setText(title);
//        this.description.setText(description);
//        this.link = link;
//        this.specification = specification;

/*
        if (link.isEmpty()) {
            chipJava.setVisible(false);
            chipXml.setVisible(false);
        } else {
            chipJava.setVisible(true);
            chipXml.setVisible(true);
        }

        if (specification.isEmpty()) {
            chipSpecification.setVisible(false);
        } else {
            chipSpecification.setVisible(true);
        }
*/
        //new MaterialAnimation().transition(Transition.BOUNCEINLEFT).animate(this.title);
        //new MaterialAnimation().transition(Transition.BOUNCEINLEFT).animate(this.description);
    }
}
