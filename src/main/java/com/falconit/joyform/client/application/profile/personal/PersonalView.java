package com.falconit.joyform.client.application.profile.personal;



import com.falconit.joyform.client.application.form.util.Field;
import com.falconit.joyform.client.application.form.util.Form;
import com.falconit.joyform.client.application.form.util.FormCRUD;
import com.falconit.joyform.client.application.util.Constants;
import com.falconit.joyform.client.ui.NavigatedView;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.addins.client.webp.MaterialWebpImage;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.ImageType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;


public class PersonalView extends NavigatedView implements PersonalPresenter.MyView {
    interface Binder extends UiBinder<Widget, PersonalView> {
    }
    
    private MaterialCollapsible coll;
    MaterialCollapsibleBody body[]=null;
    
    private Form myForm = new Form( );
    
    @UiField
    MaterialPanel holder;
        
    @Inject
    PersonalView(Binder uiBinder) {
        
        initWidget( uiBinder.createAndBindUi( this ) );
        
        coll = new MaterialCollapsible( );
        coll.setAccordion( true );

        body = new MaterialCollapsibleBody[10];

        for ( int i =0; i < body.length; i++){
            body[i] = new MaterialCollapsibleBody();

            MaterialCollapsibleItem item = new MaterialCollapsibleItem();
            coll.add(item);

            MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
            item.add(header);
            MaterialLink lnktitle = new MaterialLink();
            lnktitle.setFontWeight( Style.FontWeight.BOLDER );
            lnktitle.setTextColor(Color.TEAL);
            lnktitle.setText( Constants.PROFILE_CATEGORIES[i] );
            header.add(lnktitle);//<m:MaterialIcon iconType="EDIT" waves="DEFAULT" float="LEFT" circle="true" iconSize="LARGE" />

            MaterialLink lnkedit = new MaterialLink();
            lnkedit.setIconType(IconType.EDIT);
            lnkedit.setWaves(WavesType.DEFAULT);
            lnkedit.setIconColor(Color.TEAL);
            lnkedit.setFloat(Style.Float.LEFT);
            lnkedit.setCircle(true);
            lnkedit.setIconSize(IconSize.LARGE);
            header.add( lnkedit );
            
            item.add( body[i] );
        }

        coll.setActive(1);
        coll.setShadow(0);
        holder.add( coll );
        
        getForm( );
    }
     
    private void getForm( ){
        //id = null;
        FormCRUD crud = new FormCRUD();
        crud.setListener(new FormCRUD.CRUDListener(){
            @Override
            public void success(String result) {
                //Window.alert( "Result = " + result );
            }

            @Override
            public void success(Form result) {
                
            }

            @Override
            public void success(List<Form> result) {
                Window.alert("size=" + result.size());
                if( !result.isEmpty() ){
                    
                    myForm = result.get( 0 );
                    //myForm.setMode( Form.DISPLAY_MODE_READ_ONLY );
                    //myForm.setItemDisplay(Form.ITEMS_DISPLAY_CATEGORIZE);
                    int count = 0;
                    Window.alert( "field size=" + myForm.getChild() );
                    
                    for( Field field : myForm.getChild() ){
                        
                        MaterialRow row = new MaterialRow();
                        MaterialLabel label = new MaterialLabel( );
                        label.setGrid( "l4 m4 s12" );
                        label.setFontSize("0.8em");
                        label.setText( field.getChildren().get(0).getLabel().get( Constants.LANGUAGE ) );
                        row.add( label );
                        
                        MaterialLabel value = new MaterialLabel();
                        value.setGrid( "l6 m6 s12" );
                        value.setFontWeight(Style.FontWeight.BOLD);
                        row.add( value );
                        
                        if( field.getName().equals("photo")){
                            MaterialWebpImage photo = new MaterialWebpImage();
                            row.add(photo);
                            photo.setGrid("l2 m2 s4");
                            photo.setFloat(Style.Float.LEFT);
                            photo.setFallbackExtension("png");
                            photo.setMaxWidth("150px");
                            photo.setMaxHeight("150px");
                            photo.setType(ImageType.CIRCLE);
                            photo.setUrl("https://p7.hiclipart.com/preview/535/466/472/google-account-microsoft-account-login-email-gmail-email.jpg");
                         
                            value.setText( "A photo helps personalize your account" );
                        }else{
                            value.setText( "test value" );
                            row.setBorderBottom("1px dotted #b2dfdb");
                        }
                        
                        //Window.alert( "field=" + field.getName() );
                        
                        if( field.getCategory( ).equals("profile")){
                        if( field.getName().equals("photo") )
                            body[0].insert(row, 0);
                        else
                            body[0].add(row);

                        }else if( field.getCategory().equals("contact")){
                            body[1].add(row);

                        }else if( field.getCategory().equals("places")){
                            body[2].add(row);
                        }else if( field.getCategory().equals("work & education")){
                            body[3].add(row);
                        }else if( field.getCategory().equals("documents")){
                            body[4].add(row);
                        }else if( field.getCategory().equals("travel info")){
                            body[5].add(row);
                        }else if( field.getCategory().equals("family & relationships")){
                            body[6].add(row);
                        }else if( field.getCategory().equals("bio-matric")){
                            body[7].add(row);
                        }else if( field.getCategory().equals("health-care")){
                            body[8].add(row);
                        }else if( field.getCategory().equals("others")){
                            body[9].add(row);
                        }
                        
                        count++;
                    }
                    Window.alert( "Total=" + count );
                }
            }

            @Override
            public void fail(String message) {
                Window.alert( "Result = " + message );
            }

            @Override
            public void fqdn(Map<String, Object[]> maps) {}
        });
        
        crud.getBy(
                "automation_1.0.0-SNAPSHOT", 
                "Personal-Information", 
                "Start up");
    }

}
