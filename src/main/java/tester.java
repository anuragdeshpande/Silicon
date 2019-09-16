import framework.guidewire.GuidewireInteract;
import framework.webdriver.BrowserFactory;

import java.io.IOException;

public class tester {
    public static void main(String[] args) throws IOException, InterruptedException {
        GuidewireInteract interact = BrowserFactory.getCurrentGuidewireBrowser();
        interact.getDriver().get("http://cc8dev/cc");
        interact.withDOM().injectInfoMessage("This is info test");
        Thread.sleep(1000);
        interact.withDOM().clearBannerMessage();
        interact.withDOM().injectWarningMessage("This is warning test");
        Thread.sleep(1000);
        interact.withDOM().clearBannerMessage();
        interact.withDOM().injectSuccessMessage("This is Success test");
        Thread.sleep(1000);
        interact.withDOM().clearBannerMessage();
        interact.withDOM().injectDangerMessage("This is Danger test");
        Thread.sleep(1000);
        interact.withDOM().clearBannerMessage();


    }
}
