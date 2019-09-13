/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.form.util;


import com.google.gwt.dom.client.Style;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.pathanimator.MaterialPathAnimator;
import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialAnchorButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;
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
        
        row.setTitle( field.getLabel() );
        row.setId( field.getId() );
        
        return row;
    }
    
    public void createWidget( Field parent, MaterialRow row, int index, String mode, boolean shadow ){
        
        for( Field field : parent.getChildren() ){
            
            int colSize = 12 / parent.getChildren().size();
            MaterialColumn child = new MaterialColumn();
            child.setId( field.getId() );
            child.setTitle( field.getLabel() );
            child.setGrid("l" + colSize+" m" + colSize+" s12");
            row.add( child );
            
            
            MaterialAnchorButton btnedit = new MaterialAnchorButton();
            btnedit.setType(ButtonType.FLOATING);
            btnedit.setBackgroundColor(Color.BLUE);
            btnedit.setIconType(IconType.EDIT);
            btnedit.setSize(ButtonSize.MEDIUM);
            btnedit.setFloat(Style.Float.RIGHT);
            btnedit.setTooltip("Edit");
            btnedit.setVisibility(Style.Visibility.HIDDEN);
            child.add( btnedit );
            btnedit.addClickHandler(handler ->{buttonListener.onEditClick(field, index);});
            
            MaterialAnchorButton btnremove = new MaterialAnchorButton();
            btnremove.setType( ButtonType.FLOATING );
            btnremove.setBackgroundColor( Color.RED );
            btnremove.setIconType( IconType.DELETE );
            btnremove.setSize( ButtonSize.MEDIUM );
            btnremove.setFloat(Style.Float.RIGHT);
            btnremove.setTooltip("Remove");
            btnremove.setMarginRight(20);
            btnremove.setVisibility(Style.Visibility.HIDDEN);
            child.add( btnremove );
            btnremove.addClickHandler(handler ->{buttonListener.onDeleteClick(field, index);});
            
            if( mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                
                child.addMouseOverHandler(handler ->{
                    if( mouseListener != null )
                        mouseListener.mouseEnter(field, index);
                    
                    if( shadow )
                        child.setShadow(1);
                    
                    btnremove.setVisibility( Style.Visibility.VISIBLE );
                    btnedit.setVisibility( Style.Visibility.VISIBLE );
                    new MaterialAnimation().transition(Transition.ZOOMIN).animate( btnremove );
                    new MaterialAnimation().transition(Transition.ZOOMIN).animate( btnedit );
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
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setReadOnly( field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                
            }else if( field.getWidgetType().equals(Field.WIDGET_TEXT_AREA) ){
                MaterialTextArea widget = new MaterialTextArea();
                widget.setText( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setType( field.getInputType() );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setName(field.getName());
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
                
            }else if( field.getWidgetType().equals(Field.WIDGET_TEXT_BOX_RICH) ){
                MaterialRichEditor widget = new MaterialRichEditor();
                widget.setHTML( field.getValue() != null ? field.getValue().toString() : "" );
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
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
                
            }else if( field.getWidgetType().equals(Field.WIDGET_CHECK_BOX) ){
                MaterialCheckBox widget = new MaterialCheckBox();
                widget.setText( field.getLabel() );
                widget.setValue( field.getValue() != null ? (boolean)field.getValue() : false  );
                widget.setName(field.getName());
                widget.setEnabled( !field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
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
                widget.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
                widget.setReadOnly( field.isReadOnly() );
                
                field.bind(widget);
                child.add(widget);
                
                if( clickListener != null  && mode.equals(Form.DISPLAY_MODE_DESIGNER) ){
                    widget.addClickHandler(handler ->{
                        clickListener.onClick( field, index );
                    });
                }
                
            }else if( field.getWidgetType().equals(Field.WIDGET_COMBO_BOX ) ){
                MaterialComboBox<String> widget = new MaterialComboBox<>();
                widget.setMultiple(true);
                widget.setAutoValidate( field.isValidate() );
                widget.setAllowBlank( field.isAllowBlank() );
                widget.setId(field.getId());
                widget.setPlaceholder(field.getPlaceHolder());
                widget.setHelperText(field.getHelperText());
                widget.setLabel(field.getLabel());
                widget.setReadOnly( field.isReadOnly() );
                
                int count = 0;
                for( Field data : field.getChildren()){
                    widget.addItem( data.getLabel(), data.getValue().toString() );
                    if( field.getValue().toString().equals( data.getValue().toString() ))
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
                
            }else{
                child.add( new MaterialLink( "Not implemented yet" ) );
            }
            
        }
    }
}
