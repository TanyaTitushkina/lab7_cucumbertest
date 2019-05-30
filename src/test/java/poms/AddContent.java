package poms;

import controls.WebButton;
import controls.WebLink;
import core.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddContent extends AbstractPOM {

    public AddContent(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//ul/li/a/span[text()='Article']")
    public WebLink articleLnk;

    @FindBy(xpath = "//ul/li/a/span[text()='Basic page']")
    public WebLink basicPageLnk;

}
