package framework.elements.selectbox;

import framework.constants.ReactionTime;
import framework.customExceptions.IncorrectCallException;
import framework.elements.Identifier;
import framework.logger.RegressionLogger;
import framework.webdriver.BrowserFactory;
import framework.webdriver.Interact;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UISelectBox extends UISelect {

    private List<WebElement> listElements;
    private Select select;
    private boolean shouldAccountForNone = false;

    public UISelectBox(WebElement element) {
        super(element);
        this.select = new Select(element);
        this.listElements = this.select.getOptions()
                .stream().filter(webElement -> !webElement.getText().equalsIgnoreCase("<none>")).collect(Collectors.toList());
        if(this.listElements.size() != this.getElementOptions().size()){
            shouldAccountForNone = true;
        }
    }

    public UISelectBox(Identifier identifier) {
        super(identifier);
        this.select = new Select(this.getElement());
        this.listElements = this.select.getOptions()
                .stream().filter(webElement -> !webElement.getText().equalsIgnoreCase("<none>")).collect(Collectors.toList());
        if(this.listElements.size() != this.getElementOptions().size()){
            shouldAccountForNone = true;
        }
    }

    public UISelectBox(Identifier identifier, ReactionTime reactionTime) {
        super(identifier, reactionTime);
        if (this.isPresent()) {
            this.select = new Select(this.getElement());
            this.listElements = this.select.getOptions()
                    .stream().filter(webElement -> !webElement.getText().equalsIgnoreCase("<none>")).collect(Collectors.toList());
            if(this.listElements.size() != this.getElementOptions().size()){
                shouldAccountForNone = true;
            }
        }
    }

    public List<WebElement> getElementOptions() {
        return this.select.getOptions();
    }

    @Override
    public void select(String selection) {
        this.select.selectByVisibleText(selection);
    }

    @Override
    public String selectRandom() {
        String optionToSelect = this.listElements.get(new Random(System.currentTimeMillis()).nextInt(this.listElements.size())).getText();
        this.select.selectByVisibleText(optionToSelect);
        return optionToSelect;
    }

    @Override
    public String select(int itemNumber) {
        if(shouldAccountForNone){
            itemNumber = itemNumber - 1;
        }
        String selection;
        try{
            selection = this.listElements.get(itemNumber).getText();
            this.select.selectByVisibleText(selection);
            return selection;
        }catch (ArrayIndexOutOfBoundsException aie){
            throw new IncorrectCallException("By default <none> option is filtered. Hence when a item number is given it is subtracted by 1. If you would like to override this behavior" +
                    " then please use .getElementOptions() or .listElements and pass the option to select(String) method.");
        }
    }

    @Override
    public String selectByPartial(String selection) {
        for (WebElement listItem : this.listElements) {
            if (listItem.getText().contains(selection)) {
                String selectedText = listItem.getText();
                this.select.selectByVisibleText(selectedText);
                return selectedText;
            }
        }
        String message = "Could not find a partial match for "+selection+" in "+ Arrays.toString(this.listElements.stream().map(WebElement::getText).toArray())+" skipping to next step";
        RegressionLogger.getTestLogger().warn(message);
        return null;
    }

    @Override
    public boolean hasOption(String selection) {
        return this.getOptions().contains(selection);
    }

    @Override
    public List<String> getOptions() {
        return this.listElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public String selectFirstExisting(String[] selections) {
        for (String selection : selections) {
            if (this.hasOption(selection)) {
                this.select.selectByVisibleText(selection);
                return selection;
            }
        }

        String message = "Could not find any matching selection for "+Arrays.toString(selections)+" in "+ Arrays.toString(this.listElements.stream().map(WebElement::getText).toArray())+" skipping to next step";
        RegressionLogger.getTestLogger().warn(message);
        return null;
    }

    // These methods handle "MultiSelect" boxes

    public void multipleSelect(List<String> selections) {
        for (String selection : selections) {
            this.select.selectByVisibleText(selection);
        }
    }

    public void multipleDeselect(List<String> selections) {
        for (String selection : selections) {
            this.select.deselectByValue(selection);
        }
    }

    public void multipleDeselectAll() {
        this.select.deselectAll();
    }

    public String getFirstSelectedOption(){
        Interact interact = BrowserFactory.getCurrentBrowser();
        try{
            String value = ((String) ((JavascriptExecutor) interact.getDriver()).executeScript("var value = " +
                    "document.getElementsByName(\"" + identifier.getReferenceValue() + "\")[0].value; return value;"));
            if(value != null && !value.isEmpty()){
                return value;
            } else {
                throw new JavascriptException("Cannot find element");
            }
        } catch (Exception e){
        // not generally recommended to catch Super Class type.
        // Making an exception here to indicate if there is ANY issue in getting value from JS, fallback is in catch block
            interact.withDOM().injectInfoMessage("Getting selected Item using selenium. This might take some time");
            String text = this.select.getFirstSelectedOption().getText();
            interact.withDOM().clearBannerMessage();
            return text;
        }
    }

    public String[] getAllSelectedOptions(){
        return (String[]) this.select.getAllSelectedOptions().stream().map(WebElement::getText).toArray();
    }

    @Override
    public void scrollToTop(){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementsByName(\""+getElement().getAttribute("name")+"\")[0].scrollTop -= document.getElementsByName(\""+getElement().getAttribute("name")+"\")[0].scrollHeight");
    }

    @Override
    public void scrollToTop(String name){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementsByName(\""+name+"\")[0].scrollTop -= document.getElementsByName(\""+name+"\")[0].scrollHeight");
    }

    @Override
    public void scrollToBottom(){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementsByName(\""+getElement().getAttribute("name")+"\")[0].scrollTop += document.getElementsByName(\""+getElement().getAttribute("name")+"\")[0].scrollHeight");
    }

    @Override
    public void scrollToBottom(String name){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementsByName(\""+name+"\")[0].scrollTop += document.getElementsByName(\""+name+"\")[0].scrollHeight");
    }

    @Override
    public void scrollIntoView(){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementsByName(\""+getElement().getAttribute("name")+"\")[0].scrollIntoViewIfNeeded()");
    }

    @Override
    public void scrollIntoView(String name){
        WebDriver driver = BrowserFactory.getCurrentGuidewireBrowser().getDriver();
        ((JavascriptExecutor) driver).executeScript("document.getElementsByName(\""+name+"\")[0].scrollIntoViewIfNeeded()");
    }
}
