package components.box;

import java.beans.PropertyChangeListener;

public interface BoxInterface {
	String getText();
	void setText(String newText);
	void addPropertyChangeListener(PropertyChangeListener aListener);
}
