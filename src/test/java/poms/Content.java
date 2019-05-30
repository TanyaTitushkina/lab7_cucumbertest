package poms;

import controls.*;
import core.AbstractPOM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.Helpers;

public class Content extends AbstractPOM {

    public Content(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//main//a[contains(.,'Add content')]")
    public WebButton addContentBtn;

    @FindBy(xpath = "//div[label[contains(text(), 'Action')]]/select[@id='edit-action']")
    public WebSelect selectAction;

    public WebElement editContentBtn(String title) {
        log.info("Edit content of '" + title + "'" );
        return driver.findElement(By.xpath("//td[a[contains(text(), '" + title + "')]]/following-sibling::td[@class='views-field views-field-operations']//a[contains(.,'Edit')]"));
    }

    @FindBy(xpath = "//input[@id='edit-submit']")
    public WebButton applyBtn;

    @FindBy(xpath = "//input[@id='edit-submit'][@value='Delete']")
    public WebButton deleteBtn;

    @FindBy(xpath = "//div[@class='messages messages--status']")
    WebText infoMsg;

    public void selectContent(String title) {
        log.info("Select the item with title '" + title + "'");
        driver.findElement(By.xpath("//td[a[contains(text(), '" + title + "')]]/preceding-sibling::td")).click();
    }

    public void goToContent(String title) {
        log.info("Go to the item with title '" + title + "'" );
        driver.findElement(By.xpath("//a[contains(text(), '" + title + "')]")).click();
    }

    public void checkContentMsgStatus(String msg) {
        log.info("Check if the info message equals to '" + msg + "'" );
        Helpers.check2StringIfContains(infoMsg.getText(), msg);
    }

    public void checkContentType(String title, String type) {
        log.info("Check Type for '" + title + "'" );
        Helpers.check2StringIfEquals(driver.findElement(By.xpath("//td[a[contains(text(), '"
                + title + "')]]/following-sibling::td[@class='views-field views-field-type']")).getText(), type);
    }

    public void checkContentStatus(String title, String status) {
        log.info("Check Status for '" + title + "'" );
        Helpers.check2StringIfEquals(driver.findElement(By.xpath("//td[a[contains(text(), '"
                + title + "')]]/following-sibling::td[@class='views-field views-field-status']")).getText(), status);
    }

}
