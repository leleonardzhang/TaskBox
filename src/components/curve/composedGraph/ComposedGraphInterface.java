package components.curve.composedGraph;

import java.util.List;

import components.observableshapes.ObservableLineInterface;
import components.observableshapes.ObservablePointInterface;
import components.plane.plane.PlaneInterface;

public interface ComposedGraphInterface {

	void deleteLine(ObservableLineInterface aLine);

	void delelePoint(ObservablePointInterface aPoint);

	void addLine(ObservablePointInterface aPoint);

	void startPoint(ObservablePointInterface aPoint);

	void addPoint();

	PlaneInterface getPlane();

	List<ObservableLineInterface> getLines();

	List<ObservablePointInterface> getPoints();

}
