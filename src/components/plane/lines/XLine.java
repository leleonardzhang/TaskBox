package components.plane.lines;

import java.awt.Stroke;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import components.listenersupport.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"x", "y", "height", "width", "stroke"})
@EditablePropertyNames({})
public class XLine implements PlaneLineInterface{
	
	private int x, y, height, width;
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private Stroke stroke;
	
	
	public XLine(int newX, int newY, int newLength, Stroke newStroke) {
		setX(newX);
		setY(newY);
		setHeight(0);
		setWidth(newLength);
		setStroke(newStroke);
	}
	
	

	@Override
	public Stroke getStroke() {
		return stroke;
	}

	@Override
	public void setStroke(Stroke newStroke) {
		Stroke oldStroke = stroke;
		stroke = newStroke;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "stroke", oldStroke, stroke));
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
