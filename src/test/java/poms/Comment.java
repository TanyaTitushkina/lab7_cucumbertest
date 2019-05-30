package poms;

import controls.*;
import core.AbstractPOM;
import core.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Comment extends AbstractPOM {

    public Comment(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[label[contains(text(),'Subject')]]/input[@id='edit-subject-0-value']")
    public WebTextInput subjectInput;

    @FindBy(xpath = "//body")
    public WebElement commentInput;

    @FindBy(xpath = "//input[@id='edit-submit']")
    public WebButton saveBtn;

    @FindBy(xpath = "//div[@class='comment__content']//a[@class='permalink']")
    public WebText subjectTxt;

    @FindBy(xpath = "//div[@class='comment__content']//p")
    public WebText commentTxt;

    public void fillInComment(String comment) {
        Helpers.sleep(1000);
        driver.switchTo().frame(0);
        commentInput.sendKeys(comment);
        driver.switchTo().defaultContent();
    }

    public void checkCommentSubject(String subj) {
        Helpers.check2StringIfEquals(subjectTxt.getText(), subj);
    }

    public void checkCommentText(String comment) {
        Helpers.check2StringIfEquals(commentTxt.getText(), comment);
    }
}
