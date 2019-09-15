package com.falconit.joyform.client.application.apps.privateapps;



import com.falconit.joyform.client.application.tasks.display.TaskDisplayView;
import com.falconit.joyform.client.application.util.jbpmclient.api.process.ProcessByContainer;
import com.falconit.joyform.client.ui.NavigatedView;
import com.falconit.joyform.shared.jsonconvert.ObjectConverter;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardAction;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialRow;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PrivateAppsView extends NavigatedView implements PrivateAppsPresenter.MyView {
    interface Binder extends UiBinder<Widget, PrivateAppsView> {
    }

    @UiField
    MaterialRow appsholder;
    
    private List<java.util.Map<String, Object[]>> lstTasks = new ArrayList<>();
            
    @Inject
    PrivateAppsView(Binder uiBinder) {
        initWidget( uiBinder.createAndBindUi(this) );
        load();
    }

    private void load(){
        lstTasks.clear();
        MaterialLoader.loading( true );
        
        ProcessByContainer helper = new ProcessByContainer();
        helper.setListener(new ProcessByContainer.ProcessByContainerListener() {
            @Override
            public void success(String result) {
                
                MaterialLoader.loading( false );
                
                JSONObject jsonOnlineUser = JSONParser.parseStrict( result ).isObject();
                JSONArray tasks = jsonOnlineUser.get("processes").isArray();
                if( tasks == null || tasks.size() == 0 ){
                    
                }else{
                    for( int i=0; i < tasks.size(); i++){
                        JSONObject task = tasks.get(i).isObject();
                        
                        try {
                            java.util.Map<String, Object[]> taskMap = new ObjectConverter().fromJSON( task, false, false );
                            createProcess( taskMap );
                            lstTasks.add( taskMap );
                        } catch (Exception ex) {
                            Window.alert(ex.getMessage());
                        }
                    }
                }
            }

            @Override
            public void fail(String message, int stage) {
              Window.alert( message );
              MaterialLoader.loading( false );
            }
        });

        helper.processesList( "DevTest_1.0.0-SNAPSHOT", 0, 200, true );
    }
    
    private void createProcess( java.util.Map<String, Object[]> taskMap ){
        try{
            MaterialColumn col = new MaterialColumn();
            col.setGrid("l4 m4 s12");
            appsholder.add(col);
            
            MaterialCard card = new MaterialCard();
            card.setBackgroundColor(Color.BLUE_GREY_DARKEN_3);
            col.add(card);
            
            MaterialCardContent content = new MaterialCardContent();
            card.add(content);
            content.setTextColor(Color.WHITE);
            
            MaterialCardTitle title = new MaterialCardTitle();
            content.add(title);
            title.setIconType(IconType.APPS);
            title.setIconPosition(IconPosition.RIGHT);
            title.setText( taskMap.get("process-name")[1].toString() );
            
            MaterialLabel label = new MaterialLabel();
            content.add(label);
            label.setText("Version: "+taskMap.get("process-version")[1].toString());
            
            MaterialCardAction actions = new MaterialCardAction();
            card.add(actions);
            
            MaterialLink link = new MaterialLink();
            actions.add(link);
            link.setText("Open");
            link.setIconType(IconType.OPEN_IN_NEW);
            link.setHref(
                "?container=" + taskMap.get("container-id")[1].toString()
                + "&process=" + taskMap.get("process-id")[1].toString()
                + "&title=" + taskMap.get("process-name")[1].toString()
                + "&taskName=Start up"
                + "&display=" + TaskDisplayView.DISPLAY_START_UP
                + "#taskdisplay" );
            
        }catch(Exception ex){}
    }
    
    private void taskSelected( java.util.Map<String, Object[]> map ){
        String container = map.get("task-container-id")[1].toString();
        String taskId = map.get("task-id")[1].toString();
        String processId = map.get("task-proc-def-id")[1].toString();
        String taskName = map.get("task-name")[1].toString();
        
        
        Window.Location.assign( "?container=" + container + "&taskId=" + taskId +"&process" + processId +"&taskName=" + taskName + "#taskdisplay" );
            
        /*
        TaskInputDataReader reader = new TaskInputDataReader();
        reader.setListener(new TaskInputDataReaderListener(){
            @Override
            public void result(String taskName, String nodeName, String Skippable, Map<String, Object[]> maps) {
                Window.alert("Task name=" + taskName + ", Node name=" + nodeName+", size=" + maps.size());
            }

            @Override
            public void fail(String message) {
            }
        });
        reader.read(container, taskId);
        */
    }
    
  /*  
    @UiHandler("btnGotoFirstPage")
    void onGotoFirstPage(ClickEvent e) {
        pager.firstPage();
    }
*/
    
    
}
