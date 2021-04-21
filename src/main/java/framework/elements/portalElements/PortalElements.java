package framework.elements.portalElements;

import framework.elements.Identifier;
import org.openqa.selenium.By;

public class PortalElements {

    public static Identifier table(String valueOfListAttributeOnTableElement){
        return new Identifier(By.xpath("//table[@list='"+valueOfListAttributeOnTableElement+"']"), "HubTable");
    }
}
