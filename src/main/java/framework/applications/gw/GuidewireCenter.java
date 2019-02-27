package framework.applications.gw;

import framework.enums.Environment;
import framework.webdriver.BrowserFactory;

abstract public class GuidewireCenter implements GWOperations{
    @Override
    public void openEnvironment(Environment environment) {
        BrowserFactory.getNewChromeBrowser().getDriver().get(environment.getEnvironmentUrl());
    }
}
