package projectlink;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

public class InstallProjects implements org.eclipse.ui.IStartup {

	@Override
	public void earlyStartup() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		
		Set<String> available = getInstalledProjects(workspace.getRoot());
		List<String> created = createProjects(workspace, available);
		
		if(!created.isEmpty()) {
			JOptionPane.showMessageDialog(null, "created projects: " + created);
		}
	}

	private Set<String> getInstalledProjects(IWorkspaceRoot root) {
		Set<String> set = new HashSet<>();
		for (IProject project : root.getProjects()) {
			set.add(project.getName());
		}
		return set;
	} 

	private List<String> createProjects(IWorkspace workspace, Set<String> skip) {
		List<String> created = new ArrayList<>();
		for (String required : getRequiredProjects(getName(workspace))) {
			if (!skip.contains(required)) {
				createProject(workspace, required);
				created.add(required);
			}
		}
		return created;
	}

	private String getName(IWorkspace workspace) {
		return workspace.getRoot().getLocation().lastSegment();
	}

	private void createProject(IWorkspace workspace, String name) {
		try {
			File prejectReference = new File(getHomeDir(), "Development/project/" + name + "/.project");
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
			File config = new File(getHomeDir(), "Development/git/ws/main/static/workspaces.txt");
			for (String line : readLinesTrimmed(config)) {
				if (line.startsWith(name + " ")) {
					return Arrays.asList(line.replaceFirst("^\\S++\\s++", "").split("\\s++"));
				}
			}
			return Arrays.asList();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private File getHomeDir() {
		return new File(System.getProperty("user.home"));
	}

	public static List<String> readLinesTrimmed(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				lines.add(line.trim());
				line = br.readLine();
			}
		}
		return lines;
	}
}
