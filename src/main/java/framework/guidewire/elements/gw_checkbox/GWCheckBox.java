package framework.guidewire.elements.gw_checkbox;

import framework.elements.Identifier;
import framework.elements.checkbox.UICheckbox;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GWCheckBox extends UICheckbox {

    public GWCheckBox(Identifier identifier) {
        super(identifier);
    }

    public GWCheckBox(WebElement element){
        super(element);
    }

    @Override
    public boolean isChecked() {
        WebElement checkBox = resolveCheckBox();
        return checkBox.getAttribute("class").contains("x-grid-checkcolumn-checked");
    }

    @Override
    public void mark() {
        // this function call will re-resolve the checkbox element
        WebElement checkBox = resolveCheckBox();
        if(!isChecked()){
            clickCheckbox(checkBox);
        }

    }

    @Override
    public void unmark() {
        // this function call will re-resolve the checkbox element
        WebElement checkBox = resolveCheckBox();
        if(isChecked()){
            clickCheckbox(checkBox);
        }
    }

    private WebElement resolveCheckBox(){
        return this.getElement().findElement(By.tagName("img"));
    }

    private void clickCheckbox(WebElement element){
        try{
            BrowserFactory.getCurrentBrowser().getActions().clickAndHold(element)
                    .moveByOffset(1, 1)
                    .release(element)
                    .build()
                    .perform();
        } catch (Exception e){
//            System.out.println("Clicked on the checkbox - but got an exception: "+e.getLocalizedMessage());
        }
    }


}
