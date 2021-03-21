package components.plane.plane;

import java.util.List;

import components.plane.lines.PlaneLineTickInterface;
import components.plane.lines.PlaneTickInterface;

public interface PlaneInterface {
	public static final int TICK_LENGTH = 5, X_LENGTH = 500, Y_LENGTH = 500, NAME_MARGIN = 25;
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
	int getXTickNumber();
	int getYTickNumber();
	void setXTickNumber(int newXTickNumber);
	void setYTickNumber(int newYTickNumber);
	int getX();
	int getY();
	void setX(int newX);
	void setY(int newY);
	int getXStep();
	int getYStep();
	void setXStep(int newXStep);
	void setYStep(int newYStep);
	int getPrecision();
	void setPrecision(int newPrecision);
}
