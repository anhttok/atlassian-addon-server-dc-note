# atlassian-addon-server-dc-note

# Upgrade to Jira 9 guidelines

- Modify pom.xml file to upgrade jira-api version to 9.x.x.
- Add these changes to classes extended JiraWebActionSupport class.
  - Add @SupportedMethods(RequestMethod.GET) annotation at the top of the class.
  - Modify method name from "doExecute" to execute() and its returned output to supper.execute().
