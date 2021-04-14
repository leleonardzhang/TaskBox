package components.caret;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import components.mathEditor.MathEditorPanel;

public class MathPanelCaretListener implements CaretListener{
	private MathEditorPanel panel;
	
	public MathPanelCaretListener(MathEditorPanel newPanel) {
		panel = newPanel;
	}

	@Override
	public void caretUpdate(CaretEvent evt) {
		panel.setCaretPosition(evt.getDot());
	}

}
