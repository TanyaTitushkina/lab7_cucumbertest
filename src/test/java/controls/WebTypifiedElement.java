package controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public abstract class WebTypifiedElement extends TypifiedElement {

    public WebTypifiedElement(WebElement wrappedElement){
        super(wrappedElement);
    }
    protected Logger log = LogManager.getLogger(this.getClass().getSimpleName());

    @Override
    public void click() {
        log.info("Click on element '" + getName() + "'");
        super.click();
    }

    public void checkIfEquals(String value){
        log.info("Verify text of '" + getName() + "' element");
        log.info("1st: '" + getText() + "'");
        log.info("2nd: '" + value + "'");
        Assert.assertEquals(getText(), value);
    }

    public void isDisplayedAssertion(){
        Assert.assertTrue(this.isDisplayed());
    }

}
