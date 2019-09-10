/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.shared.jsonconvert;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialPanel;
import com.google.gwt.dom.client.Style;

/**
 *
 * @author apple
 */
public class JSONtoPanel extends MaterialColumn{
    
    public JSONtoPanel(){
        setGrid("l12 m12 s12");
        setPaddingTop( 10 );
        
        MaterialCard card = new MaterialCard();
        card.setBackgroundColor(Color.WHITE);
        //card.setFloat(Style.Float.LEFT);
        card.setHoverable( true );
        card.setPaddingTop(10);
        add(card);
        
        MaterialCardContent cardcontent = new MaterialCardContent();
        cardcontent.setTextColor( Color.BLACK );
        cardcontent.setOverflow( Style.Overflow.AUTO );
        card.add(cardcontent);
        
        MaterialPanel target = new MaterialPanel();
        cardcontent.add( target );
        
        
        
    }
}
