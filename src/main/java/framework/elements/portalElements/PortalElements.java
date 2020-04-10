package framework.elements.portalElements;

import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import org.openqa.selenium.By;

public class PortalElements {
    public static Identifier textBox(String ng_model_value){
        return new Identifier(By.cssSelector("input[type='text'][ng-model='"+ng_model_value+"']"), ElementType.TEXT_BOX);
    }

    public static Identifier button(String buttonName){
        By byReference = By.xpath("//*[contains(@class, 'gw-btn-primary') and contains(text(), '"+buttonName+"')]");
        return new Identifier(byReference, ElementType.BUTTON);
    }

    public static Identifier link(String linkText){
        return new Identifier(By.linkText(linkText), ElementType.LINK);
    }

    public static Identifier table(String valueOfListAttributeOnTableElement){
        return new Identifier(By.xpath("//table[@list='"+valueOfListAttributeOnTableElement+"']"), ElementType.TABLE);
    }
}
