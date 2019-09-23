/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient.api.task;


import com.falconit.joyform.client.application.util.Constants;
import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTitle;


/**
 *
 * @author User
 */
public class TaskInputDataRenderring {
    
    public MaterialRow render( java.util.Map<String, Object[]> maps, String grid ){
        
        boolean titleExist = false;
        
        MaterialRow row = new MaterialRow();
        row.setPaddingBottom(5);
        if( grid != null && !grid.isEmpty())
            row.setGrid( grid );
        
        for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
            
            if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_DATETIME ) 
                    || entry.getValue()[0].toString().equals( ObjectConverter.TYPE_TIMESTAMP )){
                
                MaterialRow child = new MaterialRow();
                row.add(child);

                MaterialColumn colLabel = new MaterialColumn();
                child.add( colLabel );
                colLabel.setGrid("l4 m4 s12");

                MaterialLabel lbl = new MaterialLabel();
                lbl.setText( entry.getKey().replaceAll("_", " ") );
                lbl.setFontSize("0.85em");
                colLabel.add(lbl);
                
                MaterialColumn colValue = new MaterialColumn();
                child.add( colValue );
                colValue.setGrid("l6 m6 s12");

                DateTimeFormat dtfd = DateTimeFormat.getFormat("dd/MM/yyyy hh:mm a");
                MaterialLabel lblValue = new MaterialLabel();
                colValue.add(lblValue);
                lblValue.setText( dtfd.format(new java.util.Date( Long.parseLong( entry.getValue()[1].toString()) )) );
                lblValue.setFontWeight(Style.FontWeight.BOLD);
                child.setBorderBottom("1px dotted #b2dfdb");
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_STRING )){
                
                if( entry.getValue()[1].toString().startsWith("data:image")){
                    
                    MaterialRow sign = new MaterialRow();
                    sign.setMarginTop(10);
                    MaterialLabel lblrating = new MaterialLabel();
                    lblrating.setText( entry.getKey().replaceAll("_", " ") );
                    sign.add( lblrating );
                
                    MaterialImage imageData = new MaterialImage();
                    imageData.setWidth("150px");
                    imageData.setHeight("150px");
                    imageData.setUrl( entry.getValue()[1].toString() );
                    //imageData.setMarginLeft(20);
                    sign.add( imageData );
                    
                    if( entry.getValue()[2].toString().equalsIgnoreCase("photo")){
                        sign.remove( lblrating );
                        imageData.setFloat(Style.Float.RIGHT);
                        imageData.setMarginRight(20);
                        if( titleExist)
                            row.insert( sign, 1 );
                        else
                            row.insert( sign, 0 );
                    }else
                        row.add( sign );
                    
                }else if( entry.getValue()[1].toString().startsWith("http://") 
                        || entry.getValue()[1].toString().startsWith("https://")){
                    // attachment
                }else{
                
                    MaterialRow child = new MaterialRow();
                    row.add(child);
                    
                    MaterialColumn colLabel = new MaterialColumn();
                    child.add( colLabel );
                    
                    MaterialLabel lbl = new MaterialLabel();
                    lbl.setText( entry.getKey().replaceAll("_", " ") );
                    lbl.setFontSize("0.85em");
                    colLabel.add(lbl);
                    
                    if( entry.getValue()[1].toString().equals(Constants.OBJECT_TITLE)){
                        colLabel.remove(lbl);
                        
                        MaterialTitle title = new MaterialTitle();
                        colLabel.add(title);
                        colLabel.setGrid("l12 m12 s12");
                        colLabel.setTextAlign(TextAlign.CENTER);
                        title.setTitle( entry.getKey().replaceAll("_", " ") );
                        title.setTextAlign(TextAlign.CENTER);
                        
                        //lbl.setFontWeight(Style.FontWeight.BOLDER);
                        titleExist = true;
                    }else{
                        colLabel.setGrid("l4 m4 s12");
                        MaterialColumn colValue = new MaterialColumn();
                        child.add( colValue );
                        colValue.setGrid("l6 m6 s12");

                        MaterialLabel lblValue = new MaterialLabel();
                        colValue.add(lblValue);
                        lblValue.setText( entry.getValue()[1].toString() );
                        lblValue.setFontWeight(Style.FontWeight.BOLD);
                        if( entry.getKey().equalsIgnoreCase("password"))
                            lblValue.setText("* * * * * * * * * * *");
                        
                        child.setBorderBottom( "1px dotted #b2dfdb" );
                    }
                
                }
            }else if( entry.getValue()[0].toString().equals(ObjectConverter.TYPE_DECIMAL)
                    || entry.getValue()[0].toString().equals(ObjectConverter.TYPE_NUMBER)){
                
                MaterialRow child = new MaterialRow();
                row.add(child);

                MaterialColumn colLabel = new MaterialColumn();
                child.add( colLabel );
                colLabel.setGrid("l4 m4 s12");
                MaterialLabel lbl = new MaterialLabel();
                lbl.setText( entry.getKey().replaceAll("_", " ") );
                lbl.setFontSize("0.85em");
                colLabel.add(lbl);
                
                MaterialColumn colValue = new MaterialColumn();
                child.add( colValue );
                colValue.setGrid("l6 m6 s12");
                
                MaterialLabel lblValue = new MaterialLabel();
                colValue.add(lblValue);
                lblValue.setText( entry.getValue()[1].toString() );
                lblValue.setFontWeight(Style.FontWeight.BOLD);
                child.setBorderBottom( "1px dotted #b2dfdb" );
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_BOOLEAN ) ){
                
                MaterialRow child = new MaterialRow();
                row.add(child);

                MaterialColumn colLabel = new MaterialColumn();
                child.add( colLabel );
                colLabel.setGrid("l4 m4 s12");
                MaterialLabel lbl = new MaterialLabel();
                lbl.setText( entry.getKey().replaceAll("_", " ") );
                lbl.setFontSize("0.85em");
                colLabel.add(lbl);
                
                MaterialColumn colValue = new MaterialColumn();
                child.add( colValue );
                colValue.setGrid("l6 m6 s12");
                
                MaterialLabel lblValue = new MaterialLabel();
                colValue.add(lblValue);
                lblValue.setText( entry.getValue()[1].toString().equalsIgnoreCase("true") ? "Yes" : "No" );
                lblValue.setFontWeight(Style.FontWeight.BOLD);
                
                child.setBorderBottom("1px dotted #b2dfdb");
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_NULL ) ){
                
                MaterialRow child = new MaterialRow();
                row.add(child);

                MaterialColumn colLabel = new MaterialColumn();
                child.add( colLabel );
                colLabel.setGrid("l4 m4 s12");
                MaterialLabel lbl = new MaterialLabel();
                lbl.setFontSize("0.85em");
                lbl.setText( entry.getKey().replaceAll("_", " ") );
                colLabel.add(lbl);
                
                MaterialColumn colValue = new MaterialColumn();
                child.add( colValue );
                colValue.setGrid("l6 m6 s12");
                MaterialLabel lblValue = new MaterialLabel();
                colValue.add(lblValue);
                lblValue.setText( "" );
                lblValue.setFontWeight(Style.FontWeight.BOLD);
                
                child.setBorderBottom("1px dotted #b2dfdb");

            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_OBJECT ) ){
                
                MaterialLabel widget = new MaterialLabel( );
                widget.setFontWeight(Style.FontWeight.BOLDER);
                if( entry.getKey().contains(".") ){
                    String names[] = entry.getKey( ).split("\\.");
                    if( names.length > 0)
                        widget.setText( names[ names.length -1 ].replaceAll("_", " ") );
                }else
                    widget.setText( entry.getKey().replaceAll("_", " ") );
                
                row.add( widget );
                java.util.Map<String, Object[]> map = (java.util.Map<String, Object[]>) entry.getValue()[1];
                MaterialRow r = render( map, "" );
                widget.setPaddingTop(10);
                row.add( r );
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_ARRAY ) ){
                
                MaterialLabel widget = new MaterialLabel( );
                widget.setText( entry.getKey().replaceAll("_", " ") );
                row.add( widget );
                widget.setPaddingTop(10);
                
                java.util.List<Object[]> mapArray = ( java.util.List) entry.getValue()[1];
                
                for( Object[] object : mapArray ){
                    if( object[0].toString().equals( ObjectConverter.TYPE_OBJECT ) ){
                        java.util.Map<String, Object[]> map = (java.util.Map<String, Object[]>) object[1];
                        MaterialRow r = render( map, "" );
                        row.add( r );
                    }
                }
            }
        }
        
        return row;
    }
}
