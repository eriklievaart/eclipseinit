package eclipseinit;

public class StartupJobs implements org.eclipse.ui.IStartup {

	@Override
	public void earlyStartup() {
		try {
			EclipseInfo info = new EclipseInfo();
			if (info.installedProjects.isEmpty()) {
				EclipsePreferences.init();
				EclipseLayout.doLayout();
			}
			new ProjectInstaller().createProjects(info);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
