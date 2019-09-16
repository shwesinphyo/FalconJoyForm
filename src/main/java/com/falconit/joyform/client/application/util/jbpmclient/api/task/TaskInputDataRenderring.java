/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.util.jbpmclient.api.task;

import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 *
 * @author User
 */
public class TaskInputDataRenderring {
    
    public MaterialRow render( java.util.Map<String, Object[]> maps, String grid ){
        
        MaterialRow row = new MaterialRow();
        if( grid != null && !grid.isEmpty())
            row.setGrid( grid );
        
        for( java.util.Map.Entry<String, Object[]> entry : maps.entrySet() ){
            
            if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_TIMESTAMP )){
                
                MaterialTextBox widget = new MaterialTextBox( );
                DateTimeFormat dtfd = DateTimeFormat.getFormat("dd/MM/yyyy hh:mm a");
                widget.setText( dtfd.format(new java.util.Date( Long.parseLong( entry.getValue()[1].toString()) )) );
                widget.setLabel(entry.getKey().replaceAll("_", " ") );
                widget.setReadOnly(true);
                widget.setBorderBottom("1px solid DarkOliveGreen");
                widget.setPadding(5);
                row.add(widget);
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_DATETIME )){
                
                MaterialTextBox widget = new MaterialTextBox( );
                DateTimeFormat dtfd = DateTimeFormat.getFormat("dd/MM/yyyy hh:mm a");
                widget.setText( dtfd.format(new java.util.Date( Long.parseLong( entry.getValue()[1].toString()) )) );
                widget.setLabel(entry.getKey().replaceAll("_", " "));
                widget.setReadOnly(true);
                widget.setBorderBottom("1px solid DarkOliveGreen");
                widget.setPadding(5);
                row.add(widget);
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_STRING )){
                
                MaterialTextBox widget = new MaterialTextBox( );
                widget.setText( entry.getValue()[1].toString() );
                widget.setLabel(entry.getKey().replaceAll("_", " "));
                widget.setReadOnly(true);
                widget.setBorderBottom("1px solid DarkOliveGreen");
                widget.setPadding(5);
                row.add(widget);
                if( entry.getKey().equalsIgnoreCase("password"))
                    widget.setType(InputType.PASSWORD);
                
            }else if( entry.getValue()[0].toString().equals(ObjectConverter.TYPE_DECIMAL) ){
                
                MaterialTextBox widget = new MaterialTextBox( );
                widget.setText( entry.getValue()[1].toString() );
                widget.setLabel(entry.getKey().replaceAll("_", " "));
                widget.setReadOnly(true);
                widget.setBorderBottom("1px solid DarkOliveGreen");
                widget.setPadding(5);
                row.add(widget);
                
            }else if( entry.getValue()[0].toString().equals(ObjectConverter.TYPE_NUMBER) ){
                
                MaterialTextBox widget = new MaterialTextBox( );
                widget.setText( entry.getValue()[1].toString() );
                widget.setLabel(entry.getKey().replaceAll("_", " "));
                widget.setReadOnly(true);
                widget.setBorderBottom("1px solid DarkOliveGreen");
                widget.setPadding(5);
                row.add(widget);
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_BOOLEAN ) ){
                
                MaterialTextBox widget = new MaterialTextBox( );
                widget.setText( entry.getValue()[1].toString().equalsIgnoreCase("true") ? "Yes" : "No" );
                widget.setLabel(entry.getKey().replaceAll("_", " "));
                widget.setReadOnly(true);
                widget.setBorderBottom("1px solid DarkOliveGreen");
                widget.setPadding(5);
                row.add(widget);
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_NULL ) ){
                MaterialTextBox widget = new MaterialTextBox( );
                widget.setText( "" );
                widget.setLabel(entry.getKey().replaceAll("_", " "));
                widget.setReadOnly(true);
                widget.setBorderBottom("1px solid DarkOliveGreen");
                widget.setPadding(5);
                row.add(widget);
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_OBJECT ) ){
                
                MaterialLabel widget = new MaterialLabel( );
                widget.setFontWeight(Style.FontWeight.BOLDER);
                if( entry.getKey().contains(".")){
                    String names[] = entry.getKey().split("\\.");
                    if( names.length > 0)
                        widget.setText( names[ names.length -1 ].replaceAll("_", " ") );
                }else
                    widget.setText( entry.getKey().replaceAll("_", " ") );
                
                row.add( widget );
                java.util.Map<String, Object[]> map = (java.util.Map<String, Object[]>) entry.getValue()[1];
                MaterialRow r = render( map, "" );
                row.add( r );
                
            }else if( entry.getValue()[0].toString().equals( ObjectConverter.TYPE_ARRAY ) ){
                
                
                MaterialLabel widget = new MaterialLabel( );
                widget.setText( entry.getKey().replaceAll("_", " ") );
                row.add( widget );
                
                
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
