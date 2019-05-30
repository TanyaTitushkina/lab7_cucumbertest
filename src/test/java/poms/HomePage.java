package poms;

import core.AbstractPOM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPOM {

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void openArticle(String title) {
        driver.findElement(By.xpath("//h2[@class='node__title']/a[span[contains(text(),'" + title + "')]]")).click();
    }
}
