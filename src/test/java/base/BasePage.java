package base;

import excel.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.ChooseProfilePage;
import pages.CreateProfilePage;
import pages.ProfilePage;
import pages.LoginPage;

import java.io.IOException;
import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wdwait;
    public LoginPage loginPage;
    public ChooseProfilePage chooseProfilePage;
    public CreateProfilePage createProfilePage;
    public ProfilePage profilePage;
    public ExcelReader excelReader;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        chooseProfilePage = new ChooseProfilePage(driver);
        createProfilePage = new CreateProfilePage(driver);
        profilePage = new ProfilePage(driver);
        excelReader = new ExcelReader("C:\\Users\\brank\\IdeaProjects\\QAunited.cloudInterview\\excelFile.xlsx");
    }

    public void waitToBeClickable(WebElement e) {
        wdwait.until(ExpectedConditions.elementToBeClickable(e));
    }

    public void waitToBeVisible(WebElement e) {
        wdwait.until(ExpectedConditions.visibilityOf(e));
    }

    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

}
