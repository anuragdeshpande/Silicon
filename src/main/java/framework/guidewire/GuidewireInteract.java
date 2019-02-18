package framework.guidewire;

import framework.webdriver.Interact;
import org.openqa.selenium.WebDriver;

public class GuidewireInteract extends Interact {
    public GuidewireInteract(WebDriver driver) {
        super(driver);
    }

/*    public void withTabArrow(Identifier identifier) {
        WebElement element = findElement(identifier);
        Dimension size = element.getSize();
        BrowserFactory.getCurrentBrowser().getActions().moveToElement(element, size.getWidth() - 12, 10).click().build().perform();
    }

    @Override
    public UISelectBox withSelectBox(Identifier identifier) {
        this.findElement(CCIDs.ESCAPE_CLICKER).click();

        WebElement selectBox = findElement(identifier);
        selectBox.click();

        List<WebElement> listElements = new ArrayList<>(findElement(CCIDs.LIST_OPTIONS).findElements(By.tagName("li")));
        return new UISelectBox(selectBox, listElements);
    }

    @Override
    public UISelectBox withOptionalSelectBox(Identifier identifier) {
        this.findElement(CCIDs.ESCAPE_CLICKER).click();

        WebElement selectBox = null;
        List<WebElement> listElements = new ArrayList<>();

        try {
            selectBox = findOptional(identifier);
            if (selectBox != null) {
                selectBox.click();
                listElements = new ArrayList<>(findOptional(CCIDs.LIST_OPTIONS).findElements(By.tagName("li")));
            }
        } catch (TimeoutException t) {
            System.out.println("Element Not found.  Returning shell element.");
        }
        return new UISelectBox(selectBox, listElements);
    }*/
}
