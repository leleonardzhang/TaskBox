package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import bus.uigen.ObjectEditor;
import components.curve.fitting.AngleTimeFunction;
import components.curve.fitting.MyCurveFitter;
import components.mainpanel.MainPanel;
import components.mainpanel.MainPanelInterface;
import components.mathEditor.MathEditorPanel;
import components.page.pages.Page;
import components.page.pages.PageInterface;
import components.plane.plane.Plane;
import components.plane.plane.PlaneInterface;


public class Main {
	public static void main(String[] args)  {
		MyCurveFitter fitter = new MyCurveFitter();
        ArrayList<WeightedObservedPoint> points = new ArrayList<WeightedObservedPoint>();

        
        points.add(new WeightedObservedPoint(1.0, 0.5, 6.4845));
        points.add(new WeightedObservedPoint(1.0, 1, 1.4687));
        points.add(new WeightedObservedPoint(1.0, 1.5, 0.1378));
        points.add(new WeightedObservedPoint(1.0, 2, -0.6861));
        points.add(new WeightedObservedPoint(1.0, 2.5, -1.1952));
        points.add(new WeightedObservedPoint(1.0, 3, -1.3816));
        points.add(new WeightedObservedPoint(1.0, 3.5, -1.2462));
        points.add(new WeightedObservedPoint(1.0, 4, -0.8393));
        

        final double coeffs[] = fitter.fit(points);
        
        for (int i = 0; i < coeffs.length; i ++) {
        	System.out.print(coeffs[i] + " ");
        	System.out.println();
        }
        
        AngleTimeFunction func = new AngleTimeFunction();
        
        for (double i = 0; i < 10; i += 0.5) {
        	System.out.printf("%f %f\n", i, func.value(i, coeffs));
        }
	}
}
