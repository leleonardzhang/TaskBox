package components.curve.point;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import components.listenersupport.PropertyListenerSupport;
import components.observableshapes.ObservablePointInterface;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"x", "y", "radius"})
@EditablePropertyNames({"x", "y"})
public class CurvePoint implements ObservablePointInterface{
	private int x, y;
	private double angle, radius;
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	
	
	public CurvePoint(int newX, int newY, int newRadius) {
		setX(newX);
		setY(newY);
		setRadius(newRadius);
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
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyListenerSupport.addElement(aListener);
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public void setAngle(double newAngle) {
		double oldAngle = angle;
		angle = newAngle;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "angle", oldAngle, angle));
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public void setRadius(double newRadius) {
		double oldRadius = radius;
		radius = newRadius;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "radius", oldRadius, radius));
	}



}
