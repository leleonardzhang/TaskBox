package components.mainpanel;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import components.page.pages.PageInterface;
import exporter.Exporter;
import util.annotations.Column;
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
	@Row(2)
	@Column(1)
	@Override
	public void nextPage() {
		if (currentPageIndex == pages.size() - 1) {
			setCurrentPageIndex(0);
		}
		else {
			setCurrentPageIndex(getCurrentPageIndex() + 1);
		}
	}

	@PreferredWidgetClass(JButton.class)
	@Label("<")
	@Row(2)
	@Column(0)
	@Override
	public void prevPage() {
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
	@Row(0)
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
		Exporter.export(this, "src/data/mainpanel.json");
	}

}
