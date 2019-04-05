package eclipseinit;

import java.io.File;

import eclipseinit.util.FileLogger;

public class StartupJobs implements org.eclipse.ui.IStartup {

	@Override
	public void earlyStartup() {
		System.out.println("@ START ECLIPSEINIT @");
		FileLogger.with(new File("/tmp/log/eclipseinit"), logger -> {
			try {
				EclipseInfo info = new EclipseInfo();
				logger.log("workspace: " + info.getWorkspaceName());
				EclipsePreferences.init();
				new ProjectInstaller(logger).createProjects(info);

			} catch (Exception e) {
				logger.log(e.getClass().getSimpleName() + ": " + e.getMessage());
				throw new RuntimeException(e);
			}
		});
		System.out.println("@ END ECLIPSEINIT @");
	}
}
