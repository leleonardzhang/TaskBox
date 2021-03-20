package components.box;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextArea;

import components.listenersupport.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.PreferredWidgetClass;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"text"})
@EditablePropertyNames({})

public class SubmittedAnswerBox implements BoxInterface{
	protected String text = "";
	protected PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	
	@Override
	@PreferredWidgetClass(JTextArea.class)
	public String getText() {
		return text;
	}
	
	@Override
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "text", oldText, text));
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyListenerSupport.addElement(aListener);    
    }
}
