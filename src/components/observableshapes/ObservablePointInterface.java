package components.observableshapes;

import java.beans.PropertyChangeListener;

public interface ObservablePointInterface{
	int getX();
	void setX(int newX);
	int getY();
	void setY(int newY);
	double getAngle();
	void setAngle(double newAngle);
	double getRadius();
	void setRadius(double newRadius);
	void addPropertyChangeListener(PropertyChangeListener aListener);
}
