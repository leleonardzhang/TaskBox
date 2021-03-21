package components.curve.composedGraph;

import java.util.ArrayList;
import java.util.List;

import components.curve.fitting.Plotter;
import components.curve.line.CurveLine;
import components.curve.point.CurvePoint;
import components.observableshapes.ObservableLineInterface;
import components.observableshapes.ObservablePointInterface;
import components.plane.plane.Plane;
import components.plane.plane.PlaneInterface;
import javafx.util.Pair;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"points", "lines", "plane", "fittingLines"})
@EditablePropertyNames({"points"})
public class ComposedGraph implements ComposedGraphInterface{
	private int x, y;
	private List<ObservablePointInterface> points = new ArrayList<ObservablePointInterface>();
	private List<ObservableLineInterface> lines = new ArrayList<ObservableLineInterface>();
	private List<ObservableLineInterface> fittingLines = new ArrayList<ObservableLineInterface>();
	private ObservablePointInterface startPoint, prevPoint;
	private PlaneInterface plane;
	private List<Pair<ObservablePointInterface, ObservableLineInterface>> pairs = new ArrayList<Pair<ObservablePointInterface, ObservableLineInterface>> ();
	public static final int RADIUS = 5;
	private Plotter plotter = new Plotter(this);
	
	
	public ComposedGraph(int newX, int newY) {
		plane = new Plane(newX, newY);
		x = newX;
		y = newY;
	}
	
	@Override
	public List<ObservablePointInterface> getPoints() {
		return points;
	}
	
	@Override
	public List<ObservableLineInterface> getLines(){
		return lines;
	}
	
	@Override
	public PlaneInterface getPlane() {
		return plane;
	}
	
	@Override
	public void addPoint() {
		points.add(new CurvePoint(x, y, RADIUS));
	}
	
	@Override
	public void startPoint(ObservablePointInterface aPoint) {
		startPoint = aPoint;
	}
	
	@Override
	public void addLine(ObservablePointInterface aPoint) {
		if (startPoint == null) {
			startPoint = points.get(0);
		}
		if (prevPoint == null) {
			prevPoint = startPoint;
		}
		if (prevPoint == aPoint) {
			return;
		}
		ObservableLineInterface newLine = new CurveLine((int) (prevPoint.getX() + prevPoint.getRadius()), 
				(int) (prevPoint.getY() + prevPoint.getRadius()), 
				(int) (aPoint.getX() + aPoint.getRadius() - prevPoint.getX() - prevPoint.getRadius()), 
				(int) (aPoint.getY() + aPoint.getRadius() - prevPoint.getY() - prevPoint.getRadius()));
		lines.add(newLine);
		pairs.add(new Pair<ObservablePointInterface, ObservableLineInterface> (aPoint, newLine));
		pairs.add(new Pair<ObservablePointInterface, ObservableLineInterface> (prevPoint, newLine));
		prevPoint = aPoint;
		
	}
	
	@Override
	public void deleteLine(ObservableLineInterface aLine) {
		lines.remove(aLine);
	}
	
	@Override
	public void delelePoint(ObservablePointInterface aPoint) {
		if (startPoint == aPoint) {
			startPoint = null;
		}
		if (prevPoint == aPoint) {
			prevPoint = null;
		}
		for (Pair <ObservablePointInterface, ObservableLineInterface> pair : pairs) {
			if (pair.getKey() == aPoint) {
				deleteLine(pair.getValue());
			}
		}
		points.remove(aPoint);
	}

	@Override
	public void setLines(List<ObservableLineInterface> newLines) {
		lines = newLines;
	}

	@Override
	public void setPoints(List<ObservablePointInterface> newPoints) {
		points = newPoints;
	}

	@Override
	public void fit() {
		plotter.plot();
	}

	@Override
	public List<ObservableLineInterface> getFittingLines() {
		return fittingLines;
	}
}
