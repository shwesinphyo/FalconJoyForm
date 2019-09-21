/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;


import com.falconit.joyform.client.application.form.customwidget.MediaUploading;
import com.falconit.joyform.client.application.form.customwidget.NRCWidget;
import com.falconit.joyform.client.application.form.customwidget.SignatureWidget;
import com.falconit.joyform.client.application.util.Constants;
import com.google.gwt.dom.client.Style;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.rating.MaterialRating;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.constants.RadioButtonType;
import gwt.material.design.client.ui.MaterialAnchorButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRadioButton;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

/**
 *
 * @author User
 */
public class WidgetGenerator {
    
    public interface WidgetGeneratorClickListener{
        public void onClick( Field field, int index );
    }
    private WidgetGeneratorClickListener clickListener;
    public void setClickListener( WidgetGeneratorClickListener clickListener ){
        this.clickListener = clickListener;
    }
    
    public interface WidgetGeneratorMouseListener{
        public void mouseEnter( Field field, int index );
        public void mouseExit( Field field, int index );
    }
    private WidgetGeneratorMouseListener mouseListener;
    public void setMouseListener( WidgetGeneratorMouseListener mouseListener ){
        this.mouseListener = mouseListener;
    }
    
        
    public interface WidgetGeneratorButtonClickListener{
        public void onEditClick( Field field, int index );
        public void onDeleteClick( Field field, int index );
        public void onUpClick( Field field, int index );
        public void onDownClick( Field field, int index );
    }
    private WidgetGeneratorButtonClickListener buttonListener;
    public void setButtonListener( WidgetGeneratorButtonClickListener buttonListener ){
        this.buttonListener = buttonListener;
    }
    
    public MaterialRow getWidget( Field field, int index ) throws Exception{
        MaterialRow row = new MaterialRow(); 
        row.addStyleName("test");
        
        
        
        if( field.getBackgroundColor() != null )
            row.setBackgroundColor( field.getBackgroundColor() );
        
        row.setGrid( field.getGrid() );
        //row.setShadow(1);
        //row.setPadding(8);
        //row.setMargin(4);
        if( field.getPadding() > 0)
            row.setPadding( field.getPadding() );
        if( field.getMargin() > 0)
            row.setMargin( field.getMargin() );
        
        row.setTitle( field.getLabel().get(Constants.LANGUAGE) );
        row.setId( field.getId() );
        
        return row;
    }
    
