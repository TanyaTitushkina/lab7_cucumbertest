package poms;

import controls.*;
import core.AbstractPOM;
import core.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateArticle extends AbstractPOM {

    public CreateArticle(WebDriver driver){
        super(driver);
    }


    @FindBy(xpath = "//div[label[contains(text(),'Title')]]/input[contains(@id,'edit-title')]")
    public WebTextInput titleInput;

    @FindBy(xpath = "//textarea[@class='cke_source cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr']")
    public WebTextInput textAreaInput;

    @FindBy(xpath = "//body")
    private WebElement contentInput;

    @FindBy(xpath = "//a[@title='Source']")
    public WebLink sourceFormatLnk;

    public void fillInContent(String value){
        driver.switchTo().frame(0);
        contentInput.sendKeys(value);
        driver.switchTo().defaultContent();
    }

    public void clearContent(){
        driver.switchTo().frame(0);
        contentInput.clear();
        driver.switchTo().defaultContent();
    }

    public void checkContent(String value){
        driver.switchTo().frame(0);
        Helpers.check2StringIfEquals(contentInput.getText(), value);
        driver.switchTo().defaultContent();
    }

    @FindBy(xpath = "//label[contains(text(),'Tags')]/following-sibling::input")
    public WebTextInput tagInput;

    @FindBy(xpath = "//input[@id='edit-field-image-0-upload']")
    public WebFileInput imageInput;

    @FindBy(xpath = "//label[contains(text(),'Alternative text')]/following-sibling::input")
    public WebTextInput altText;

    @FindBy(xpath = "//input[@id='edit-field-image-0-remove-button']")
    public WebButton removeImageBtn;

    @FindBy(xpath = "//summary[text()='Menu settings']")
    public WebElement menuSettings;

    @FindBy(xpath = "//input[@id='edit-menu-enabled']")
    public WebCheckBox provideMenuLink;

    @FindBy(xpath = "//input[@value='Save']")
    public WebButton saveBtn;

    @FindBy(xpath = "//input[@id='edit-preview']")
    public WebButton previewBtn;


}
