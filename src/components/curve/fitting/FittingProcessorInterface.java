package components.curve.fitting;

public interface FittingProcessorInterface {
	void addFittingPoint(double x, double y);
	double[] process();
}
