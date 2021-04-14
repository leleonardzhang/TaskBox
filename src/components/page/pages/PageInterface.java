package components.page.pages;

import java.beans.PropertyChangeListener;

import components.composedGraph.ComposedGraphInterface;

public interface PageInterface{
	void addPropertyChangeListener(PropertyChangeListener aPropertyChangeListener);
	void removePropertyChangeListener(PropertyChangeListener aPropertyChangeListener);
	String getTask();
	void setTask(String newTask);
	String getAnswer();
	void setAnswer(String newAnswer);
	int getPageId();
	void setPageId(int newPageId);
	ComposedGraphInterface getGraph();
	void setGraph(ComposedGraphInterface newGraph);
}
