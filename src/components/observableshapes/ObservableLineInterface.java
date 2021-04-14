package components.observableshapes;

import java.awt.Color;
import java.awt.Stroke;

public interface ObservableLineInterface extends ObservableShapeInterface{
	Stroke getStroke();
	void setStroke(Stroke newStroke);
	Color getColor();
	void setColor(Color newColor);
}
