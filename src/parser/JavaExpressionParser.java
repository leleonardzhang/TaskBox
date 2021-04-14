package parser;

import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


public class JavaExpressionParser {
	public static final char COMMA = ',', PLUS = '+', MINUS = '-', TIMES = '*', DIVIDES = '/', CARET = '^', EQUALS = '=', LP = '(', RP = ')';
	public enum FunctionName{
		SQRT ("sqrt"),
		POW ("pow"),
		EXP ("exp"),
		LOG ("log"),
		SIN ("sin"),
		COS ("cos"),
		TAN ("tan"),
		MIN ("min"),
		MAX ("max");
		public String name;
		FunctionName(String newName){
			name = newName;
		}
	}
	
	
	public JavaExpressionParser(){
		
	}
	
	public Vector<Character> trim(Vector<Character> aVector){
		Vector<Character> newVector = new Vector<Character> ();
		for (Character c : aVector) {
			if (c != ' ') {
				newVector.add(c);
			}
		}
		return newVector;
	}
	
	public boolean isExpression(Vector<Character> aVector) {
		System.out.println("Testing Expression " + aVector);
		if (aVector.size() == 0) return false;
		return isAtomicExpression(aVector) || isAddSubExpression(aVector) || isMultiDivExpression(aVector) || isExpExpression(aVector) || isFunctionExpression(aVector) || isParenthesisExpression(aVector);
	}
	public boolean isAddSubExpression(Vector<Character> aVector) {
		System.out.println("Testing add-sub Expression " + aVector);
		if (aVector.size() == 0) return false;
		int pt = 0;
		while (pt < aVector.size() && 
				(!isAddOrSub(aVector.get(pt)) || !isExpression(truncate(aVector, 0, pt)) || !isExpression(truncate(aVector, pt + 1, aVector.size())))) {
			pt ++;
		}
		if (pt == aVector.size()) return false;
		return true;
	}
	public boolean isMultiDivExpression(Vector<Character> aVector) {
		System.out.println("Testing multi div Expression " + aVector);
		if (aVector.size() == 0) return false;
		int pt = 0;
		while (pt < aVector.size() && 
				(!isMultiOrDiv(aVector.get(pt)) || !isExpression(truncate(aVector, 0, pt)) || !isExpression(truncate(aVector, pt + 1, aVector.size())))) {
			pt ++;
		}
		if (pt == aVector.size()) return false;
		return true;
	}
	public boolean isExpExpression(Vector<Character> aVector) {
		if (aVector.size() == 0) return false;
		int pt = 0;
		while (pt < aVector.size() && 
				(!isCaret(aVector.get(pt)) || !isExpression(truncate(aVector, 0, pt)) || !isExpression(truncate(aVector, pt + 1, aVector.size())))) {
			pt ++;
		}
		if (pt == aVector.size()) return false;
		return true;
	}
	public boolean isFunctionExpression(Vector<Character> aVector) {
		System.out.println("Testing function Expression " + aVector);
		if (aVector.size() == 0) return false;
		int pt = 0;
		while (!isFunctionName(truncate(aVector, 0, pt)) && pt < aVector.size()) {
			pt ++;
		}
		if (aVector.size() == pt) return false;
		return isFunctionParameters(truncate(aVector, pt, aVector.size()));
	}
	public boolean isFunctionParameters(Vector<Character> aVector) {
		return isLeftParenthesis(aVector.get(0)) && isRightParenthesis(aVector.get(aVector.size()-1)) && isParameters(truncateBoth(aVector));
	}
	public boolean isParameters(Vector<Character> aVector) {
		if (aVector.size() == 0) return false;
		if (isExpression(aVector)) return true;
		int pt = 0;
		while (pt < aVector.size() && 
				(!isComma(aVector.get(pt)) || !isExpression(truncate(aVector, 0, pt)) || !isParameters(truncate(aVector, pt + 1, aVector.size())))) {
			pt ++;
		}
		if (pt == aVector.size()) return false;
		return true;
	}
	public boolean isParenthesisExpression(Vector<Character> aVector) {
		System.out.println("Testing parenthesis Expression " + aVector);
		if (aVector.size() == 0) return false;
		System.out.println("Parenthesis Expression " + truncateBoth(aVector));
		return isLeftParenthesis(aVector.get(0)) && isRightParenthesis(aVector.get(aVector.size() - 1)) && isExpression(truncateBoth(aVector));
	}
	public boolean isFunctionName(Vector<Character> aVector) {
		for (FunctionName functionName : FunctionName.values()) {
			if (compare(aVector, functionName.name)) {
				return true;
			}
		}
		return false;
	}
	public boolean isAtomicExpression(Vector<Character> aVector) {
		return isNumber(aVector) || isVariable(aVector);
	}
	public boolean isNumber(Vector<Character> aVector) {
		return isUnsignedNumber(aVector) || (isSign(aVector.get(0)) && isUnsignedNumber(truncateLeft(aVector)));
	}
	public boolean isUnsignedNumber(Vector<Character> aVector) {
		if (aVector.size() == 1) {
			return isDigit(aVector.get(0));
		}
		return isNonZeroDigit(aVector.get(0)) && isDigitString(truncateLeft(aVector));
	}
	public boolean isVariable(Vector<Character> aVector) {
		if (aVector.size() == 1) {
			return isUnsignedVariable(aVector);
		}
		return isUnsignedVariable(aVector) || (isSign(aVector.get(0)) && isUnsignedVariable(truncateLeft(aVector)));
	}
	public boolean isUnsignedVariable(Vector<Character> aVector) {
		if (aVector.size() == 1) {
			return isLetter(aVector.get(0));
		}
		return isLetter(aVector.get(0)) && isLetterDigitString(truncateLeft(aVector));
	}
	public boolean isDigitString(Vector<Character> aVector) {
		if (aVector.size() == 1) {
			return isDigit(aVector.get(0));
		}
		return isDigit(aVector.get(0)) && isDigitString(truncateLeft(aVector));
	}
	public boolean isLetterDigitString(Vector<Character> aVector) {
		if (aVector.size() == 1) {
			return isLetterOrDigit(aVector.get(0));
		}
		return isLetterOrDigit(aVector.get(0)) && isLetterDigitString(truncateLeft(aVector));
	}
	public boolean isLetterOrDigit(char aChar) {
		return isLetter(aChar) || isDigit(aChar);
	}
	public boolean isSign(char aChar) {
		return aChar == PLUS || aChar == MINUS; 
	}
	public boolean isNonZeroDigit(char aChar) {
		return aChar <= '9' && aChar >= '1';
	}
	public boolean isDigit(char aChar) {
		return Character.isDigit(aChar);
	}
	public boolean isLetter(char aChar) {
		return Character.isLetter(aChar);
	}
	public boolean isComma(char aChar) {
		return aChar == COMMA;
	}
	public boolean isRightParenthesis(char aChar) {
		return aChar == RP;
	}
	public boolean isLeftParenthesis(char aChar) {
		return aChar == LP;
	}
	public boolean isAddOrSub(char aChar) {
		return aChar == PLUS || aChar == MINUS;
	}
	public boolean isMultiOrDiv(char aChar) {
		return aChar == TIMES || aChar == DIVIDES;
	}
	public boolean isCaret(char aChar) {
		return CARET == aChar;
	}
	public Vector<Character> truncate(Vector<Character> aVector, int start, int end){
		Vector<Character> newVector = new Vector<Character>();
		for (int i = start; i < end; i ++) {
			newVector.add(aVector.get(i));
		}
		return newVector;
	}
	public Vector<Character> truncateLeft(Vector<Character> aVector){
		return truncate(aVector, 1, aVector.size());
	}
	public Vector<Character> truncateRight(Vector<Character> aVector){
		return truncate(aVector, 0, aVector.size()-1);
	}
	public Vector<Character> truncateBoth(Vector<Character> aVector){
		return truncate(aVector, 1, aVector.size()-1);
	}
	public int find(Vector<Character> aVector, char aChar, int start) {
		for (int i = start; i < aVector.size(); i ++) {
			if (aVector.get(i) == aChar) {
				return i;
			}
		}
		return -1;
	}
	public boolean compare(Vector<Character> aVector, String aString) {
		if (aVector.size() != aString.length()) {
			return false;
		}
		for (int i = 0; i < aVector.size(); i ++) {
			if (aVector.get(i) != aString.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}
