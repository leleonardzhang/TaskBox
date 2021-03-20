package components.observableshapes;

import components.observableshapes.ObservableShapeInterface;

public interface ObservableImageInterface extends ObservableShapeInterface{
	String getImageFileName();
	void setImageFileName(String newName);
}
