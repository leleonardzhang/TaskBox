package components.plane.plane;

import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.List;

import components.plane.lines.PlaneLineTickInterface;
import components.plane.lines.PlaneTick;
import components.plane.lines.PlaneTickInterface;
import components.plane.lines.XLineTick;
import components.plane.lines.YLineTick;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"xLines", "yLines", "xName", "yName", "xNameTick", "yNameTick", "xMin", "xMax", "yMin", "yMax"})
@EditablePropertyNames({"xName", "yName", "xMin", "xMax", "yMin", "yMax"})
public class Plane implements PlaneInterface{
	
	private List<PlaneLineTickInterface> xLines, yLines;
	private String xName = "", yName = "";
	private PlaneTickInterface xNameTick, yNameTick;
	private double xMin = 0, xMax = 0, yMin = 0, yMax = 0;
	public static final int X_STEP = 100, Y_STEP = 100, X_LINES = 5, Y_LINES = 5, X_LENGTH = 500, Y_LENGTH = 500, NAME_MARGIN = 25;
	
	
	public Plane(int newX, int newY) {
		xLines = new ArrayList<PlaneLineTickInterface> ();
		yLines = new ArrayList<PlaneLineTickInterface> ();
		for (int i = 0; i < X_LINES; i ++) {
			xLines.add(new XLineTick(newX, newY - i * X_STEP, X_LENGTH, new BasicStroke(0.5f), ""));
		}
		for (int i = 0; i < Y_LINES; i ++) {
			yLines.add(new YLineTick(newX + i * Y_STEP, newY - Y_LENGTH, Y_LENGTH, new BasicStroke(0.5f), ""));
		}
		
		xNameTick = new PlaneTick(newX + X_LENGTH, newY + NAME_MARGIN, "");
		yNameTick = new PlaneTick(newX - NAME_MARGIN*2, newY - Y_LENGTH, "");
		
	}
	
	

	@Override
	public List<PlaneLineTickInterface> getXLines() {
		return xLines;
	}

	@Override
	public List<PlaneLineTickInterface> getYLines() {
		return yLines;
	}

	@Override
	public String getXName() {
		return xName;
	}

	@Override
	public String getYName() {
		return yName;
	}



	@Override
	public void setXName(String newName) {
		xName = newName;
		getXNameTick().setText(getXName());
		
	}



	@Override
	public void setYName(String newName) {
		yName = newName;
		getYNameTick().setText(getYName());
	}



	@Override
	public PlaneTickInterface getXNameTick() {
		return xNameTick;
	}



	@Override
	public PlaneTickInterface getYNameTick() {
		return yNameTick;
	}



	@Override
	public double getXMax() {
		return xMax;
	}



	@Override
	public double getXMin() {
		return xMin;
	}



	@Override
	public double getYMax() {
		return yMax;
	}



	@Override
	public double getYMin() {
		return yMin;
	}



	@Override
	public void setXMax(double newValue) {
		xMax = newValue;
		if (getXMin() > getXMax()) {
			setXMin(getXMax());
		}
		adjustTick();
	}



	@Override
	public void setXMin(double newValue) {
		yMin = newValue;
		if (getXMax() < getXMin()) {
			setXMax(getXMin());
		}
		adjustTick();
	}



	@Override
	public void setYMax(double newValue) {
		yMax = newValue;
		if (getYMin() > getYMax()) {
			setYMin(getYMax());
		}
		adjustTick();
	}



	@Override
	public void setYMin(double newValue) {
		yMin = newValue;
		if (getYMax() < getYMin()) {
			setYMax(getYMin());
		}
		adjustTick();
	}



	@Override
	public void adjustTick() {
		double x_gap = (getXMax() - getXMin()) / (X_LINES - 1);
		double y_gap = (getYMax() - getYMin()) / (Y_LINES - 1);
		for (int i = 0; i < X_LINES; i ++) {
			getXLines().get(i).getTick().setText(String.format("%.2f", getXMin() + x_gap * i));
		}
		for (int i = 0; i < Y_LINES; i ++) {
			getYLines().get(i).getTick().setText(String.format("%.2f", getYMin() + y_gap * i));
		}
	}

}
