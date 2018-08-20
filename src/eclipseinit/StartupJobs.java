package eclipseinit;

public class StartupJobs implements org.eclipse.ui.IStartup {

	@Override
	public void earlyStartup() {
		try {
			EclipseInfo info = new EclipseInfo();
			EclipsePreferences.init();
			new ProjectInstaller().createProjects(info);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
