package components.observableshapes;

import java.beans.PropertyChangeListener;

public interface ObservableShapeInterface extends ShapeInterface{
	void addPropertyChangeListener(PropertyChangeListener aListener);
}
