package components.plane.lines;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import components.listenersupport.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"x", "y", "text"})
@EditablePropertyNames({})
public class PlaneTick implements PlaneTickInterface{
	
	private int x, y, height, width;
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private String text;

	public PlaneTick(int newX, int newY, String newText) {
		setX(newX);
		setY(newY);
		setText(newText);
	}
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "text", oldText, text));
	}

	@Override
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
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
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "x", oldX, x));
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int newY) {
		int oldY = y;
		y = newY;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "y", oldY, y));
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setWidth(int newWidth) {
		int oldWidth = width;
		width = newWidth;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "width", oldWidth, width));
	}

	@Override
	public void setHeight(int newHeight) {
		int oldHeight = height;
		height = newHeight;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "height", oldHeight, height));
	}

}
