package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ChooseProfilePage {

    public ChooseProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBys({@FindBy( className = "create"), @FindBy( tagName = "button")})
    public WebElement createNewProfileButton;

    @FindBy( className = "profiles__profile")
    public List<WebElement> listOfCreatedProfiles;

//    ----------------------------------------------------------------------

    public void clickOnCreateNewProfileButton() {
        createNewProfileButton.click();
    }

    public WebElement profileImg(int p) {
        return listOfCreatedProfiles.get(p).findElement(By.tagName("img"));
    }

    public WebElement profileName(int p) {
        return listOfCreatedProfiles.get(p).findElement(By.tagName("span"));
    }

    public void clickOnProfile(int p) {
        profileImg(p).click();
    }

    public String getAvatarSrc(int p) {
        return profileImg(p).getAttribute("src");
    }

    public String getProfileName(int p) {
        return profileName(p).getText();
    }



}
