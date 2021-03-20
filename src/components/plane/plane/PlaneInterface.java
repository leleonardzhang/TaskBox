package components.plane.plane;

import java.util.List;

import components.plane.lines.PlaneLineTickInterface;
import components.plane.lines.PlaneTickInterface;

public interface PlaneInterface {
	List<PlaneLineTickInterface> getXLines();
	List<PlaneLineTickInterface> getYLines();
	String getXName();
	String getYName();
	void setXName(String newName);
	void setYName(String newName);
	PlaneTickInterface getXNameTick();
	PlaneTickInterface getYNameTick();
	double getXMax();
	double getXMin();
	double getYMax();
	double getYMin();
	void setXMax(double newValue);
	void setXMin(double newValue);
	void setYMax(double newValue);
	void setYMin(double newValue);
	void adjustTick();
}
