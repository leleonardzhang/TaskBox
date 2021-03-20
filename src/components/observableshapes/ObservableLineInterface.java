package components.observableshapes;

import java.awt.Stroke;

public interface ObservableLineInterface extends ObservableShapeInterface{
	Stroke getStroke();
	void setStroke(Stroke newStroke);
}
