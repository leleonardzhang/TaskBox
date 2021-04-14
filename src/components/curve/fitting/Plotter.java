package components.curve.fitting;

import java.awt.Color;
import java.util.Arrays;

import components.composedGraph.ComposedGraphInterface;
import components.curve.line.CurveLine;
import components.observableshapes.ObservablePointInterface;
import components.plane.plane.PlaneInterface;

public class Plotter {
	private ComposedGraphInterface graph;
	private AngleTimeFunction function = new AngleTimeFunction();
	private FittingProcessorInterface fitter = new FittingProcessor();
	
	public Plotter(ComposedGraphInterface aGraph) {
		graph = aGraph;
	}
	
	public void plot() {
		double planeX = graph.getPlane().getX();
		double planeY = graph.getPlane().getY();
		
  		double xScale = (graph.getPlane().getXMax() - graph.getPlane().getXMin())  / (PlaneInterface.Y_LENGTH * graph.getPlane().getYTickNumber() / (graph.getPlane().getYTickNumber() + 1));
		double yScale = (graph.getPlane().getYMax() - graph.getPlane().getYMin()) / (PlaneInterface.X_LENGTH * graph.getPlane().getXTickNumber() / (graph.getPlane().getXTickNumber() + 1));
		for (ObservablePointInterface point : graph.getPoints()) {
			fitter.addFittingPoint((point.getX() + point.getRadius() - planeX) * xScale + graph.getPlane().getXMin(), (planeY - point.getY() - point.getRadius()) * yScale + graph.getPlane().getYMin());	
		}
		graph.getFittingLines().clear();
		
		double[] coeff = fitter.process();
		
		System.out.println(Arrays.toString(coeff));
		
		for (double t = 0.2; t < 20; t += 0.2) {
			
			double yValuePrev = function.value(t - 0.2, coeff);
			double yValue = function.value(t, coeff);
			
			int yPlotPrev = (int) (planeY - (yValuePrev - graph.getPlane().getYMin()) / yScale - 5.0);
			int xPlotPrev = (int) ((t - 0.2 - graph.getPlane().getXMin()) / xScale + planeX - 5.0);
			int yPlot = (int) (planeY - (yValue - graph.getPlane().getYMin()) / yScale - 5.0);
			int xPlot = (int) ((t - graph.getPlane().getXMin()) / xScale + planeX - 5.0);
			graph.getFittingLines().add(new CurveLine(xPlotPrev, yPlotPrev, xPlot - xPlotPrev, yPlot - yPlotPrev, Color.RED));
		}
	
	}
}
