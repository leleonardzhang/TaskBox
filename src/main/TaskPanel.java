package main;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import components.composedGraph.ComposedGraph;
import components.mainpanel.MainPanel;
import components.mainpanel.MainPanelInterface;
import components.page.pages.Page;
import exporter.Exporter;

public class TaskPanel {

	public static void main(String[] args) {
		MainPanelInterface panel = new MainPanel();
		panel.addPage(new Page("Example task I", "", 0));
		panel.addPage(new Page("Example task II", "", 1));
		
		ObjectEditor.setPropertyAttribute(Page.class, "answer", AttributeNames.SCROLLED, true);
		ObjectEditor.setMethodAttribute(ComposedGraph.class, "addLine", AttributeNames.ICON, "Test");
		
		OEFrame frame = ObjectEditor.edit(panel);
		
		frame.setSize(700, 1000);
		frame.setLocation(0, 0);
		Exporter.export(panel, "example.json");

	}

}
