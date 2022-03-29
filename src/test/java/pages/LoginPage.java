package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy( id = "username")
    public WebElement username;

    @FindBy( id = "password")
    public WebElement password;

    @FindBy( css = ".button.button--primary")
    public WebElement loginButton;

//------------------------------------------------------------------

    public void enterUsername(String u) {
        username.sendKeys(u);
    }

    public void enterPasword(String p) {
        password.sendKeys(p);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
