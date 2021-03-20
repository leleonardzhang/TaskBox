package components.mathEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import components.listenersupport.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"helper", "image", "result"})
@EditablePropertyNames({"result"})
public class MathEditorPanel {
	private String helper = "";
	private String result = "";
	private int imageHeight = 100, imageWidth = 400;
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private MathExpressionImageInterface image = new MathExpressionImage(0, 0, imageHeight, imageWidth, "src/image/image.png");
	

	public MathExpressionImageInterface getImage() {
		return image;
	}
	
	public void setImage(MathExpressionImageInterface newImage) {
		image = newImage;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String newResult) {
		String oldResult = result;
		result = newResult;
		render(result);
		System.err.println("rendered");
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "result", oldResult, result));
		getImage().setImageFileName("src/image/latex.png");
		getImage().setHeight(imageHeight);
		getImage().setWidth(imageWidth);
	}
	
	
	
	public void render(String latexString) {
		TeXFormula formula = new TeXFormula(latexString);
		TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		icon.setInsets(new Insets(5, 5, 5, 5));
		BufferedImage image = new BufferedImage(icon.getIconWidth(),
				icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
		JLabel jl = new JLabel();
		jl.setForeground(new Color(0, 0, 0));
		icon.paintIcon(jl, g2, 0, 0);
		imageHeight = image.getHeight();
		imageWidth = image.getWidth();
		File outputfile = new File("src/image/latex.png");
		try {
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getHelper() {
		return helper;
	}
	
	public void setHelper(String newHelper) {
		String oldHelper = helper;
		helper = newHelper;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "helper", oldHelper, helper));
	}
	
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyListenerSupport.addElement(aListener);    
    }
	
	public void superScript(String exponent) {
		setHelper("^{" + exponent + "}");
	}
	
	public void fraction(String enumerator, String denominator) {
		setHelper("\\frac{" + enumerator + "}{" + denominator + "}");
	}
	
	public void dotProduct() {
		setHelper("\\cdot");
	}
	
	public void crossProduct() {
		setHelper("\\times");
	}
	
	public void subscript(String expression, String subscript) {
		setHelper(expression + "_{" + subscript + "}");
	}
	
	public void squareRoot(String expression) {
		setHelper("\\sqrt{" + expression + "}");
	}
	
	public void nthRoot(String n, String expression) {
		setHelper("\\sqrt[" + n + "]{" + expression + "}");
	}
	
	public void plusMinus() {
		setHelper("\\pm");
	}
	
	public void lessEqual() {
		setHelper("\\le");
	}
	
	public void greaterEqual() {
		setHelper("\\ge");
	}
	
	public void notEqual() {
		setHelper("\\neq");
	}
	
	public void percentage() {
		setHelper("\\%");
	}
	
	public void approximateEqual() {
		setHelper("\\approx");
	}
	
	public void equivalent() {
		setHelper("\\equiv");
	}
	
	public void alpha() {
		setHelper("\\alpha");
	}
	
	public void beta() {
		setHelper("\\beta");
	}
	
	public void delta() {
		setHelper("\\delta");
	}
	
	public void epsilon() {
		setHelper("\\epsilon");
	}
	
	public void gamma() {
		setHelper("\\gamma");
	}
	
	public void varepsilon() {
		setHelper("\\varepsilon");
	}
	
	public void zeta() {
		setHelper("\\zeta");
	}
	
	public void integral(String lowerBound, String upperBound) {
		setHelper("\\int_{" + lowerBound + "}{" + upperBound + "}");
	}
	
	
	
	
	
	
}
