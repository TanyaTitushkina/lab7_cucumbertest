package poms;

import controls.*;
import core.AbstractPOM;
import core.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class Login extends AbstractPOM {



    public Login(WebDriver driver){
        super(driver);
    }


//    WebElement pageTitle = driver.findElement(pageTitleLocator);
//    WebElement usernameInput = driver.findElement(By.xpath("//div[label[text()='Username']]/input[@name='name']"));
//    WebElement passwordInput = driver.findElement(By.xpath("//div[label[text()='Password']]/input[@name='pass']"));
//    WebElement loginBtn = driver.findElement(By.xpath("//input[@name='op' and @value='Log in']"));

    //String values

//    By pageTitleLoc = By.xpath("//h1[contains(@class,'title page-title')]");
//    By usernameInputLoc = By.xpath("//div[label[text()='Username']]/input[@name='name']");
//    By passwordInputLoc = By.xpath("//div[label[text()='Password']]/input[@name='pass']");
//    By loginBtnLoc = By.xpath("//input[@name='op' and @value='Log in']");

//    @FindBy(xpath = "//h1[contains(@class,'title page-title')]")
//    public WebText pageTitle;

    @FindBy(xpath = "//div[label[text()='Username']]/input[@name='name']")
    public WebTextInput usernameInput;

    @FindBy(xpath = "//div[label[text()='Password']]/input[@name='pass']")
    public WebTextInput passwordInput;

    @FindBy(xpath = "//input[@name='op' and @value='Log in']")
    public WebButton loginBtn;


//    public void checkPageTitle(String value){
//        Assert.assertTrue(pageTitle.isDisplayed(), "Page title is displayed");
//        Helpers.check2StringIfEquals(pageTitle.getText(), "Log in");
//    }

}
