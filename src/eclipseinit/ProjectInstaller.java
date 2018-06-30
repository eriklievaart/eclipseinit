package eclipseinit;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import eclipseinit.util.FileTool;
import eclipseinit.util.WsPaths;

public class ProjectInstaller {

	public void createProjects(EclipseInfo info) {
		for (String required : getRequiredProjects(info.getWorkspaceName())) {
			if (!info.installedProjects.contains(required)) {
				createProject(info.workspace, required);
			}
		}
	}

	private void createProject(IWorkspace workspace, String name) {
		try {
			File prejectReference = WsPaths.getProjectDir(name);
			Path path = new Path(prejectReference.getAbsolutePath());
			IProjectDescription description = workspace.loadProjectDescription(path);

			IProject project = workspace.getRoot().getProject(description.getName());
			project.create(description, null);
			project.open(null);
			
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	private List<String> getRequiredProjects(String name) {
		try {
			for (String line : FileTool.readLinesTrimmed(WsPaths.getWorkspacesFile())) {
				if (line.startsWith(name + " ")) {
					return Arrays.asList(line.replaceFirst("^\\S++\\s++", "").split("\\s++"));
				}
			}
			return Arrays.asList();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
