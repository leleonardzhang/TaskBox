package components.observableshapes;

import java.awt.Color;

public interface ObservableRectangleInterface extends ObservableShapeInterface{
	boolean getFilled();
	void setFilled(boolean isFilled);
	Color getColor();
	void setColor(Color newColor);
}
