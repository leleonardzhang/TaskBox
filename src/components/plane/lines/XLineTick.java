package components.plane.lines;

import java.awt.Stroke;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;




@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"line", "tick"})
@EditablePropertyNames({})
public class XLineTick implements PlaneLineTickInterface{
	
	private PlaneLineInterface line;
	private PlaneTickInterface tick;
	public static final int MARGIN = 25;


	/*
	 * @param newX the x on Y-axis
	 * @param newY the y on Y-axis
	 * @param newLength the length of line
	 * @param newStroke stroke of line
	 */
	public XLineTick(int newX, int newY, int newLength, Stroke newStroke, String newText) {
		setLine(new XLine(newX, newY, newLength, newStroke));
		setTick(new PlaneTick(newX - MARGIN, newY, newText));
		getTick().setX(getTick().getX() - getTick().getWidth()/2);
	}
	
	
	@Override
	public PlaneLineInterface getLine() {
		return line;
	}

	@Override
	public void setLine(PlaneLineInterface newLine) {
		line = newLine;
	}

	@Override
	public PlaneTickInterface getTick() {
		return tick;
	}

	@Override
	public void setTick(PlaneTickInterface newTick) {
		tick = newTick;
	}

}