    public void createWidget( Field parent, MaterialRow row, int index, String mode, boolean shadow ){
        
        for( Field field : parent.getChildren() ){
            
            int colSize = 12 / parent.getChildren( ).size( );
            MaterialColumn child = new MaterialColumn( );
            child.setId( field.getId( ) );
            child.setTitle( field.getLabel().get(Constants.LANGUAGE) );
            child.setGrid("l" + colSize+" m" + colSize+" s12");
            row.add( child );

            
            MaterialAnchorButton btnremove = new MaterialAnchorButton();
            btnremove.setType( ButtonType.FLOATING );
            btnremove.setBackgroundColor( Color.RED );
            btnremove.setIconType( IconType.DELETE );
            btnremove.setSize( ButtonSize.MEDIUM );
            btnremove.setFloat(Style.Float.RIGHT);
            btnremove.setTooltip("Remove");
            btnremove.setMargin(5);
            btnremove.setMarginRight(20);
            btnremove.setVisibility(Style.Visibility.HIDDEN);
            btnremove.addClickHandler(handler ->{buttonListener.onDeleteClick(field, index);});
            
                        
            MaterialAnchorButton btnedit = new MaterialAnchorButton();
            btnedit.setType(ButtonType.FLOATING);
            btnedit.setBackgroundColor(Color.TEAL);
            btnedit.setIconType(IconType.EDIT);
            btnedit.setSize(ButtonSize.MEDIUM);
            btnedit.setFloat(Style.Float.RIGHT);
            btnedit.setTooltip("More rich");
            btnedit.setMargin(5);
            btnedit.setVisibility(Style.Visibility.HIDDEN);
            
            btnedit.addClickHandler(handler ->{buttonListener.onEditClick(field, index);});
            
            MaterialAnchorButton btndown = new MaterialAnchorButton();
            btndown.setType( ButtonType.FLOATING );
            btndown.setBackgroundColor( Color.TEAL );
            btndown.setIconType( IconType.ARROW_DOWNWARD );
            btndown.setSize( ButtonSize.MEDIUM );
            btndown.setFloat(Style.Float.RIGHT);
            btndown.setTooltip("Remove down");
            btndown.setMargin(5);
            btndown.setMarginRight(20);
            btndown.setVisibility(Style.Visibility.HIDDEN);
            
            btndown.addClickHandler(handler ->{buttonListener.onDownClick(field, index);});
            
            MaterialAnchorButton btnup = new MaterialAnchorButton();
            btnup.setType( ButtonType.FLOATING );
            btnup.setBackgroundColor( Color.TEAL );
            btnup.setIconType( IconType.ARROW_UPWARD );
            btnup.setSize( ButtonSize.MEDIUM );
            btnup.setFloat(Style.Float.RIGHT);
            btnup.setTooltip("Remove up");
            btnup.setMargin(5);
            btnup.setMarginRight(20);
            btnup.setVisibility(Style.Visibility.HIDDEN);
            
            btnup.addClickHandler(handler ->{buttonListener.onUpClick(field, index);});
            
            if( mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                
                child.add( btnremove );
                child.add( btnedit );
                child.add( btndown );
                child.add( btnup );
                
                
                child.addMouseOverHandler(handler ->{
                    if( mouseListener != null )
                        mouseListener.mouseEnter(field, index);
                    
                    if( shadow )
                        child.setShadow(1);
                    
                    btnremove.setVisibility( Style.Visibility.VISIBLE );
                    btnedit.setVisibility( Style.Visibility.VISIBLE );
                    new MaterialAnimation().transition(Transition.ZOOMIN).animate( btnremove );
                    new MaterialAnimation().transition(Transition.ZOOMIN).animate( btnedit );
                    if( index > 0){
                        btnup.setVisibility( Style.Visibility.VISIBLE );
                        new MaterialAnimation().transition(Transition.ZOOMIN).animate( btnup );
                    }
                    
                    btndown.setVisibility( Style.Visibility.VISIBLE );
                    new MaterialAnimation().transition(Transition.ZOOMIN).animate( btndown );
                    
                    /*
                    MaterialPathAnimator.animate( btnremove.getElement(), btnedit.getElement(), () -> {
                        // Hide the fab with zoom out animation
                        //new MaterialAnimation().transition(Transition.ZOOMIN).animate( child );
                    });
                    */
                });
                
                /*
                child.addMouseUpHandler(handler ->{
                    if( mouseListener != null )
                        mouseListener.mouseEnter(field, index);
                    
                    btnremove.setVisibility( Style.Visibility.VISIBLE );
                    MaterialPathAnimator.animate( btnremove.getElement(), btnedit.getElement(), () -> {
                        // Hide the fab with zoom out animation
                        new MaterialAnimation().transition(Transition.ZOOMOUT).animate( btnremove );
                        btnedit.setVisibility( Style.Visibility.VISIBLE );
                    });
                });
                */

                child.addMouseOutHandler( handler ->{
                    if( mouseListener != null )
                        mouseListener.mouseExit(field, index);
                    if( shadow )
                        child.setShadow( 0 );
                    
                    new MaterialAnimation().transition(Transition.ZOOMOUT).animate( btnremove );
                    new MaterialAnimation().transition(Transition.ZOOMOUT).animate( btnedit );
                    btnremove.setVisibility( Style.Visibility.HIDDEN );
                    btnedit.setVisibility( Style.Visibility.HIDDEN );
                    
                    new MaterialAnimation().transition(Transition.ZOOMOUT).animate( btnup );
                    new MaterialAnimation().transition(Transition.ZOOMOUT).animate( btndown );
                    btnup.setVisibility( Style.Visibility.HIDDEN );
                    btndown.setVisibility( Style.Visibility.HIDDEN );
                    /*
                    // Execute the close callback animation
                    MaterialPathAnimator.reverseAnimate( btnremove.getElement(), btnedit.getElement(), () -> {
                        // Setting the visibility of the FAB for reverse animation
                        //new MaterialAnimation().transition(Transition.ZOOMOUT).animate(child);
                    });
                    */
                });

                /*
                child.addMouseDownHandler( handler ->{
                    if( mouseListener != null )
                        mouseListener.mouseExit(field, index);
                    
                    // Execute the close callback animation
                    MaterialPathAnimator.reverseAnimate( btnremove.getElement(), btnedit.getElement(), () -> {
                        // Setting the visibility of the FAB for reverse animation
                        new MaterialAnimation().transition(Transition.ZOOMIN).animate(btnedit);
                        btnedit.setVisibility( Style.Visibility.HIDDEN );
                        btnremove.setVisibility( Style.Visibility.HIDDEN );
                    });
                });
                */
            }
            
            if( field.getWidgetType( ).equals( Field.WIDGET_TEXT_BOX ) 
                    || field.getWidgetType( ).equals(Field.WIDGET_TEXT_BOX_NUMBER)){
                MaterialTextBox widget = new MaterialTextBox( );
                widget.setText( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setType( field.getInputType() );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setName(field.getName());
                widget.setLabel( field.getLabel().get(Constants.LANGUAGE) );
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setReadOnly( field.isReadOnly() );
                
                field.bind( widget );
                child.add( widget );
                
                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                

            }else if( field.getWidgetType().equals(Field.WIDGET_TEXT_AREA) ){
                MaterialTextArea widget = new MaterialTextArea();
                widget.setText( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setType( field.getInputType() );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setName(field.getName());
                widget.setLabel( field.getLabel().get(Constants.LANGUAGE) );
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setResizeRule(MaterialTextArea.ResizeRule.AUTO);
                widget.setReadOnly( field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }             
                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                                
            }else if( field.getWidgetType().equals(Field.WIDGET_TEXT_BOX_RICH) ){
                MaterialRichEditor widget = new MaterialRichEditor();
                widget.setHTML( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
                //widget.setLabel( field.getLabel() );
                widget.setHelperText(field.getHelperText());
                widget.setHeight( field.getHeight().isEmpty() ? "260px" : field.getHeight() );
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                

            }else if( field.getWidgetType().equals(Field.WIDGET_CHECK_BOX) ){
                MaterialCheckBox widget = new MaterialCheckBox();
                widget.setText( field.getLabel().get(Constants.LANGUAGE) );
                widget.setValue( field.getValue() != null ? (boolean)field.getValue() : false  );
                widget.setName(field.getName());
                //widget.setLabel( field.getLabel() );
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }else{
                    child.setBorderBottom("1px solid DarkOliveGreen");
                }

            }else if( field.getWidgetType().equals( Field.WIDGET_SWITCH ) ){
                MaterialSwitch widget = new MaterialSwitch();
                widget.setValue( field.getValue() != null ? (boolean)field.getValue() : false  );
                widget.setOnLabel(field.getName());
                widget.setEnabled( !field.isReadOnly() );
                widget.setId(field.getId());
                widget.setTextColor(Color.GREEN);
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }else{
                    child.setBorderBottom("1px solid DarkOliveGreen");
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                
            }else if( field.getWidgetType().equals(Field.WIDGET_RATING) ){
                
                MaterialLabel lblrating = new MaterialLabel();
                lblrating.setText( field.getLabel().get(Constants.LANGUAGE) );
                child.add( lblrating );
                
                MaterialRating widget = new MaterialRating( );
                widget.setValue( field.getValue() != null ? (int) field.getValue() : 0  );
                widget.setId( field.getId() );
                widget.setEnabled( !field.isReadOnly() );
                widget.setTextColor(Color.AMBER);
                if( field.getIconType() != null ){
                    if( field.getIconType().name().equalsIgnoreCase("FAVORITE")){
                        widget.setSelectedRatingIcon( IconType.FAVORITE );
                        widget.setUnselectedRatingIcon( IconType.FAVORITE_BORDER );
                    }
                }
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }else{
                    child.setBorderBottom("1px solid DarkOliveGreen");
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }

            }else if( field.getWidgetType().equals(Field.WIDGET_DATE_TIME) ){
                MaterialDatePicker widget = new MaterialDatePicker();
                widget.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
                widget.setDetectOrientation( true );
                
                if( field.getValue() != null )
                    widget.setDate(new java.util.Date((long) field.getValue() ));
                else
                    widget.setDate(new java.util.Date());
                
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setAutoClose(true);
                //widget.setLabel( field.getLabel() );
                widget.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
                widget.setReadOnly( field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                
            }else if( field.getWidgetType().equals(Field.WIDGET_COMBO_BOX ) ){
                MaterialComboBox<String> widget = new MaterialComboBox<>();
                widget.setMultiple(true);
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setLabel(field.getLabel().get(Constants.LANGUAGE));
                widget.setLabel( field.getLabel().get(Constants.LANGUAGE) );
                widget.setReadOnly( field.isReadOnly() );
                
                int count = 0;
                for( Field data : field.getChildren()){
                    widget.addItem( data.getLabel().get(Constants.LANGUAGE), data.getValue().toString() );
                    if( field.getValue()!= null && field.getValue().toString().equals( data.getValue().toString() ))
                        widget.setSelectedIndex( count );
                    count++;
                }
                
                //widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }

            }else if( field.getWidgetType().equals( Field.WIDGET_RADIO_GROUP ) ){
                
                MaterialRow widget = new MaterialRow();
                widget.setId( field.getId() );
                widget.setPadding(1);
                widget.setMargin(1);
                
                MaterialRow lRow = new MaterialRow();
                lRow.setPaddingBottom(3);
                lRow.setMarginBottom(3);
                MaterialLabel lblrating = new MaterialLabel();
                lblrating.setText( field.getLabel().get(Constants.LANGUAGE) );
                lRow.add( lblrating );
                widget.add( lRow );
                
                for( Field data : field.getChildren() ){
                    MaterialRadioButton rbtn = new MaterialRadioButton();
                    rbtn.setText( data.getLabel().get(Constants.LANGUAGE) );
                    rbtn.setName( field.getName() );
                    //rbtn.setValue( (boolean) data.getValue() );
                    rbtn.setType( RadioButtonType.GAP );
                    
                    if( field.getOrientation().equals(Field.ORIENTATION_VERTICAL)){
                        MaterialRow rRow = new MaterialRow();
                        rRow.add(rbtn);
                        widget.add( rRow );
                    }else{
                        widget.add( rbtn );
                    }
                    
                    if( field.getValue()!= null && field.getValue().toString().equals( rbtn.getText() ))
                        rbtn.setValue(true);
                    
                    widget.setEnabled( !field.isReadOnly() );
                }

                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                

            }else if( field.getWidgetType().equals( Field.WIDGET_NRC ) ){
                MaterialRow widget = new MaterialRow();
                widget.setId( field.getId() );
                
                MaterialLabel lblrating = new MaterialLabel( );
                lblrating.setText( field.getLabel().get(Constants.LANGUAGE) );
                widget.add( lblrating );
                
                NRCWidget nrc = new NRCWidget();
                nrc.setId( field.getId() );
                widget.add(nrc);
                
                field.bind( nrc );
                child.add(widget);
                widget.setEnabled( !field.isReadOnly() );
                
                if( field.getValue() != null )
                    nrc.setValue( field.getValue() );
                
                
                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                
 
            }else if( field.getWidgetType().equals( Field.WIDGET_MEDIA ) ){
                MaterialRow widget = new MaterialRow();
                widget.setId( field.getId() );
                
                MaterialLabel lblrating = new MaterialLabel();
                lblrating.setText( field.getLabel().get(Constants.LANGUAGE) );
                widget.add( lblrating );
                
                MediaUploading nrc = new MediaUploading( MediaUploading.FILE_TYPE_DOC );
                nrc.setId( field.getId() );
                widget.add(nrc);
                
                field.bind( nrc );
                child.add(widget);
                widget.setEnabled( !field.isReadOnly() );
                
                if( field.getValue() != null )
                    nrc.setValue( field.getValue() );

                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }

            }else if(field.getWidgetType().equals( Field.WIDGET_FILE_UPLOADER )){
                MaterialRow widget = new MaterialRow();
                widget.setId( field.getId() );
                
                MaterialLabel lblrating = new MaterialLabel();
                lblrating.setText( field.getLabel().get(Constants.LANGUAGE) );
                widget.add( lblrating );
                
                MediaUploading nrc = new MediaUploading( MediaUploading.FILE_TYPE_IMAGE);
                nrc.setId( field.getId() );
                widget.add(nrc);
                
                field.bind( nrc );
                child.add(widget);
                widget.setEnabled( !field.isReadOnly() );
                
                if( field.getValue() != null )
                    nrc.setValue( field.getValue() );

                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                
  
            }else if(field.getWidgetType().equals( Field.WIDGET_FACIAL_PHOTO ) ){
                MaterialRow widget = new MaterialRow();
                widget.setId( field.getId() );
                
                MaterialLabel lblrating = new MaterialLabel();
                lblrating.setText( field.getLabel().get(Constants.LANGUAGE) );
                widget.add( lblrating );
                
                MediaUploading nrc = new MediaUploading( MediaUploading.FILE_TYPE_FACIAL );
                nrc.setId( field.getId() );
                widget.add(nrc);
                
                field.bind( nrc );
                child.add(widget);
                widget.setEnabled( !field.isReadOnly() );
                
                if( field.getValue() != null )
                    nrc.setValue( field.getValue() );

                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }
                

            }else if( field.getWidgetType().equals( Field.WIDGET_SIGNATURE_PAD ) ){
                MaterialRow widget = new MaterialRow();
                widget.setId( field.getId() );
                
                MaterialLabel lblrating = new MaterialLabel();
                lblrating.setText( field.getLabel().get(Constants.LANGUAGE) );
                widget.add( lblrating );
                
                SignatureWidget nrc = new SignatureWidget();
                nrc.setId( field.getId() );
                widget.add(nrc);
                
                field.bind( nrc );
                child.add(widget);
                widget.setEnabled( !field.isReadOnly() );
                
                if( field.getValue() != null )
                    nrc.setValue( field.getValue() );

                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                                
                if( field.isReadOnly() ){
                    widget.setBorderBottom("1px solid DarkOliveGreen");
                }

            }else{
                child.add( new MaterialLink( "Not implemented yet" ) );
            }
            
        }
    }
}
