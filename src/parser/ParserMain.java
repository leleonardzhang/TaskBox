package parser;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTree;

public class ParserMain{
	public static void main(String[] args) {
		String exp = "pow(1,2)";
		Vector<Character> rootVector = new Vector<Character> ();
		for (int i = 0; i < exp.length(); i ++) {
			rootVector.add(exp.charAt(i));
		}
		// System.out.println(parser.isExpression(rootVector))
		JavaExpressionTreeParser treeParser = new JavaExpressionTreeParser(rootVector);
		
		JFrame frame = new JFrame();
		frame.add(treeParser.getTree());
		TexTreeBuilder builder = new TexTreeBuilder();
		builder.build(treeParser.getRoot());
		frame.setVisible(true);
	}
	
}
