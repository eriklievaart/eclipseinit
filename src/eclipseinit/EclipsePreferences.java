package eclipseinit;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IExportedPreferences;
import org.eclipse.core.runtime.preferences.IPreferenceFilter;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.runtime.preferences.PreferenceFilterEntry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import eclipseinit.util.WsPaths;

public class EclipsePreferences {

	public static void init() throws Exception {
		File file = WsPaths.getEclipsePreferencesFile();
		IPreferencesService service = Platform.getPreferencesService();
		IExportedPreferences prefs = service.readPreferences(new FileInputStream(file));
		IPreferenceFilter filter = new IPreferenceFilter() {
			@Override
			public String[] getScopes() {
				return new String[] { InstanceScope.SCOPE, ConfigurationScope.SCOPE };
			}

			@Override
			public Map<String, PreferenceFilterEntry[]> getMapping(String scope) {
				return null;
			}
		};
		final IWorkbench workbench = PlatformUI.getWorkbench();

		workbench.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					service.applyPreferences(prefs, new IPreferenceFilter[] { filter });
					ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.CLEAN_BUILD, null);

				} catch (CoreException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}
