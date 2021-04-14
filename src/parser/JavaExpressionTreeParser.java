package parser;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class JavaExpressionTreeParser {
	private DefaultMutableTreeNode rootNode;
	private JTree tree;
	private JavaExpressionParser parser = new JavaExpressionParser();
	
	public JavaExpressionTreeParser(Vector<Character> aVector) {
		if (parser.isExpression(aVector)) {
			rootNode = new DefaultMutableTreeNode(aVector);
			tree = new JTree(rootNode);
			parse(rootNode);
		}
	}
	
	public void parse(DefaultMutableTreeNode thisNode) {
		Vector<Character> aVector = (Vector<Character>) thisNode.getUserObject();
		if (parser.isAtomicExpression(aVector)) {
			return;
		}
		
		if (parser.isAddSubExpression(aVector)) {
			int pt = 0;
			while (pt < aVector.size() && 
					(!parser.isAddOrSub(aVector.get(pt)) || !parser.isExpression(parser.truncate(aVector, 0, pt)) || !parser.isExpression(parser.truncate(aVector, pt + 1, aVector.size())))) {
				pt ++;
			}
			
			Vector<Character> vectorLeft = parser.truncate(aVector, 0, pt);
			DefaultMutableTreeNode childLeft = new DefaultMutableTreeNode(vectorLeft);
			
			Vector<Character> vectorMid = parser.truncate(aVector, pt, pt + 1);
			DefaultMutableTreeNode childMid = new DefaultMutableTreeNode(vectorMid);
			
			Vector<Character> vectorRight = parser.truncate(aVector, pt + 1, aVector.size());
			DefaultMutableTreeNode childRight = new DefaultMutableTreeNode(vectorRight);
			
			thisNode.add(childLeft);
			parse(childLeft);
			thisNode.add(childMid);
			thisNode.add(childRight);
			parse(childRight);
			return;
		}
		
		if (parser.isMultiDivExpression(aVector)) {
			int pt = 0;
			while (pt < aVector.size() && 
					(!parser.isMultiOrDiv(aVector.get(pt)) || !parser.isExpression(parser.truncate(aVector, 0, pt)) || !parser.isExpression(parser.truncate(aVector, pt + 1, aVector.size())))) {
				pt ++;
			}
			
			Vector<Character> vectorLeft = parser.truncate(aVector, 0, pt);
			DefaultMutableTreeNode childLeft = new DefaultMutableTreeNode(vectorLeft);
			
			Vector<Character> vectorMid = parser.truncate(aVector, pt, pt + 1);
			DefaultMutableTreeNode childMid = new DefaultMutableTreeNode(vectorMid);
			
			Vector<Character> vectorRight = parser.truncate(aVector, pt + 1, aVector.size());
			DefaultMutableTreeNode childRight = new DefaultMutableTreeNode(vectorRight);
			
			thisNode.add(childLeft);
			parse(childLeft);
			thisNode.add(childMid);
			thisNode.add(childRight);
			parse(childRight);
			return;
		}
		
		if (parser.isFunctionExpression(aVector)) {
			int pt = 0;
			while (!parser.isFunctionName(parser.truncate(aVector, 0, pt)) && pt < aVector.size()) {
				pt ++;
			}
			
			Vector<Character> vectorFunction = parser.truncate(aVector, 0, pt);
			DefaultMutableTreeNode childFunction = new DefaultMutableTreeNode(vectorFunction);
			
			Vector<Character> vectorLeft = parser.truncate(aVector, pt, pt + 1);
			DefaultMutableTreeNode childLeft = new DefaultMutableTreeNode(vectorLeft);
			
			Vector<Character> vectorMid = parser.truncate(aVector, pt + 1, aVector.size() - 1);
			DefaultMutableTreeNode childMid = new DefaultMutableTreeNode(vectorMid);
			
			Vector<Character> vectorRight = parser.truncate(aVector, aVector.size() - 1, aVector.size());
			DefaultMutableTreeNode childRight = new DefaultMutableTreeNode(vectorRight);
			
			thisNode.add(childFunction);
			thisNode.add(childLeft);
			thisNode.add(childMid);
			parseParameters(childMid);
			thisNode.add(childRight);
			return;
		}
		
		if (parser.isExpExpression(aVector)) {
			int pt = 0;
			while (pt < aVector.size() && 
					(!parser.isCaret(aVector.get(pt)) || !parser.isExpression(parser.truncate(aVector, 0, pt)) || !parser.isExpression(parser.truncate(aVector, pt + 1, aVector.size())))) {
				pt ++;
			}
			
			
			Vector<Character> vectorLeft = parser.truncate(aVector, 0, pt);
			DefaultMutableTreeNode childLeft = new DefaultMutableTreeNode(vectorLeft);
			
			Vector<Character> vectorMid = parser.truncate(aVector, pt, pt + 1);
			DefaultMutableTreeNode childMid = new DefaultMutableTreeNode(vectorMid);
			
			Vector<Character> vectorRight = parser.truncate(aVector, pt + 1, aVector.size());
			DefaultMutableTreeNode childRight = new DefaultMutableTreeNode(vectorRight);
			
			thisNode.add(childLeft);
			parse(childLeft);
			thisNode.add(childMid);
			thisNode.add(childRight);
			parse(childRight);
			return;
		}
		
		
		if (parser.isParenthesisExpression(aVector)) {
			Vector<Character> vectorLeft = new Vector<Character>();
			vectorLeft.add('(');
			
			Vector<Character> vectorMid = parser.truncateBoth(aVector);
			DefaultMutableTreeNode childMid = new DefaultMutableTreeNode(vectorMid);
			
			Vector<Character> vectorRight = new Vector<Character>();
			vectorRight.add(')');
			
			thisNode.add(new DefaultMutableTreeNode(vectorLeft));
			thisNode.add(childMid);
			thisNode.add(new DefaultMutableTreeNode(vectorRight));
			parse(childMid);
			return;
		}
		
		
		
	}
	
	public void parseParameters(DefaultMutableTreeNode thisNode) {
		Vector<Character> aVector = (Vector<Character>) thisNode.getUserObject();
		if (parser.isExpression(aVector)) {
			parse(thisNode);
			return;
		}
		
		int pt = 0;
		while (pt < aVector.size() && 
				(!parser.isComma(aVector.get(pt)) || !parser.isExpression(parser.truncate(aVector, 0, pt)) || !parser.isParameters(parser.truncate(aVector, pt + 1, aVector.size())))) {
			pt ++;
		}
		
		Vector<Character> vectorLeft = parser.truncate(aVector, 0, pt);
		DefaultMutableTreeNode childLeft = new DefaultMutableTreeNode(vectorLeft);
		
		Vector<Character> vectorMid = parser.truncate(aVector, pt, pt + 1);
		DefaultMutableTreeNode childMid = new DefaultMutableTreeNode(vectorMid);
		
		Vector<Character> vectorRight = parser.truncate(aVector, pt + 1, aVector.size());
		DefaultMutableTreeNode childRight = new DefaultMutableTreeNode(vectorRight);
		
		thisNode.add(childLeft);
		parse(childLeft);
		thisNode.add(childMid);
		thisNode.add(childRight);
		parseParameters(childRight);
		return;
		
	}
	
	public JTree getTree() {
		return tree;
	}
	
	public DefaultMutableTreeNode getRoot() {
		return rootNode;
	}
}
