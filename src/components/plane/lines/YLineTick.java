package components.plane.lines;

import java.awt.Stroke;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"line", "tick"})
@EditablePropertyNames({})
public class YLineTick implements PlaneLineTickInterface{
	
	private PlaneLineInterface line;
	private PlaneTickInterface tick;
	public static final int MARGIN = 20;


	/*
	 * @param newX the x on X-axis
	 * @param newY the y on X-axis
	 * @param newLength the length of line
	 * @param newStroke stroke of line
	 */
	public YLineTick(int newX, int newY, int newLength, Stroke newStroke, String newText) {
		setLine(new YLine(newX, newY, newLength, newStroke));
		setTick(new PlaneTick(newX, newY + MARGIN + getLine().getHeight(), newText));
		getTick().setY(getTick().getY() - getTick().getHeight()/2);
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
