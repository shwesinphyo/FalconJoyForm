package com.falconit.joyform.client.ui.charts;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.BubbleChart;
import com.googlecode.gwt.charts.client.corechart.BubbleChartOptions;
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendPosition;
import gwt.material.design.client.ui.MaterialCardContent;

public class MaterialBubbleChart extends Composite {

	private static MaterialBubbleChartUiBinder uiBinder = GWT.create(MaterialBubbleChartUiBinder.class);

	interface MaterialBubbleChartUiBinder extends UiBinder<Widget, MaterialBubbleChart> {
	}
	
	@UiField MaterialCardContent cardContent;
	private BubbleChart chart;

	public MaterialBubbleChart() {
		initWidget(uiBinder.createAndBindUi(this));
		initialize();
	}
	
	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				chart = new BubbleChart();
				cardContent.add(chart); 
                                chart.setHeight("380px");
				draw();
			}
		});
	}

	private void draw() {
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "ID");
		dataTable.addColumn(ColumnType.NUMBER, "Impresson");
		dataTable.addColumn(ColumnType.NUMBER, "Participant");
		dataTable.addColumn(ColumnType.STRING, "Title");
		dataTable.addColumn(ColumnType.NUMBER, "Reach");
		dataTable.addRows(4);
		dataTable.setValue(0, 0, "C1");
		dataTable.setValue(1, 0, "C2");
		dataTable.setValue(2, 0, "C3");
		dataTable.setValue(3, 0, "C4");
                
		dataTable.setValue(0, 1, 3242);
		dataTable.setValue(1, 1, 5645);
		dataTable.setValue(2, 1, 9879);
		dataTable.setValue(3, 1, 1298);
                
		dataTable.setValue(0, 2, 435);
		dataTable.setValue(1, 2, 564);
		dataTable.setValue(2, 2, 912);
		dataTable.setValue(3, 2, 102);
               
		dataTable.setValue(0, 3, "Campaign 1");
		dataTable.setValue(1, 3, "Campaign 2");
		dataTable.setValue(2, 3, "Campaign 3");
		dataTable.setValue(3, 3, "Campaign 4");
             
		dataTable.setValue(0, 4, 3242 + 435);
		dataTable.setValue(1, 4, 5645 + 564);
		dataTable.setValue(2, 4, 9879 + 912);
		dataTable.setValue(3, 4, 1298 + 102);


		// Set options
		

		// Draw the chart
		chart.draw(dataTable, getOptions());
	}
	
	private BubbleChartOptions getOptions() {
		BubbleChartOptions options = BubbleChartOptions.create();
		options.setColors("2196f3", "42a5f5", "64b5f6");
		
		// Set Legend
		Legend legend = Legend.create();
		legend.setPosition(LegendPosition.TOP);
		options.setLegend(legend);
		
		return options;
	}

}
