package components.mainpanel;

import java.beans.PropertyChangeListener;
import java.util.List;

import components.composedGraph.ComposedGraphInterface;
import components.page.pages.PageInterface;

public interface MainPanelInterface extends PropertyChangeListener{
	List<PageInterface> getPages();
	void setPages(List<PageInterface> newPages);
	void nextPage();
	void prevPage();
	int getCurrentPageIndex();
	void setCurrentPageIndex(int newPageIndex);
	PageInterface getCurrentPage();
	void setCurrentPage(PageInterface newPage);
	void clearPages();
	void addPage(PageInterface newPage);
}
