package controls;

import org.openqa.selenium.WebElement;

import java.util.Optional;

public class WebTextInput extends WebTypifiedElement {

    public WebTextInput(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String sendKeys(String value){
        log.info("Populate '" + getName() + "' with value '" + value + "'");
        clear();
        getWrappedElement().sendKeys(value);
        checkIfEquals(value);
        return value;
    }

    @Override
    public String getText() {
        if ("textarea".equals(getWrappedElement().getTagName())) {
            return getWrappedElement().getText();
        }

        return Optional.ofNullable(getWrappedElement().getAttribute("value")).orElse("");
    }
}
