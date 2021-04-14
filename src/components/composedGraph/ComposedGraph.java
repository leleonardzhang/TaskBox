package components.composedGraph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import components.curve.fitting.Plotter;
import components.curve.line.CurveLine;
import components.curve.point.CurvePoint;
import components.observableshapes.ObservableLineInterface;
import components.observableshapes.ObservablePointInterface;
import components.plane.plane.Plane;
import components.plane.plane.PlaneInterface;
import javafx.util.Pair;
import util.annotations.Column;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.Label;
import util.annotations.PreferredWidgetClass;
import util.annotations.PropertyNames;
import util.annotations.Row;
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
	@Row(0)
	@Column(0)
	@ComponentWidth(100)
	public PlaneInterface getPlane() {
		return plane;
	}
	
	@Override
	@PreferredWidgetClass(JButton.class)
	@Label("Add Point")
	@Row(1)
	@Column(0)
	@ComponentWidth(100)
	public void addPoint() {
		points.add(new CurvePoint(x, y, RADIUS));
	}
	
	@Override
	@PreferredWidgetClass(JButton.class)
	@Label("Set Start Point")
	@Row(1)
	@Column(1)
	@ComponentWidth(100)
	public void startPoint(ObservablePointInterface aPoint) {
		startPoint = aPoint;
	}
	
	@Override
	@PreferredWidgetClass(JButton.class)
	@Label("Add Line")
	@Row(2)
	@Column(0)
	@ComponentWidth(100)
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
				(int) (aPoint.getY() + aPoint.getRadius() - prevPoint.getY() - prevPoint.getRadius()),
				Color.BLACK
				);
		lines.add(newLine);
		pairs.add(new Pair<ObservablePointInterface, ObservableLineInterface> (aPoint, newLine));
		pairs.add(new Pair<ObservablePointInterface, ObservableLineInterface> (prevPoint, newLine));
		prevPoint = aPoint;
		
	}
	
	@Override
	@PreferredWidgetClass(JButton.class)
	@Label("Delete Line")
	@Row(2)
	@Column(2)
	@ComponentWidth(100)
	public void deleteLine(ObservableLineInterface aLine) {
		lines.remove(aLine);
	}
	
	@Override
	@PreferredWidgetClass(JButton.class)
	@Label("Delete Point")
	@Row(1)
	@Column(2)
	@ComponentWidth(100)
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
	@PreferredWidgetClass(JButton.class)
	@Label("Fit")
	@Row(3)
	@Column(1)
	@ComponentWidth(100)
	public void fit() {
		if (points.size() == 0) return;
		plotter.plot();
	}

	@Override
	public List<ObservableLineInterface> getFittingLines() {
		return fittingLines;
	}

	@Override
	@PreferredWidgetClass(JButton.class)
	@Label("Add Line Auto")
	@Row(2)
	@Column(1)
	@ComponentWidth(100)
	public void addLines() {
		if (points.size() < 2) return;
		lines.clear();
		for (int i = 1; i < points.size(); i ++) {
			lines.add(new CurveLine(
					(int) (points.get(i - 1).getX() + points.get(i - 1).getRadius()), 
					(int) (points.get(i - 1).getY() + points.get(i - 1).getRadius()), 
					points.get(i).getX() - points.get(i - 1).getX(), 
					points.get(i).getY() - points.get(i - 1).getY(), 
					Color.BLACK));
		}
	}

	@Override
	@PreferredWidgetClass(JButton.class)
	@Label("Delete Lines")
	@Row(3)
	@Column(0)
	@ComponentWidth(100)
	public void deleteLines() {
		lines.clear();
	}
}
