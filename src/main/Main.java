package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import bus.uigen.ObjectEditor;
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

        WeightedObservedPoint point = new WeightedObservedPoint(1.0,
            1.0,
            1.0);
        points.add(point);

        final double coeffs[] = fitter.fit(points);
        System.out.println(Arrays.toString(coeffs));
	}
}
