package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage {

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBys({@FindBy( className = "header__navigation"), @FindBy( tagName = "button")} )
    public List<WebElement> listOfHeaderButtons;

    @FindBys({@FindBy( className = "card__avatar"), @FindBy( tagName = "img")})
    public WebElement profileAvatar;

    @FindBy( className = "card__profile-name")
    public WebElement profileName;

    @FindBy( className = "card__profile-type")
    public WebElement profileType;

    @FindBy( className = "card__lorem")
    public WebElement profileDescription;

    @FindBys({@FindBy( className = "card__description"), @FindBy( className = "card__delete")})
    public WebElement deleteButton;

//    ------------------------------------------------------------------------

    public void clickOnCreateProfileButton() {
        listOfHeaderButtons.get(0).click();
    }

    public void clickOnChooseProfileButton() {
        listOfHeaderButtons.get(1).click();
    }

    public void clickOnDeleteButton() {
        deleteButton.click();
    }

    public String getAvatarSrc() {
        return profileAvatar.getAttribute("src");
    }

    public String getProfileName() {
        return profileName.getText();
    }

    public String getProfileType() {
        return profileType.getText();
    }

    public String getProfileDescription() {
        return profileDescription.getText();
    }

}
