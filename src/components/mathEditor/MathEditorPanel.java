package components.mathEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import components.listenersupport.PropertyListenerSupport;
import parser.JavaExpressionTreeParser;
import parser.TexTreeBuilder;
import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.PreferredWidgetClass;
import util.annotations.PropertyNames;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"image", "texExpression", "javaExpression"})
@EditablePropertyNames({"texExpression", "javaExpression"})
public class MathEditorPanel {
	private String texExpression = "", javaExpression = "";
	private int imageHeight = 100, imageWidth = 400;
	private int caretPosition;
	private PropertyListenerSupport propertyListenerSupport = new PropertyListenerSupport();
	private MathExpressionImageInterface image = null;

	

	public MathExpressionImageInterface getImage() {
		return image;
	}
	
	
	public void setImage(MathExpressionImageInterface newImage) {
		image = newImage;
	}
	
	@Row(0)
	public String getTexExpression() {
		return texExpression;
	}
	
	@Row(1)
	public String getJavaExpression() {
		return javaExpression;
	}
	
	public void setJavaExpression(String newJavaExpression) {
		String oldJavaExpression = javaExpression;
		javaExpression = newJavaExpression;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "javaExpression", oldJavaExpression, javaExpression));
		
		Vector<Character> rootVector = new Vector<Character> ();
		for (int i = 0; i < javaExpression.length(); i ++) {
			rootVector.add(javaExpression.charAt(i));
		}
		
		JavaExpressionTreeParser treeParser = new JavaExpressionTreeParser(rootVector);
		TexTreeBuilder builder = new TexTreeBuilder();
		
		render(builder.build(treeParser.getRoot()));
		
		if (image == null) {
			image = new MathExpressionImage(0, 0, imageHeight, imageWidth, "data/latex.png");
		}
		
		getImage().setImageFileName("data/latex.png");
		getImage().setHeight(imageHeight);
		getImage().setWidth(imageWidth);
	}
	
	
	public void setTexExpression(String newTexExpression) {
		String oldTexExpression = texExpression;
		texExpression = newTexExpression;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "texExpression", oldTexExpression, texExpression));
		
		render(texExpression);
		
		if (image == null) {
			image = new MathExpressionImage(0, 0, imageHeight, imageWidth, "data/latex.png");
		}
		
		getImage().setImageFileName("data/latex.png");
		getImage().setHeight(imageHeight);
		getImage().setWidth(imageWidth);
	}
	
	public int getCaretPosition() {
		return caretPosition;
	}
	
	public void setCaretPosition(int newPosition) {
		int oldPosition = caretPosition;
		caretPosition = newPosition;
		System.out.println(caretPosition);
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "caretPosition", oldPosition, caretPosition));
	}
	
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyListenerSupport.addElement(aListener);    
    }
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(0)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void dotProduct() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\cdot");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(1)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void crossProduct() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\times");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(2)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void divide() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\div");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(3)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void neq() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\neq");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(4)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void ge() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\ge");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(5)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void le() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\le");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(6)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void approximate() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\approx");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(7)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void not() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\not");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(8)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void inf() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\infty");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(2)
	@Column(9)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void pm() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\pm");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(3)
	@Column(5)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void dot() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\dot{x}");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(3)
	@Column(6)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void ddot() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\ddot{x}");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(3)
	@Column(0)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void superScript() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "^{x}");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(3)
	@Column(1)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void subScript() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "_{x}");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(4)
	@Column(0)
	@ComponentHeight(60)
	@ComponentWidth(30)
	public void fraction() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\frac{a}{b}");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(3)
	@Column(2)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void sqrt() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\sqrt{a}");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(3)
	@Column(3)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void floor() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\lfloor x \\rfloor");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(3)
	@Column(4)
	@ComponentHeight(30)
	@ComponentWidth(30)
	public void ceil() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\lceil x \\rceil");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(4)
	@Column(2)
	@ComponentHeight(60)
	@ComponentWidth(30)
	public void integral() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\int^{b}_{a}");
		setTexExpression(buffer.toString());
	}
	
	@PreferredWidgetClass(JButton.class)
	@Row(4)
	@Column(1)
	@ComponentHeight(60)
	@ComponentWidth(30)
	public void sum() {
		StringBuffer buffer = new StringBuffer(getTexExpression());
		buffer.insert(caretPosition, "\\sum^{b}_{a}");
		setTexExpression(buffer.toString());
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
		File outputfile = new File("data/latex.png");
		try {
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
