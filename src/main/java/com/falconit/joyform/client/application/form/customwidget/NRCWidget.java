/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.customwidget;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 *
 * @author User
 */
public class NRCWidget extends MaterialRow{
    

    private MaterialComboBox<String> cboNo;
    private MaterialComboBox<String> cboTSP;
    private MaterialComboBox<String> cboType;
    private MaterialTextBox txtNumber;
    
    public NRCWidget( ){
        MaterialColumn colNo = new MaterialColumn();
        colNo.setGrid("l2 m2 s3");
        cboNo = new MaterialComboBox<>( );
        colNo.add( cboNo );
        add(colNo);
        for( int i=1; i < 15; i++)
            cboNo.addItem("" + i, "" + i);
        cboNo.setSelectedIndex(0);
        
        MaterialColumn collbl = new MaterialColumn();
        collbl.setGrid("l1 m1 s1");
        collbl.setTextAlign(TextAlign.CENTER);
        MaterialTextBox lbl = new MaterialTextBox();
        lbl.setText("/");
        lbl.setTextAlign(TextAlign.CENTER);
        lbl.setEnabled(false);
        
        collbl.add(lbl);
        add(collbl);
        
        MaterialColumn colTSP = new MaterialColumn();
        colTSP.setGrid( "l2 m2 s4" );
        cboTSP = new MaterialComboBox<>( );
        colTSP.add( cboTSP );
        add( colTSP );
        for( String t : a1 )
            cboTSP.addItem( t, t );
        cboTSP.setSelectedIndex(0);
        
        MaterialColumn colType = new MaterialColumn( );
        colType.setGrid("l1 m1 s4");
        add( colType );
        cboType = new MaterialComboBox<>( );
        colType.add(cboType);
        cboType.addItem("N", "N");
        cboType.addItem("E", "E");
        cboType.addItem("P", "P");
        
        
        MaterialColumn colNum = new MaterialColumn();
        colNum.setGrid("l3 m3 s3");
        add( colNum );
        txtNumber = new MaterialTextBox();
        txtNumber.setType(InputType.NUMBER);
        txtNumber.setMaxLength( 6 );
        colNum.add(txtNumber);

        cboNo.addValueChangeHandler( handler -> {
            String tsp[] = mapTSP.get(cboNo.getSelectedValue().get(0));
            cboTSP.clear();
            for( String t : tsp )
                cboTSP.addItem( t, t );
            cboTSP.setSelectedIndex(0);
        });
        
                // third action
        txtNumber.addKeyUpHandler(new KeyUpHandler(){
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if( txtNumber.getText().trim().length() < 6 ){
                    //txtNumber.setErrorText("6 digits required");
                }
            }
        });
        
       mapTSP.put("1", a1);
       mapTSP.put("2", b2);
       mapTSP.put("3", c3);
       mapTSP.put("4", d4);
       mapTSP.put("5", e5);
       mapTSP.put("6", f6);
       mapTSP.put("7", g7);
       mapTSP.put("8", h8);
       mapTSP.put("9", i9);
       mapTSP.put("10", j10);
       mapTSP.put("11", k11);
       mapTSP.put("12", l12);
       mapTSP.put("13", m13);
       mapTSP.put("14", n14);
    }
    
    public void setValue( Object value ){
        if( value == null ) return;
        try{
            String v = (String) value;
            
            if( v.length() < 10) return;
            
            String no = v.substring( 0, v.indexOf("/"));
            String tsp = v.substring( v.indexOf("/") + 1, v.indexOf("("));
            String type = v.substring( v.indexOf("(") + 1, v.indexOf(")"));
            String number = v.substring( v.indexOf(")") + 1, v.length() );
Window.alert("Value=" + v +", NO=" + no+", tsp=" + tsp +", type=" + type+", num=" + number);
            java.util.List<String> noItems = new java.util.ArrayList<>();
            noItems.add( no );
            cboNo.setSingleValue(no, true);
            //cboNo.setValues( noItems, true );
            
            java.util.List<String> tspItems = new java.util.ArrayList<>();
            tspItems.add( tsp );
            cboTSP.setSingleValue(tsp, true);
            //cboTSP.setValues( tspItems, false );
            
            java.util.List<String> typeItems = new java.util.ArrayList<>();
            typeItems.add( type );
            cboType.setSingleValue( type, true );
            //cboType.setValues( typeItems, false );
            txtNumber.setValue( number );//.setText( number );
            
        }catch(Exception ex){}
    }
    
    public Object getValue(){
        Object object = null;
        
        String no = cboNo.getSelectedValue().get(0);
        String tsp = cboTSP.getSelectedValue().get(0);
        String type = cboType.getSelectedValue().get(0);
        String number = txtNumber.getText();
        String value = no + "/" + tsp + "(" + type + ")" + number;
        object = value;
        
        return object;
    }

    private java.util.Map<String, String[]> mapTSP = new java.util.HashMap<>();
    private String a1[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String b2[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String c3[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String d4[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String e5[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String f6[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String g7[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String h8[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String i9[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String j10[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String k11[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String l12[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String m13[] = new String[]{"aaa","bbb","ccc","ddd"};
    private String n14[] = new String[]{"aaa","bbb","ccc","ddd"};
}
