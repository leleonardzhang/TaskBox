package components.mainpanel;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

import components.composedGraph.ComposedGraph;
import components.composedGraph.ComposedGraphInterface;
import components.page.pages.PageInterface;
import exporter.Exporter;
import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.Label;
import util.annotations.PreferredWidgetClass;
import util.annotations.PropertyNames;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"currentPage"})
@EditablePropertyNames({})
public class MainPanel implements MainPanelInterface{
	
	private List<PageInterface> pages = new ArrayList<PageInterface> ();
	private PageInterface currentPage;
	private int currentPageIndex = 0;
	
	public MainPanel() {
		
	}
	

	@Override
	public List<PageInterface> getPages() {
		return pages;
	}

	@Override
	public void setPages(List<PageInterface> newPages) {
		pages = newPages;
	}
	
	@PreferredWidgetClass(JButton.class)
	@Label(">")
	@Column(2)
	@Row(0)
	@Override
	public void nextPage() {
		if (pages.size() == 0) return;
		if (currentPageIndex == pages.size() - 1) {
			setCurrentPageIndex(0);
		}
		else {
			setCurrentPageIndex(getCurrentPageIndex() + 1);
		}
	}

	@PreferredWidgetClass(JButton.class)
	@Label("<")
	@Column(0)
	@Row(0)
	@Override
	public void prevPage() {
		if (pages.size() == 0) return;
		if (currentPageIndex == 0) {
			setCurrentPageIndex(pages.size() - 1);
		}
		else {
			setCurrentPageIndex(getCurrentPageIndex() - 1);
		}
	}

	@Override
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	@Override
	public void setCurrentPageIndex(int newPageIndex) {
		currentPageIndex = newPageIndex;
		setCurrentPage(pages.get(currentPageIndex));
	}

	@Override
	@Column(1)
	@ComponentWidth(200)
	public PageInterface getCurrentPage() {
		return currentPage;
	}

	@Override
	public void setCurrentPage(PageInterface newPage) {
		currentPage = newPage;
	}

	@Override
	public void clearPages() {
		pages.clear();
	}
	
	@Override
	public void addPage(PageInterface newPage) {
		getPages().add(newPage);
		newPage.addPropertyChangeListener(this);
		setCurrentPageIndex(getCurrentPageIndex());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
	}

}
