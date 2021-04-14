package components.page.pages;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextArea;

import components.composedGraph.ComposedGraph;
import components.composedGraph.ComposedGraphInterface;
import components.listenersupport.PropertyListenerSupport;
import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.PreferredWidgetClass;
import util.annotations.PropertyNames;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"task", "answer", "graph"})
@EditablePropertyNames({"answer"})
public class Page implements PageInterface{
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private String answer, task;
	private int pageId;
	private ComposedGraphInterface graph;
	
	public Page(String newTask, String newAnswer, int newPageId) {
		graph = new ComposedGraph(50, 525);
		setTask(newTask);
		setAnswer(newAnswer);
		setPageId(newPageId);
	}
	
	@PreferredWidgetClass(JTextArea.class)
	@ComponentHeight(100)
	@ComponentWidth(200)
	@Column(0)
	@Row(0)
	@Override
	public String getTask() {
		return task;
	}
	
	@Override
	public void setTask(String newTask) {
		String oldTask = task;
		task = newTask;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "task", oldTask, task));
	}
	
	@PreferredWidgetClass(JTextArea.class)
	@ComponentHeight(200)
	@ComponentWidth(200)
	@Column(0)
	@Row(1)
	@Override
	public String getAnswer() {
		return answer;
	}
	
	@Override
	public void setAnswer(String newAnswer) {
		String oldAnswer = answer;
		answer = newAnswer;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "answer", oldAnswer, answer));
	}
	
	@Override
	public int getPageId() {
		return pageId;
	}
	
	@Override
	public void setPageId(int newPageId) {
		pageId = newPageId;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aPropertyChangeListener) {
		propertyListenerSupport.addElement(aPropertyChangeListener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener aPropertyChangeListener) {
		propertyListenerSupport.removeElement(aPropertyChangeListener);
	}
	
	@Override
	@Column(1)
	public ComposedGraphInterface getGraph() {
		return graph;
	}


	@Override
	public void setGraph(ComposedGraphInterface newGraph) {
		graph = newGraph;
	}
}
