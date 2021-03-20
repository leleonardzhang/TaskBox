package components.curve.fitting;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;

public class AngleTimeFunction implements ParametricUnivariateFunction{

	@Override
	public double[] gradient(double t, double... parameters) {
		double y0 = parameters[0];
		double m = parameters[1];
		double l = parameters[2];
		double g = parameters[3];
		double lambda = parameters[4];
		DerivativeStructure y0Dev = new DerivativeStructure(5, 1, 0, y0);
		DerivativeStructure mDev = new DerivativeStructure(5, 1, 1, m);
		DerivativeStructure lDev = new DerivativeStructure(5, 1, 2, l);
		DerivativeStructure gDev = new DerivativeStructure(5, 1, 3, g);
		DerivativeStructure lambdaDev = new DerivativeStructure(5, 1, 4, lambda);
		DerivativeStructure hDev = gDev.divide(mDev.multiply(lDev)).subtract(lambdaDev.multiply(lambdaDev).divide(mDev.multiply(mDev).multiply(4))).sqrt();
		DerivativeStructure y = y0Dev.multiply(
				lambdaDev.negate().multiply(t).divide(mDev.multiply(2)).exp()).multiply(
						hDev.multiply(t).cos().add(
									lambdaDev.divide(mDev.multiply(hDev).multiply(2)).multiply(hDev.multiply(t).sin())
								)
						);
		return new double[] {
				y.getPartialDerivative(1, 0, 0, 0, 0),
				y.getPartialDerivative(0, 1, 0, 0, 0),
				y.getPartialDerivative(0, 0, 1, 0, 0),
				y.getPartialDerivative(0, 0, 0, 1, 0),
				y.getPartialDerivative(0, 0, 0, 0, 1)
		};
		
	}

	@Override
	public double value(double t, double... parameters) {
		double y0 = parameters[0];
		double m = parameters[1];
		double l = parameters[2];
		double g = parameters[3];
		double lambda = parameters[4];
		double h = Math.sqrt(g / m / l - lambda * lambda / 4 / m / m);
		return (y0 * Math.exp((-1) * lambda * t / 2 / m) * (Math.cos(h * t) + lambda / 2 / m / h * Math.sin(h * t)));
	}
}
