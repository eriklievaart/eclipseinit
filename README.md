# projectlink
Eclipse plugin for initializing workspaces.
When starting eclipse for the first time, it loads global eclipse preferences from an epf file.
The java perspective and debug perspective are openened and all the views I rarely use are closed.
The plugin also checks a config file and adds any missing projects to the workspace on every startup.

Preferences are loaded from the following file:

[user_home]/Development/git/ws/main/static/eclipse/oxygen.epf

Workspaces are read from the following file:

[user_home]/Development/git/ws/main/static/workspaces.txt

Each line in the file is expected to list the name of a workspace, next a space, then space separated project names.
If the name of the current workspace matches the first word on the line, the projects will be imported.
Each project is expected to have a .project file in

[user_home]/Development/project/[project_name]/.project




### Editing / Building the plugin
Use Eclipse PDE to edit / build the code.
Create a new empty pde project and copy the sources in.
Use the export function to create the jar.
