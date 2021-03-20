package components.listenersupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface PropertyListenerSupportInterface {
	int size();
	PropertyChangeListener elementAt(int index);
	boolean isFull();
	void addElement(PropertyChangeListener l);
	void removeElement(PropertyChangeListener l);
	void notifyAllListeners(PropertyChangeEvent event);
}
