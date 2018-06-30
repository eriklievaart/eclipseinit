package eclipseinit.util;

import java.io.File;

public class WsPaths {

	public static File getEclipsePreferencesFile() {
		return new File(getHomeDir(), "Development/git/ws/main/static/eclipse/oxygen.epf");
	}

	public static File getWorkspacesFile() {
		return new File(getHomeDir(), "Development/git/ws/main/static/workspaces.txt");
	}
	
	public static File getProjectDir(String name) {
		return new File(getHomeDir(), "Development/project/" + name + "/.project");
	}

	private static File getHomeDir() {
		return new File(System.getProperty("user.home"));
	}
}
