/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;

/**
 *
 * @author User
 */
public class FormCategorize {
    
    
    private MaterialCollapsible coll;
    private MaterialCollapsibleBody bodyProfile;
    private MaterialCollapsibleBody bodyContant;
    private MaterialCollapsibleBody bodyPlaces;
    private MaterialCollapsibleBody bodyWorks;
    private MaterialCollapsibleBody bodyDocuments;
    private MaterialCollapsibleBody bodyTravel;
    private MaterialCollapsibleBody bodyFamily;
    private MaterialCollapsibleBody bodyHealth;
    private MaterialCollapsibleBody bodyBiometric;
    private MaterialCollapsibleBody bodyOthers;
    
    public MaterialCollapsible getCollapsible(){
        return coll;
    }
    
    public FormCategorize( ){
        
        coll = new MaterialCollapsible();
        //accordion="true" active="1" shadow="0"
        coll.setAccordion(true);
        coll.setActive(1);
        coll.setShadow(0);
        
        createCollapsible("Profile", bodyProfile );
        createCollapsible("Contact", bodyContant );
        createCollapsible("Places", bodyPlaces );
        createCollapsible("Works & education", bodyWorks );
        createCollapsible("Documents", bodyDocuments );
        createCollapsible("Travel info", bodyTravel );
        createCollapsible("Family & relationships", bodyFamily );
        createCollapsible("Healthcare", bodyHealth );
        createCollapsible("Bio-metric", bodyBiometric );
        createCollapsible("Others", bodyOthers );
    }
    
    
    
    private void createCollapsible(String title, MaterialCollapsibleBody body ){
        MaterialCollapsibleItem item = new MaterialCollapsibleItem();
        coll.add(item);
        
        MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
        item.add(header);
        MaterialLink lnktitle = new MaterialLink();
        lnktitle.setFontWeight( Style.FontWeight.BOLDER );
        lnktitle.setTextColor(Color.TEAL);
        lnktitle.setText(title);
        header.add(lnktitle);//<m:MaterialIcon iconType="EDIT" waves="DEFAULT" float="LEFT" circle="true" iconSize="LARGE" />

        body = new MaterialCollapsibleBody(); 
        item.add( body );
    }
   
    public void categorize( String category, MaterialRow row ){
        Window.alert( (category == null) +", " + (row == null) +", cat=" + category +", body=" + (bodyProfile==null) );
        if( category.equals("profile")){
            bodyProfile.add(row);
        }else if( category.equals("contact")){
            bodyContant.add(row);
            
        }else if( category.equals("places")){
            bodyPlaces.add(row);
        }else if( category.equals("work & education")){
            bodyWorks.add(row);
        }else if( category.equals("documents")){
            bodyDocuments.add(row);
        }else if( category.equals("travel info")){
            bodyTravel.add(row);
        }else if( category.equals("family & relationships")){
            bodyFamily.add(row);
        }else if( category.equals("bio-matric")){
            bodyBiometric.add(row);
        }else if( category.equals("health-care")){
            bodyHealth.add(row);
        }else if( category.equals("others")){
            bodyOthers.add(row);
        }
    }

}
