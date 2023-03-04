package ...;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractIssueWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

public class LicensedWebItemCondition extends AbstractIssueWebCondition {
    private LicenseEvaluator licenseEvaluator;

    public LicensedWebItemCondition(LicenseEvaluator licenseEvaluator) {
        this.licenseEvaluator = licenseEvaluator;
    }

    public boolean shouldDisplay(ApplicationUser user, Issue issue, JiraHelper jiraHelper) {
        return licenseEvaluator.isValid();
    }
}
