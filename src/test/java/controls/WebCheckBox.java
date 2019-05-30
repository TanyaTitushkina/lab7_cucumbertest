package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WebCheckBox extends WebTypifiedElement {

    public WebCheckBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    //copy from yandex
    /**
     * Finds label corresponding to this checkbox using "following-sibling::label" xpath.
     *
     * @return {@code WebElement} representing label or {@code null} if no label has been found.
     */
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

    /**
     * The same as {@link #getLabelText()}.
     *
     * @return Text of the checkbox label or {@code null} if no label has been found.
     */
    public String getText() {
        return getLabelText();
    }

    /**
     * Selects checkbox if it is not already selected.
     */
    public void select() {
        log.info("Select " + getName());
        if (!isSelected()) {
            getWrappedElement().click();
        }
        Assert.assertTrue(isSelected(), getName() + " is selected");
    }

    /**
     * Deselects checkbox if it is not already deselected.
     */
    private void deselect() {
        if (isSelected()) {
            getWrappedElement().click();
        }
    }

    /**
     * Selects checkbox if passed value is {@code true} and deselects otherwise.
     */
    private void set(boolean value) {
        if (value) {
            select();
        } else {
            deselect();
        }
    }
}
