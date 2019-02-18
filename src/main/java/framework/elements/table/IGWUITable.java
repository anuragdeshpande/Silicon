package framework.elements.table;

import org.openqa.selenium.By;

public interface IGWUITable extends IUITable {

    By TOOLBAR_REFERENCE = By.xpath("./ancestor::div[2]/preceding-sibling::div[2]");
    By FIRST_PAGE_REFERENCE = By.cssSelector("a[data-qtip='First Page']");
    By LAST_PAGE_REFERENCE = By.cssSelector("a[data-qtip='Last Page']");
    By NEXT_PAGE_REFERENCE = By.cssSelector("a[data-qtip='Next Page']");
    By PREVIOUS_PAGE_REFERENCE = By.cssSelector("a[data-qtip='Previous Page']");
}
