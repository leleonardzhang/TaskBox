package parser;

import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;

public class TexTreeBuilder {
	private DefaultMutableTreeNode root;
	private JavaExpressionParser parser = new JavaExpressionParser();
	
	
	public String build(DefaultMutableTreeNode aRoot) {
		root = aRoot;
		String resultString = "";
		for (Character c : getTexVector(root)) {
			resultString += c;
		}
		return resultString;
	}
	
	public Vector<Character> getTexVector(DefaultMutableTreeNode thisNode) {
		Vector<Character> childrenVector = new Vector<Character> ();
		for (int i = 0; i < thisNode.getChildCount(); i ++) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) thisNode.getChildAt(i);
			if (child.getChildCount() == 0) {
				if (((Vector<Character>) child.getUserObject()).get(0) == '/') {
					Vector<Character> fracVector = new Vector<Character> ();
					fracVector.add('\\');
					fracVector.add('f');
					fracVector.add('r');
					fracVector.add('a');
					fracVector.add('c');
					fracVector.add('{');
					fracVector = concat(fracVector, childrenVector);
					fracVector.add('}');
					fracVector.add('{');
					DefaultMutableTreeNode nextChild = (DefaultMutableTreeNode) thisNode.getChildAt(i + 1);
					if (nextChild.getChildCount() == 0) {
						fracVector = concat(fracVector, ((Vector<Character>) nextChild.getUserObject()));
					}
					else {
						fracVector = concat(fracVector, getTexVector(nextChild));
					}
					fracVector.add('}');
					i ++;
					childrenVector = fracVector;
				}
				else if (((Vector<Character>) child.getUserObject()).get(0) == '^') {
					Vector<Character> powerVector = new Vector<Character> ();
					powerVector = concat(childrenVector, powerVector);
					powerVector.add('^');
					powerVector.add('{');
					DefaultMutableTreeNode nextChild = (DefaultMutableTreeNode) thisNode.getChildAt(i + 1);
					if (nextChild.getChildCount() == 0) {
						powerVector = concat(powerVector, ((Vector<Character>) nextChild.getUserObject()));
					}
					else {
						powerVector = concat(powerVector, getTexVector(nextChild));
					}
					powerVector.add('}');
					i ++;
					childrenVector = powerVector;
				}
				else if (((Vector<Character>) child.getUserObject()).get(0) == '*') {
					DefaultMutableTreeNode nextChild = (DefaultMutableTreeNode) thisNode.getChildAt(i + 1);
					DefaultMutableTreeNode prevChild = (DefaultMutableTreeNode) thisNode.getChildAt(i - 1);
					Vector<Character> timesVector = new Vector<Character> ();
					timesVector = concat(childrenVector, timesVector);
					if (prevChild.getChildCount() == 0 && nextChild.getChildCount() == 0 
							&& parser.isNumber((Vector<Character>) prevChild.getUserObject()) 
							&& parser.isUnsignedVariable((Vector<Character>) nextChild.getUserObject())) {
					}
					else {
						timesVector.add(' ');
						timesVector.add('\\');
						timesVector.add('t');
						timesVector.add('i');
						timesVector.add('m');
						timesVector.add('e');
						timesVector.add('s');
						timesVector.add(' ');
						childrenVector = timesVector;
					}
				}
				
				else if (isSqrt((Vector<Character>) child.getUserObject()) 
						&& parser.isExpression((Vector<Character>) ((DefaultMutableTreeNode) thisNode.getChildAt(i + 2)).getUserObject())) {
					Vector<Character> fracVector = new Vector<Character> ();
					fracVector.add('\\');
					fracVector.add('s');
					fracVector.add('q');
					fracVector.add('r');
					fracVector.add('t');
					fracVector.add('{');
					DefaultMutableTreeNode nextChild = (DefaultMutableTreeNode) thisNode.getChildAt(i + 2);
					if (nextChild.getChildCount() == 0) {
						fracVector = concat(fracVector, ((Vector<Character>) nextChild.getUserObject()));
					}
					else {
						fracVector = concat(fracVector, getTexVector(nextChild));
					}
					fracVector.add('}');
					i += 3;
					childrenVector = fracVector;
				}
				
				else if (isExp((Vector<Character>) child.getUserObject())
						&& parser.isExpression((Vector<Character>) ((DefaultMutableTreeNode) thisNode.getChildAt(i + 2)).getUserObject())) {
					Vector<Character> fracVector = new Vector<Character> ();
					fracVector.add('e');
					fracVector.add('^');
					fracVector.add('{');
					DefaultMutableTreeNode nextChild = (DefaultMutableTreeNode) thisNode.getChildAt(i + 2);
					if (nextChild.getChildCount() == 0) {
						fracVector = concat(fracVector, ((Vector<Character>) nextChild.getUserObject()));
					}
					else {
						fracVector = concat(fracVector, getTexVector(nextChild));
					}
					fracVector.add('}');
					i += 3;
					childrenVector = fracVector;
				}
				
				
				
			}
			else {
				childrenVector = concat(childrenVector, getTexVector(child));
			}
		}
		
		if (thisNode.getChildCount() == 0) {
			childrenVector = concat(childrenVector, (Vector<Character>) thisNode.getUserObject());
		}
		
		return childrenVector;	
	}
	
	public Vector<Character> concat(Vector<Character> vectorA, Vector<Character> vectorB){
		Vector<Character> newVector = new Vector<Character> ();
		for (Character c : vectorA) {
			newVector.add(c);
		}
		for (Character c : vectorB) {
			newVector.add(c);
		}
		return newVector;
	}
	
	public boolean isSqrt(Vector<Character> aVector) {
		if (aVector.size() != 4) return false;
		return aVector.get(0) == 's' && aVector.get(1) == 'q' && aVector.get(2) == 'r' && aVector.get(3) == 't';
	}
	
	public boolean isExp(Vector<Character> aVector) {
		if (aVector.size() != 3) return false;
		return aVector.get(0) == 'e' && aVector.get(1) == 'x' && aVector.get(2) == 'p';
	}
	


}
