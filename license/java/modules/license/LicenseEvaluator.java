package ...;

import com.atlassian.plugin.Plugin;
import com.atlassian.plugin.PluginAccessor;
import com.atlassian.plugin.PluginInformation;
import com.atlassian.plugin.spring.scanner.annotation.component.JiraComponent;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.upm.api.license.PluginLicenseManager;

import javax.inject.Inject;
import java.util.Map;

@JiraComponent
public class LicenseEvaluator {

    @ComponentImport
    private PluginLicenseManager pluginLicenseManager;

    @ComponentImport
    private final PluginAccessor pluginAccessor;

    public static final String ATLASSIAN_LICENSING_ENABLED = "atlassian-licensing-enabled";

    @Inject
    public LicenseEvaluator(PluginLicenseManager pluginLicenseManager, PluginAccessor pluginAccessor) {
        this.pluginLicenseManager = pluginLicenseManager;
        this.pluginAccessor = pluginAccessor;
    }

    public boolean isValid() {
        if (!isLicenseEnabled()) {
            return true;
        }
        return pluginLicenseManager.getLicense().isDefined() &&
                pluginLicenseManager.getLicense().get().isValid();
    }

    public boolean isLicenseEnabled() {
        String pluginKey = pluginLicenseManager.getPluginKey();
        Plugin plugin = pluginAccessor.getPlugin(pluginKey);
        PluginInformation pluginInformation = plugin.getPluginInformation();
        Map<String, String> parameters = pluginInformation.getParameters();
        String licenseEnabledValue = parameters.get(ATLASSIAN_LICENSING_ENABLED);
        return Boolean.parseBoolean(licenseEnabledValue);
    }
}
