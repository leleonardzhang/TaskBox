package main;

import bus.uigen.ObjectEditor;
import components.curve.composedGraph.ComposedGraph;
import components.curve.composedGraph.ComposedGraphInterface;
import components.curve.point.CurvePoint;

public class Test {

	public static void main(String[] args) {
		ComposedGraphInterface graph = new ComposedGraph(500, 700);
		ObjectEditor.edit(graph);
	}

}
