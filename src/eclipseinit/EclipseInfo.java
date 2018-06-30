package eclipseinit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class EclipseInfo {

	public final IWorkspace workspace = ResourcesPlugin.getWorkspace();
	public final Set<String> installedProjects = getInstalledProjects(workspace.getRoot());
	
	private Set<String> getInstalledProjects(IWorkspaceRoot root) {
		Set<String> set = new HashSet<>();
		for (IProject project : root.getProjects()) {
			set.add(project.getName());
		}
		return Collections.unmodifiableSet(set);
	} 
	
	public String getWorkspaceName() {
		return workspace.getRoot().getLocation().lastSegment();
	}
}
