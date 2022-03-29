package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateProfilePage {

    public CreateProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBys({@FindBy( tagName = "header"), @FindBy( tagName = "button")})
    public WebElement homeButton;

    @FindBy( name = "name")
    public WebElement profileNameInputField;

    @FindBy( className = "age")
    public List<WebElement> listOfAges;

    @FindBy( id = "year")
    public WebElement birthYearInputField;

    @FindBy( className = "avatar")
    public List<WebElement> listOfAvatars;

    @FindBys({@FindBy( className = "form"), @FindBy( tagName = "button")} )
    public WebElement createProfileButton;

    @FindBy( className = "form__error")
    public WebElement errorMessage;

//    ------------------------------------------------------

    public void clickOnHomeButton() {
        homeButton.click();
    }

    public void enterProfileName(String n) {
        profileNameInputField.clear();
        profileNameInputField.sendKeys(n);
    }

    public void selectAge(int a) {
        listOfAges.get(a).findElement(By.tagName("label")).click();
    }

    public void enterBirthYear(int y) {
        birthYearInputField.clear();
        birthYearInputField.sendKeys(Integer.toString(y));
    }

    public void selectAvatar(int a) {
        listOfAvatars.get(a).findElement(By.tagName("img")).click();
    }

    public void clickOnCreateProfileButton() {
        createProfileButton.click();
    }

    public String getSelectedAge(int a) {
        return listOfAges.get(a).findElement(By.tagName("label")).getText();
    }

    public String getAvatarSrc(int s) {
        return listOfAvatars.get(s).findElement(By.tagName("img")).getAttribute("src");
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }


}
