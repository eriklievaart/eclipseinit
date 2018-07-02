package eclipseinit;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class EclipseLayout {
	private static final String JAVA_PERSPECTIVE = "org.eclipse.jdt.ui.JavaPerspective";
	private static final String DEBUG_PERSPECTIVE = "org.eclipse.debug.ui.DebugPerspective";

	private static final String INTRO_VIEW = "org.eclipse.ui.internal.introview";
	private static final String OUTLINE_VIEW = "org.eclipse.ui.views.ContentOutline";
	private static final String JAVADOC_VIEW = "org.eclipse.jdt.ui.JavadocView";
	private static final String DECLARATION_VIEW = "org.eclipse.jdt.ui.SourceView";
	private static final String TASK_VIEW = "org.eclipse.ui.views.TaskList";
	private static final String BREAKPOINT_VIEW = "org.eclipse.debug.ui.BreakpointView";
	private static final String DEBUG_VIEW = "org.eclipse.debug.ui.DebugView";
	private static final String PACKAGE_VIEW = "org.eclipse.jdt.ui.PackageExplorer";

	public static void doLayout() {
		final IWorkbench workbench = PlatformUI.getWorkbench();

		workbench.getDisplay().asyncExec(() -> {
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

			if (window != null) {
				IWorkbenchPage page = window.getActivePage();
				page.hideView(page.findView(INTRO_VIEW));
				page.close();
			}
			try {
				IWorkbenchPage java = workbench.showPerspective(JAVA_PERSPECTIVE, window);
				java.hideView(java.findView(OUTLINE_VIEW));
				java.hideView(java.findView(JAVADOC_VIEW));
				java.hideView(java.findView(DECLARATION_VIEW));

				IWorkbenchPage debug = workbench.showPerspective(DEBUG_PERSPECTIVE, window);
				debug.hideView(debug.findView(TASK_VIEW));
				debug.hideView(debug.findView(DEBUG_VIEW));
				debug.hideView(debug.findView(OUTLINE_VIEW));
				debug.hideView(debug.findView(BREAKPOINT_VIEW));

				workbench.showPerspective(JAVA_PERSPECTIVE, window);
				IViewPart pkg = java.findView(PACKAGE_VIEW);
				pkg.setFocus();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
