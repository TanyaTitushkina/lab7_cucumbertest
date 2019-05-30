package poms;

import controls.WebButton;
import core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class People extends AbstractPOM {

    public People(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Add user']")
    public WebButton addUserBtn;

}
