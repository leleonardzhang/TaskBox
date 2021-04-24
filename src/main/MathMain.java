package main;

import java.util.Vector;
import javax.swing.text.JTextComponent;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.oadapters.ClassAdapter;
import bus.uigen.oadapters.ObjectAdapter;
import components.caret.MathPanelCaretListener;
import components.mathEditor.MathEditorPanel;

public class MathMain {
	public static MathEditorPanel panel;
	public static OEFrame frame;
	
	public static void main(String[] args) {
		panel = new MathEditorPanel();
		frame = ObjectEditor.edit(panel);
		
		setCaretPositionListener();
		setButtonImage();
	}
	
	public static void setButtonImage() {
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "DotProduct", AttributeNames.ICON, "image/dot.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "CrossProduct", AttributeNames.ICON, "image/times.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Divide", AttributeNames.ICON, "image/div.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Neq", AttributeNames.ICON, "image/neq.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Ge", AttributeNames.ICON, "image/ge.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Le", AttributeNames.ICON, "image/le.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Approximate", AttributeNames.ICON, "image/approx.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Not", AttributeNames.ICON, "image/not.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Inf", AttributeNames.ICON, "image/inf.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Pm", AttributeNames.ICON, "image/pm.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Dot", AttributeNames.ICON, "image/dotx.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Ddot", AttributeNames.ICON, "image/ddotx.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "SuperScript", AttributeNames.ICON, "image/superscript.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "SubScript", AttributeNames.ICON, "image/subscript.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Fraction", AttributeNames.ICON, "image/fraction.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Sqrt", AttributeNames.ICON, "image/sqrt.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Floor", AttributeNames.ICON, "image/floor.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Ceil", AttributeNames.ICON, "image/ceil.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Integral", AttributeNames.ICON, "image/integral.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "Sum", AttributeNames.ICON, "image/sum.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "JavaExp", AttributeNames.ICON, "image/exp.png");
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "JavaSqrt", AttributeNames.ICON, "image/sqrt.png");
		
		
		ObjectEditor.setMethodAttribute(MathEditorPanel.class, "DotProduct", AttributeNames.EXPLANATION, "some text");
	}
	
	public static void setCaretPositionListener() {
		uiFrame aUIFrame = (uiFrame) frame;
		ClassAdapter adapter = (ClassAdapter) aUIFrame.getTopAdapter();
		Vector<ObjectAdapter> children = adapter.getChildrenVector();
		
		ObjectAdapter observableAdapter = null;
		
		for (ObjectAdapter child : children) {
			if (child.getComponentName().equals("mathExpression")) {
				observableAdapter = child;
				break;
			}
		}
		
		MathPanelCaretListener listener = new MathPanelCaretListener(panel);
		
		if (observableAdapter != null && (observableAdapter.getUIComponent().getPhysicalComponent()) instanceof JTextComponent) {
			((JTextComponent) observableAdapter.getUIComponent().getPhysicalComponent()).addCaretListener(listener);
		}
	}

}
