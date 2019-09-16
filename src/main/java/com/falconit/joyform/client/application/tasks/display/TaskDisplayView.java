package com.falconit.joyform.client.application.tasks.display;



import com.falconit.joyform.client.application.form.util.Form;
import com.falconit.joyform.client.application.form.util.FormCRUD;
import com.falconit.joyform.client.application.util.Constants;
import com.falconit.joyform.client.application.util.jbpmclient.api.task.TaskComplete;
import com.falconit.joyform.client.application.util.jbpmclient.api.task.TaskInputDataReader;
import com.falconit.joyform.client.application.util.jbpmclient.api.task.TaskInputDataReader.TaskInputDataReaderListener;
import com.falconit.joyform.client.application.util.jbpmclient.api.task.TaskInputDataRenderring;
import com.falconit.joyform.client.application.util.jbpmclient.api.task.TaskStarter;
import com.falconit.joyform.client.ui.NavigatedView;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTab;
import gwt.material.design.client.ui.MaterialTabItem;
import gwt.material.design.client.ui.MaterialTitle;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskDisplayView extends NavigatedView implements TaskDisplayPresenter.MyView {
    interface Binder extends UiBinder<Widget, TaskDisplayView> {
    }

    public static final String DISPLAY_START_UP = "startup";
    public static final String DISPLAY_PROCESS = "process";
    public static final String DISPLAY = "display";
    
   private String container = "";
   private String taskId = "";
   private String taskName = "";
   private String process = "";
   private String display = "";
   private String title = "";
   private Form form;
   
    @UiField
    MaterialRow holder, comments, forwards, processing, startup;
    @UiField
    MaterialTab tab;
    @UiField
    MaterialTitle txtTitle, txtTitleReply;
    @UiField
    MaterialPanel formHolder, fillup;
    
    //@UiField
    //MaterialTextArea txtbrief;

    @UiField
    MaterialButton btnsend, btnreply;
    
    @UiField
    MaterialTabItem in, out, comment, foward;
    
    @Inject
    TaskDisplayView(Binder uiBinder) {
        initWidget( uiBinder.createAndBindUi(this) );
        
        String value = com.google.gwt.user.client.Window.Location.getParameter("container");
        if( value != null ){
            container = value;
        }
        String pro = com.google.gwt.user.client.Window.Location.getParameter("process");
        if( pro != null ){
            process = pro;
        }
        String id = com.google.gwt.user.client.Window.Location.getParameter("taskId");
        if( id != null ){
            taskId = id;
        }
        String n = com.google.gwt.user.client.Window.Location.getParameter("taskName");
        if( n != null ){
            taskName = n;
        }
        String t = com.google.gwt.user.client.Window.Location.getParameter("title");
        if( t != null ){
            title = t;
        }
        String d = com.google.gwt.user.client.Window.Location.getParameter("display");
        if( d != null ){
            display = d;
        }
        
        //tab.selectTab("out");
        
        if( display.equals(DISPLAY_PROCESS)){
            taskSelected( );
            loadForm( );
            new Comments( comments, container, taskId );
        }
        else
            loadOutput();
    }


    private void loadOutput(){
        btnreply.setEnabled(false);
        MaterialLoader.loading( true );
        processing.setLayoutPosition(Style.Position.ABSOLUTE);
        processing.setVisibility(Style.Visibility.HIDDEN);
        FormCRUD crud = new FormCRUD();
        crud.setListener(new FormCRUD.CRUDListener(){
            @Override
            public void success(String result) {
            }

            @Override
            public void success(Form result) {
                
            }

            @Override
            public void success(List<Form> result) {
                //Window.alert("Success ");
                if( !result.isEmpty() ){
                    form = result.get(0);
                    form.setDraggable( false );
                    
                    txtTitle.setTitle(form.getName());
                    form.render( formHolder );
                    btnreply.setEnabled( true );
                }else{
                    Window.alert("You have no permission to use this Apps");
                    Window.Location.assign( "#privateapps" );
                }
                MaterialLoader.loading( false );
            }

            @Override
            public void fail(String message) {
                Window.alert("You have no permission to use this Apps");
                Window.Location.assign( "#privateapps" );
                MaterialLoader.loading( false );
            }
        });
        
        crud.getBy( container, process, taskName );
    }
    
    private void taskSelected( ){
        
        MaterialLoader.loading( true );
        startup.setLayoutPosition(Style.Position.ABSOLUTE);
        startup.setVisibility(Style.Visibility.HIDDEN);
        
        TaskInputDataReader reader = new TaskInputDataReader();
        reader.setListener(new TaskInputDataReaderListener(){
            @Override
            public void result(String taskName, String nodeName, String Skippable, String actor, Map<String, Object[]> maps) {
                //Window.alert("Task name=" + taskName + ", Node name=" + nodeName+", size=" + maps.size());
                if( !maps.isEmpty() ){
                    holder.add( new TaskInputDataRenderring().render(maps, "") );
                }
                MaterialLoader.loading( false );
            }

            @Override
            public void fail(String message) {
                MaterialLoader.loading( false );
            }
        });
        reader.read(container, taskId);
    }
    
       
    private void loadForm(){
        
        FormCRUD crud = new FormCRUD();
        crud.setListener(new FormCRUD.CRUDListener(){
            @Override
            public void success(String result) {
            }

            @Override
            public void success(Form result) {
                
            }

            @Override
            public void success(List<Form> result) {
                if( !result.isEmpty() ){
                    form = result.get(0);
                    form.setDraggable( false );
                    
                    txtTitleReply.setTitle(form.getName());
                    form.render( fillup );
                }else{
                    out.setVisible( false );
                }
            }

            @Override
            public void fail(String message) {
            }
        });
        
        crud.getBy( container, process, taskName );
    }
    
    @UiHandler("btnsend")
    void onSend(ClickEvent e) {
        MaterialLoader.loading( true );
        try {
            String body = form.getOutputDataForTask().toString();
            //txtbrief.setText( body );
            //Window.alert( form.getOutputDataForTask().toString() );
            TaskStarter start = new TaskStarter();
            start.setListener(new TaskStarter.TaskStarterListener(){
                @Override
                public void success(String processInstanceId) {
                    MaterialLoader.loading( false );
                    btnsend.setBackgroundColor( Color.GREEN );
                    animate( btnsend );
                    resetColor( btnsend );
                }

                @Override
                public void fail(String message, int stage) {
                    MaterialLoader.loading( false );
                    Window.alert(message);
                }
            });
            
            start.startInstances( Constants.url, container, process, body );
        } catch (Exception ex) {
            MaterialLoader.loading( false );
            Logger.getLogger(TaskDisplayView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @UiHandler("btnreply")
    void onReply(ClickEvent e) {
        
        MaterialLoader.loading( true );
        try {
            String body = "{}";
            if( form != null ){
                body = form.getOutputDataForTask().toString();
                //txtbrief.setText( body );
            }
            //Window.alert( form.getOutputDataForTask().toString() );
            TaskComplete complete = new TaskComplete();
            complete.setListener(new TaskComplete.TaskCompleteListener(){
                @Override
                public void success(String processInstanceId) {
                    MaterialLoader.loading( false );
                    btnreply.setBackgroundColor( Color.GREEN );
                    animate( btnreply );
                    resetColor( btnreply );
                }

                @Override
                public void fail(String message, int stage) {
                    MaterialLoader.loading( false );
                    Window.alert(message);
                }
            });
            
            complete.released( Constants.url, container, taskId, body );
        } catch (Exception ex) {
            MaterialLoader.loading( false );
            Logger.getLogger(TaskDisplayView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    private void animate( Widget widget ){
        MaterialAnimation animation = new MaterialAnimation();
        animation.setTransition(Transition.BOUNCEOUT);
        animation.setDelay( 100 );
        animation.setDuration( 1000 * 2 );
        animation.setInfinite( false );        
        animation.animate( widget );
    }
    
    private void resetColor( MaterialButton widget ){
        new Timer(){
            @Override
            public void run() {
                widget.setBackgroundColor( Color.BLUE );
                Window.Location.assign( "#tasklist" );
            }
        }.schedule( 3500 );
    }
}
