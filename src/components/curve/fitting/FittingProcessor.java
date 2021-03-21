package components.curve.fitting;

import java.util.ArrayList;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

public class FittingProcessor implements FittingProcessorInterface{
	public static final double WEIGHT = 1.0;
	private MyCurveFitter fitter = new MyCurveFitter();
    private ArrayList<WeightedObservedPoint> points = new ArrayList<WeightedObservedPoint>();
    
    @Override
    public void addFittingPoint(double x, double y) {
    	points.add(new WeightedObservedPoint(WEIGHT, x, y));
    }
    
    @Override
    public double[] process() {
    	return fitter.fit(points);
    }

}
