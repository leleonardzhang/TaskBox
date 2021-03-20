package components.listenersupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PropertyListenerSupport implements PropertyListenerSupportInterface{
	
	private List<PropertyChangeListener> contents = new ArrayList<PropertyChangeListener> ();
	

	@Override
	public int size() {
		return contents.size();
	}

	@Override
	public PropertyChangeListener elementAt(int index) {
		return contents.get(index);
	}


	@Override
	public synchronized void addElement(PropertyChangeListener l) {
		contents.add(l);
	}

	@Override
	public synchronized void notifyAllListeners(PropertyChangeEvent event) {
		for (PropertyChangeListener l : contents) {
			l.propertyChange(event);
		}
	}

	@Override
	public synchronized void removeElement(PropertyChangeListener l) {
		contents.remove(l);
	}

	@Override
	public boolean isFull() {
		return false;
	}
	
}
