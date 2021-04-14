package components.curve.line;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import components.listenersupport.PropertyListenerSupport;
import components.observableshapes.ObservableLineInterface;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"x", "y", "width", "height", "stroke", "color"})
public class CurveLine implements ObservableLineInterface{
	
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private int x, y, width, height;
	private Stroke stroke = new BasicStroke(1.0f);
	private Color color;

	public CurveLine(int newX, int newY, int newWidth, int newHeight, Color newColor) {
		setX(newX);
		setY(newY);
		setWidth(newWidth);
		setHeight(newHeight);
		setColor(newColor);
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
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int newWidth) {
		int oldWidth = width;
		width = newWidth;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "width", oldWidth, width));
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int newHeight) {
		int oldHeight = height;
		height = newHeight;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "height", oldHeight, height));
	}


	@Override
	public Stroke getStroke() {
		return stroke;
	}

	@Override
	public void setStroke(Stroke newStroke) {
		stroke = newStroke;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyListenerSupport.addElement(aListener);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color newColor) {
		Color oldColor = color;
		color = newColor;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "color", oldColor, color));
	}

}
