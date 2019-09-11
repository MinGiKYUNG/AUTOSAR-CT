package dms.konkuk.autosar.ct.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import dms.konkuk.autosar.ct.dialog.*;
import dms.konkuk.autosar.ct.editor.SwsEditor;
import dms.konkuk.autosar.ct.model.SwsItemProvider;

public class AddSwsItem extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		SwsItemProvider sip = SwsItemProvider.getInstance();
		AddSwsItemDialog dialog = new AddSwsItemDialog(window.getShell());
		dialog.setNo(sip.getBiggestNo()+1);
		dialog.open();
		
		if (dialog.getSwsItem() != null)	{
			sip.getSwsItems().add(dialog.getSwsItem());
			IWorkbenchPage page = window.getActivePage();
			SwsEditor view = (SwsEditor) page.findView(SwsEditor.ID);
			view.getViewer().refresh();
		}			
		return null;
	}

}
