package framework.guidewire.pages;

import framework.elements.Identifier;
import org.openqa.selenium.By;

public class GWIDs {
    public static final Identifier LIST_OPTIONS = new Identifier(By.cssSelector("div.x-boundlist.x-boundlist-floating.x-boundlist-default.x-layer.x-border-box:not([style*='display: none'])"));
    public static final Identifier QUICK_JUMP = new Identifier(By.id("QuickJump-inputEl"));
    public static final Identifier ERROR_MESSAGE = new Identifier(By.xpath("//div[@class='message']/img[@class='error_icon']/parent::div"));
}
