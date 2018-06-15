# projectlink
Eclipse plugin for linking projects to workspaces.
When starting eclipse, this plugin checks a config file and adds any missing projects to the workspace.

This plugin is extremely basic and you will probably want to modify the source code before use.
When eclipse starts, this plugin reads the following file:

[user_home]/Development/git/ws/main/static/workspaces.txt

Each line in the file is expected to list the name of a workspace, next a space, then space separated project names.
If the name of the current workspace matches the first word on the line, the projects will be imported.
Each project is expected to have a .project file in

[user_home]/Development/project/[project_name]/.project

That's all it does.


### Editing / Building the plugin
Use Eclipse PDE to edit / build the code.
Create a new empty pde project and copy the sources in.
Use the export function to create the jar.
