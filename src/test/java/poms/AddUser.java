package poms;

import controls.*;
import core.AbstractPOM;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddUser extends AbstractPOM {

    public AddUser(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='edit-mail']")
    public WebTextInput emailInput;

    @FindBy(xpath = "//input[@id='edit-name']")
    public WebTextInput usernameAddUserInput;

    @FindBy(xpath = "//input[@id='edit-pass-pass1']")
    public WebTextInput passwordAddUserInput;

    @FindBy(xpath = "//input[@id='edit-pass-pass2']")
    public WebTextInput confirmPassInput;

    @FindBy(xpath = "//input[@name='status']")
    public WebRadio statusRb;

    @FindBy(xpath = "//input[@name='notify']")
    public WebCheckBox notifyUserOfNewAccountChb;

    @FindBy(xpath = "//input[@name='files[user_picture_0]']")
    public WebFileInput pictureFileInput;

    @FindBy(xpath = "//select[@name='timezone']")
    public WebSelect timeZoneSelect;

    @FindBy(xpath = "//input[@id='edit-submit']")
    public WebButton createNewAccount;

    @FindBy(xpath = "//div[@class='messages messages--status']")
    public WebText infoMessage;

    public void setStatusRb(String value){
        if(StringUtils.isBlank(value)){
            Assert.fail("Empty value for radio " + statusRb.getName());
        }
        if(StringUtils.equalsIgnoreCase(value, "Blocked")){
            statusRb.selectByValue("0");
        }else if(StringUtils.equalsIgnoreCase(value, "Active")){
            statusRb.selectByValue("1");
        }else{
            Assert.fail("Wrong value '" + value + "' for " + statusRb.getName());
        }
    }


}
