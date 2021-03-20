package components.mathEditor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import components.listenersupport.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@PropertyNames({"x", "y", "width", "height", "imageFileName"})
@EditablePropertyNames({})
public class MathExpressionImage implements MathExpressionImageInterface{
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private int x, y, height, width;
	private String imageFileName;

	public MathExpressionImage(int newX, int newY, int h, int w, String name) {
		setX(newX);
		setY(newY);
		setHeight(h);
		setWidth(w);
		setImageFileName(name);
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
	public String getImageFileName() {
		return imageFileName;
	}

	@Override
	public void setImageFileName(String newName) {
		String oldName = imageFileName;
		imageFileName = newName;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "imageFileName", oldName, imageFileName));
	}
}
	
