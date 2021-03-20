package components.observableshapes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import components.listenersupport.PropertyListenerSupport;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"x", "y", "text"})

public class ObservableStringShape implements ObservableStringShapeInterface{

	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private int x, y;
	private String text;
	
	public ObservableStringShape(int newX, int newY, String newText) {
		setX(newX);
		setY(newY);
		setText(newText);
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyListenerSupport.addElement(aListener);    
    }

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int newX) {
		int oldX = x;
		x = newX;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "x", oldX, x));
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int newY) {
		int oldY = y;
		y = newY;

		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "y", oldY, y));
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String newText) {
		String oldText = text;
		text = newText;

		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "text", oldText, text));
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWidth(int newWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int newHeight) {
		// TODO Auto-generated method stub
		
	}

	
	
}
