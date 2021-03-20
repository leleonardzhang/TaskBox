package components.box;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextArea;

import components.listenersupport.PropertyListenerSupport;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.PreferredWidgetClass;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"text"})
@EditablePropertyNames({})
public class TaskBox implements BoxInterface{
	
	private String text = "";
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();

	@Override
	@PreferredWidgetClass(JTextArea.class)
	@ComponentWidth(200)
	@ComponentHeight(200)
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
