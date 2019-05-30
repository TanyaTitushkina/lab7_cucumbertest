package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebRadio extends WebTypifiedElement {

    public WebRadio(WebElement wrappedElement) {
        super(wrappedElement);
    }


    private WebElement getLabel() {
        try {
            return getWrappedElement().findElement(By.xpath("following-sibling::label"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Finds a text of the checkbox label.
     *
     * @return Label text or {@code null} if no label has been found.
     */
    private String getLabelText() {
        WebElement label = getLabel();
        return label == null ? null : label.getText();
    }

    public void selectByVisibleText(String text) {
        log.info("Select button with text '" + text + "' for " + getName());
        WebElement matchingButton = getButtons().stream()
                .filter(b -> text.equals(b.findElement(By.xpath("following-sibling::label")).getText()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Cannot locate label for radio button with text: %s", text)));

        selectButton(matchingButton);
    }

    //from yandex
    /**
     * Returns all radio buttons belonging to this group.
     *
     * @return A list of {@code WebElements} representing radio buttons.
     */
    private List<WebElement> getButtons() {
        String radioName = getWrappedElement().getAttribute("name");

        String xpath;
        if (radioName == null) {
            xpath = "self::* | following::input[@type = 'radio'] | preceding::input[@type = 'radio']";
        } else {
            xpath = String.format(
                    "self::* | following::input[@type = 'radio' and @name = '%s'] | " +
                            "preceding::input[@type = 'radio' and @name = '%s']",
                    radioName, radioName);
        }

        return getWrappedElement().findElements(By.xpath(xpath));
    }

    /**
     * Returns selected radio button.
     *
     * @return {@code WebElement} representing selected radio button or {@code null} if no radio buttons are selected.
     */
    private WebElement getSelectedButton() {
        return getButtons().stream()
                .filter(WebElement::isSelected)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No selected button"));
    }

    /**
     * Indicates if radio group has selected button.
     *
     * @return {@code true} if radio has selected button and {@code false} otherwise.
     */
    private boolean hasSelectedButton() {
        return getButtons().stream()
                .anyMatch(WebElement::isSelected);
    }

    /**
     * Selects first radio button that has a value matching the specified argument.
     *
     * @param value The value to match against.
     */
    public void selectByValue(String value) {
        log.info("Select value '" + value + "' for " + getName());
        WebElement matchingButton = getButtons().stream()
                .filter(b -> value.equals(b.getAttribute("value")))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Cannot locate radio button with value: %s", value)));

        selectButton(matchingButton);
    }

    /**
     * Selects a radio button by the given index.
     *
     * @param index Index of a radio button to be selected.
     */
    private void selectByIndex(int index) {
        List<WebElement> buttons = getButtons();

        if (index < 0 || index >= buttons.size()) {
            throw new NoSuchElementException(
                    String.format("Cannot locate radio button with index: %d", index));
        }

        selectButton(buttons.get(index));
    }

    /**
     * Selects a radio button if it's not already selected.
     *
     * @param button {@code WebElement} representing radio button to be selected.
     */
    private void selectButton(WebElement button) {
        if (!button.isSelected()) {
            button.click();
        }
    }


}
