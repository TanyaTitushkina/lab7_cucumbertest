package controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class WebLink extends WebTypifiedElement {

    public WebLink(WebElement wrappedElement){
        super(wrappedElement);
    }

    public String getReference() {
        return getWrappedElement().getAttribute("href");
    }

}
