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
@PropertyNames({"xLines", "yLines", "xName", "yName", "xNameTick", "yNameTick", "xMin", "xMax", "yMin", "yMax", "xTickNumber", "yTickNumber", "precision"})
@EditablePropertyNames({"xName", "yName", "xMin", "xMax", "yMin", "yMax", "xTickNumber", "yTickNumber", "precision"})
public class Plane implements PlaneInterface{
	private int x, y;
	private List<PlaneLineTickInterface> xLines = new ArrayList<PlaneLineTickInterface> (), yLines = new ArrayList<PlaneLineTickInterface> ();
	private String xName = "", yName = "";
	private PlaneTickInterface xNameTick, yNameTick;
	private double xMin = 0, xMax = 0, yMin = 0, yMax = 0;
	private int xTickNumber = 5, yTickNumber = 5, xStep = 100, yStep = 100;
	private int precision = 0;
	
	
	public Plane(int newX, int newY) {
		setX(newX);
		setY(newY);
		
		adjustTick();
		
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
		xMin = newValue;
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
		
		xLines.clear();
		yLines.clear();
		
		// x-axis and y-axis
		xLines.add(new XLineTick(getX(), getY(), X_LENGTH, new BasicStroke(0.5f), ""));
		yLines.add(new YLineTick(getX(), getY() - Y_LENGTH, Y_LENGTH, new BasicStroke(0.5f), ""));
		
		
		String precisionString = String.format("%d", getPrecision());
		String tickString = "%.".concat(precisionString).concat("f");
		
		
		double x_interval = (getXMax() - getXMin()) / (xTickNumber - 1);
		double y_interval = (getYMax() - getYMin()) / (yTickNumber - 1);
		
		for (int i = 1; i < yTickNumber; i ++) {
			xLines.add(new XLineTick(getX(), getY() - i * yStep, TICK_LENGTH, new BasicStroke(0.5f), String.format(tickString, getYMin() + y_interval * i)));
		}
		for (int i = 1; i < xTickNumber; i ++) {
			yLines.add(new YLineTick(getX() + i * xStep, getY() - TICK_LENGTH, TICK_LENGTH, new BasicStroke(0.5f), String.format(tickString, getXMin() + x_interval * i)));
		}
		
		
		
	}



	@Override
	public int getXTickNumber() {
		return xTickNumber;
	}



	@Override
	public int getYTickNumber() {
		return yTickNumber;
	}



	@Override
	public void setXTickNumber(int newXTickNumber) {
		xTickNumber = newXTickNumber;
		setXStep((int) Math.floor(X_LENGTH/xTickNumber));
		adjustTick();
	}



	@Override
	public void setYTickNumber(int newYTickNumber) {
		yTickNumber = newYTickNumber;
		setYStep((int) Math.floor(Y_LENGTH/yTickNumber));
		adjustTick();
	}



	@Override
	public int getX() {
		return x;
	}



	@Override
	public int getY() {
		return y;
	}



	@Override
	public void setX(int newX) {
		x = newX;
	}



	@Override
	public void setY(int newY) {
		y = newY;
	}



	@Override
	public int getXStep() {
		return xStep;
	}



	@Override
	public int getYStep() {
		return yStep;
	}



	@Override
	public void setXStep(int newXStep) {
		xStep = newXStep;
	}



	@Override
	public void setYStep(int newYStep) {
		yStep = newYStep;
	}



	@Override
	public int getPrecision() {
		return precision;
	}



	@Override
	public void setPrecision(int newPrecision) {
		precision = newPrecision;
		adjustTick();
	}

}
