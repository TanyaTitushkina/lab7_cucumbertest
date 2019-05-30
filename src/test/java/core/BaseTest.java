package core;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import poms.Login;
import poms.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected Logger log ;//= LogManager.getLogger(this.getClass().getSimpleName());
    private static LocalDateTime timePoint = LocalDateTime.now();
    private String dateTime = timePoint.get(ChronoField.YEAR_OF_ERA) + "-" + timePoint.getMonth() + "-" + timePoint.getDayOfMonth() + "_"+ timePoint.getHour() + "-" + timePoint.getMinute() + "-" + timePoint.getSecond();
    private String testName= getClass().getSimpleName();
    protected WebDriver driver;
    protected Config conf = ConfigFactory.load();

    @BeforeClass
    public void beforeClass(){
        String logPath = "logs/" + testName + "_" + dateTime + "/" ;
        String logFileName = logPath + testName + "_" + dateTime + ".log";
        System.setProperty("logPath", logPath);
        System.setProperty("logFileName", logFileName);
        log = LogManager.getLogger(this.getClass().getSimpleName());
        log.info(this.getClass().getSimpleName() + " test started");
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = getDriver(conf.getString("browser"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        String url = conf.getString("adminUrl");
        log.info("Navigate to " + url);
        driver.get(url);
    }

    @AfterMethod(alwaysRun=true)
    public void afterMethod(ITestResult result){
        if(!result.isSuccess()){
//            takeScreenshot();
        }
//        driver.close();
        driver.quit();
    }
/*
    public void takeScreenshot(){
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        LocalDateTime timePoint = LocalDateTime.now();
        String name = System.getProperty("logPath")  + testName + "_" + timePoint.get(ChronoField.YEAR_OF_ERA) + "-" + timePoint.getMonth() + "-" + timePoint.getDayOfMonth() + " | "+ timePoint.getHour() + "-" + timePoint.getMinute() + "-" + timePoint.getSecond() + ".png";
        File DestFile=new File(name);
        log.info("Screenshot name is " + name);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void loginWithDefaultUser(){
        Login login = new Login(driver);
        Menu menu = new Menu(driver);
        menu.waitTitleToBe("Log in", 10);
//        login.checkPageTitle("Log in");


        login.usernameInput.sendKeys(conf.getString("userName"));
        login.passwordInput.sendKeys(conf.getString("password"));
        login.loginBtn.click();

//        Menu menu = new Menu(driver);
        menu.waitTitleToBe(conf.getString("userName"), 10);
    }

    public void loginWithUser(String username, String password){
        Login login = new Login(driver);
        Menu menu = new Menu(driver);
        menu.waitTitleToBe("Log in", 10);
//        login.checkPageTitle("Log in");

        login.usernameInput.sendKeys(username);
        login.passwordInput.sendKeys(password);
        login.loginBtn.click();

//        Menu menu = new Menu(driver);
        menu.waitTitleToBe(username, 10);
    }

    public WebDriver getDriver(String browser) {
        switch(browser) {
            case "IE":
                System.setProperty("webdriver.ie.driver", "D:/JAVA/libs/selenium/MicrosoftWebDriver.exe");
                return new InternetExplorerDriver();
            case "Firefox":
                return new FirefoxDriver();
        }
        return new ChromeDriver();
    }
}