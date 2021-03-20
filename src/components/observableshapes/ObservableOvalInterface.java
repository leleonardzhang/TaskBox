package components.observableshapes;

import java.awt.Color;

import components.observableshapes.ObservableShapeInterface;

public interface ObservableOvalInterface extends ObservableShapeInterface{
	boolean getFilled();
	void setFilled(boolean isFilled);
	Color getColor();
	void setColor(Color newColor);
}
