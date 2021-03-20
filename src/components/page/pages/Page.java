package components.page.pages;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextArea;
import components.listenersupport.PropertyListenerSupport;
import util.annotations.ComponentHeight;
import util.annotations.EditablePropertyNames;
import util.annotations.PreferredWidgetClass;
import util.annotations.PropertyNames;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"task", "answer"})
@EditablePropertyNames({"answer"})
public class Page implements PageInterface{
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private String answer, task;
	private int pageId;
	
	public Page(String newTask, String newAnswer, int newPageId) {
		setTask(newTask);
		setAnswer(newAnswer);
		setPageId(newPageId);
	}
	
	@PreferredWidgetClass(JTextArea.class)
	@ComponentHeight(100)
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
	@ComponentHeight(100)
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
}
