package dms.konkuk.autosar.ct.birt;


import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.birt.report.viewer.utilities.WebViewer;

public class BirtView extends ViewPart {
	
	public static final String ID = "autosar.view.birt";

	public void createPartControl(Composite parent) {

		String path = ReportFactory.getReportFactory().GetReportPath();
        Browser browser = new Browser(parent, SWT.NONE);
        System.out.print("birt:: "+path);
        WebViewer.display(path, WebViewer.HTML, browser, "frameset");	
        }

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub	
	}
}