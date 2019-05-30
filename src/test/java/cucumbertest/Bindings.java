package cucumbertest;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import core.Helpers;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import poms.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.concurrent.TimeUnit;

public class Bindings {
    protected Logger log ;//= LogManager.getLogger(this.getClass().getSimpleName());
    private static LocalDateTime timePoint = LocalDateTime.now();
    private String dateTime = timePoint.get(ChronoField.YEAR_OF_ERA) + "-" + timePoint.getMonth() + "-" + timePoint.getDayOfMonth() + "_"+ timePoint.getHour() + "-" + timePoint.getMinute() + "-" + timePoint.getSecond();
    protected WebDriver driver;
    protected Config conf = ConfigFactory.load("application.conf");
    private Menu menu;

    @Before
    public void initializeAll() {
        String logPath = "logs\\TestRun_" + dateTime + "\\" ;
        String logFileName = logPath + "TestRun_" + dateTime + ".log";
        System.setProperty("logPath", logPath);
        System.setProperty("logFileName", logFileName);
        log = LogManager.getLogger(this.getClass().getSimpleName());
        log.info(this.getClass().getSimpleName() + " test started");
        driver = Helpers.getDriver(conf.getString("browser"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        String url = conf.getString("adminUrl");
        log.info("Navigate to " + url);
        driver.get(url);
        menu = new Menu(driver);
    }

    @Given("I am logged in as admin")
    public void i_am_logged_in_as_admin() {
        Login login = new Login(driver);
        Menu menu = new Menu(driver);
        menu.waitTitleToBe("Log in", 10);

        login.usernameInput.sendKeys(conf.getString("userName"));
        login.passwordInput.sendKeys(conf.getString("password"));
        login.loginBtn.click();

        menu.waitTitleToBe(conf.getString("userName"), 10);
    }

    @When("I go to Content page")
    public void i_go_to_Content_page() {
        Menu menu = new Menu(driver);
        if(!menu.contentLnk.isDisplayed()){
            menu.manageLnk.click();
        }
        menu.contentLnk.click();
        menu.waitTitleToBe("Content", 20);
    }

    @When("click Add Content button")
    public void click_Add_Content_button() {
        Content content = new Content(driver);
        content.addContentBtn.click();
        menu.waitTitleToBe("Add content", 10);
    }

    @When("click to Article link")
    public void click_to_Article_link() {
        AddContent addContent = new AddContent(driver);
        addContent.articleLnk.click();
        menu.waitTitleToBe("Create Article", 10);
    }

    @When("fill in Title field with \"(.*)\"")
    public void fill_in_Title_field_with(String title) {
        CreateArticle createArticle = new CreateArticle(driver);
        createArticle.titleInput.sendKeys(title);
    }

    @When("fill in Body field with \"(.*)\"")
    public void fill_in_Body_field_with(String articleContent) {
        Helpers.sleep(500);
        CreateArticle createArticle = new CreateArticle(driver);
        createArticle.fillInContent(articleContent);
    }

    @When("edit Body field with \"(.*)\"")
    public void edit_Body_field_with(String updatedContent) {
        Helpers.sleep(500);
        CreateArticle createArticle = new CreateArticle(driver);
        createArticle.clearContent();
        createArticle.fillInContent(updatedContent);
    }

    @When("click Save button")
    public void click_Save_button() {
        CreateArticle createArticle = new CreateArticle(driver);
        createArticle.saveBtn.click();
    }

    @When("open content item with \"(.*)\"")
    public void open_content_item_with(String title) {
        Content content = new Content(driver);
        content.goToContent(title);
    }

    @Then("the page title should start with \"(.*)\"")
    public void the_page_title_should_start_with(String title) {
        menu.waitTitleToBe(title, 10);
    }

    @Then("info message should appear for \"(.*)\" article")
    public void info_message_should_appear_for_article_with(String title) {
        Article article = new Article(driver);
        article.checkCreatedArticleMessage(title);
    }

    @Then("info message should appear for \"(.*)\" basic page")
    public void info_message_should_appear_for_basic_page_with(String title) {
        Article article = new Article(driver);
        article.checkCreatedPageMessage(title);
    }

    @Then("info message \"(.*)\" should appear for edited basic page")
    public void info_message_should_appear_for_edited_basic_page_with(String title) {
        Content content = new Content(driver);
        content.checkContentMsgStatus(title);
    }

    @And("page should be opened with \"(.*)\"")
    public void page_should_be_opened_with_just_created(String articleContent) {
        Article article = new Article(driver);
        article.contentTxt.checkIfEquals(articleContent);
    }

    @And("item \"(.*)\" should have type Article in Content table")
    public void item_should_have_type_Article_in_Content_table(String title) {
        do {
            menu.contentLnk.click();
            Helpers.sleep(2000);
            log.info("menu.pageTitle.getText() = " + menu.pageTitle.getText());
        } while (!menu.pageTitle.getText().equals("Content"));
        Content content = new Content(driver);
        content.checkContentType(title, "Article");
    }

    @And("item \"(.*)\" should have status Published in Content table")
    public void item_should_have_status_Published_in_Content_table(String title) {
        Content content = new Content(driver);
        content.checkContentStatus(title, "Published");
    }

    @And("click Edit button for content item with \"(.*)\"")
    public void click_Edit_button_for_content_item_with(String title) {
        Content content = new Content(driver);
        content.editContentBtn(title).click();
    }

    @And("click to Basic Page link")
    public void click_to_Basic_Page_link() {
        AddContent addContent = new AddContent(driver);
        addContent.basicPageLnk.click();
    }

    @And("select Provide Menu Link option")
    public void select_Provide_Menu_Link_option() {
        CreateArticle createArticle = new CreateArticle(driver);
        createArticle.menuSettings.click();
        createArticle.provideMenuLink.select();
    }

    @And("item \"(.*)\" should have type Basic page in Content table")
    public void item_should_have_type_Basic_page_in_Content_table(String title) {
       do {
           menu.contentLnk.click();
           Helpers.sleep(2000);
           log.info("menu.pageTitle.getText() = " + menu.pageTitle.getText());
       } while (!menu.pageTitle.getText().equals("Content"));
        Content content = new Content(driver);
        content.checkContentType(title, "Basic page");
    }

    @When("I go to Home page")
    public void i_go_to_Home_page() {
        menu.homeLnk.click();
    }

    @And("open Article with \"(.*)\"")
    public void open_Article_with(String title) {
        HomePage home = new HomePage(driver);
        home.openArticle(title);
    }

    @And("fill in Subject field with \"(.*)\"")
    public void fill_in_Subject_field_with(String subject) {
        Comment comment = new Comment(driver);
        comment.subjectInput.sendKeys(subject);

    }

    @And("fill in Comment field with \"(.*)\"")
    public void fill_in_Comment_field_with(String commentText) {
        Comment comment = new Comment(driver);
        comment.fillInComment(commentText);
    }

    @Then("info message about added comment should appear")
    public void info_message_about_added_comment_should_appear() {
        Article article = new Article(driver);
        Helpers.check2StringIfContains(article.infoMessage.getText(), "Your comment has been posted.");
    }

    @And("should appear comment with \"(.*)\" and \"(.*)\"")
    public void should_appear_comment_with(String subject, String commentText) {
        Comment comment = new Comment(driver);
        comment.checkCommentSubject(subject);
        comment.checkCommentText(commentText);
    }

    @After()
    public void closeBrowser() {
        log.info("Test has ended.\n");
        driver.quit();
    }
}
