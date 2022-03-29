package test;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass extends BasePage {

    @BeforeMethod
    public void setUpPage() {
        driver.navigate().to("https://qa-interview.united.cloud/login");
        driver.manage().window().maximize();
    }

    public void login() {
        loginPage.enterUsername("branko.stankovic1");
        loginPage.enterPasword("Lozinka123");
        loginPage.clickLoginButton();
    }

    @Test( priority = 10)
    public void verifyThatUserCanCreateNewProfileAndDeleteThatProfile() throws InterruptedException {
        login();
        waitToBeClickable(chooseProfilePage.createNewProfileButton);
        chooseProfilePage.clickOnCreateNewProfileButton();

            for (int i = 0; i< createProfilePage.listOfAges.size(); i++) {
            createProfilePage.selectAge(i);
            Thread.sleep(1000);
            int sizeOfAvatars = createProfilePage.listOfAvatars.size();
            for (int j = 0; j < sizeOfAvatars; j++) {

                    String actualProfileName = excelReader.getStringData("sheet", 1, 0);
                    waitToBeClickable(createProfilePage.profileNameInputField);
                    createProfilePage.enterProfileName(actualProfileName);
                    createProfilePage.selectAge(i);

                    String ages = createProfilePage.getSelectedAge(i);
                    String type = null;

                    if (ages.equals("0 - 6") || ages.equals("7 - 11")) {
                        type = "KIDS";
                    } else if(ages.equals("12 - 14") || ages.equals("15 - 17")) {
                        type = "TEEN";
                    } else if (ages.equals("18+")) {
                        type = "ADULT";
                    }

                    if ( createProfilePage.listOfAges.get(i).getText().equals("18+")) {
                        int birthYear = 1989;
                        waitToBeClickable(createProfilePage.birthYearInputField);
                        createProfilePage.enterBirthYear(birthYear);
                    }

                    Thread.sleep(3500);

                    scroll(createProfilePage.listOfAvatars.get(j));
                    createProfilePage.selectAvatar(j);

                    String actualAvatarSrc = createProfilePage.getAvatarSrc(j);

                    createProfilePage.clickOnCreateProfileButton();

                    waitToBeVisible(profilePage.profileAvatar);
                    waitToBeVisible(profilePage.profileName);
                    waitToBeVisible(profilePage.profileType);
                    waitToBeVisible(profilePage.profileDescription);

                    String currentAvatarSrc1 = profilePage.getAvatarSrc();
                    String currentProfileName1 = profilePage.getProfileName();
                    String currentProfileType = profilePage.getProfileType();
                    String currentProfileDescription = profilePage.getProfileDescription();
                    String profileDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                            " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam," +
                            " quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

                    Assert.assertEquals(actualAvatarSrc, currentAvatarSrc1);
                    Assert.assertEquals(actualProfileName, currentProfileName1);
                    Assert.assertEquals(type, currentProfileType);
                    Assert.assertEquals(profileDescription,currentProfileDescription);

                    profilePage.clickOnChooseProfileButton();

                    waitToBeClickable(chooseProfilePage.profileImg(1));

                    int actualNumberOfCreatedProfiles = chooseProfilePage.listOfCreatedProfiles.size();

                    Assert.assertEquals(actualNumberOfCreatedProfiles, 2);

                    String currentAvatarSrc2 = chooseProfilePage.getAvatarSrc(1);
                    String currentProfileName2 = chooseProfilePage.getProfileName(1);

                    Assert.assertEquals(actualProfileName, currentProfileName2);
                    Assert.assertEquals(actualAvatarSrc, currentAvatarSrc2);

                    waitToBeClickable(chooseProfilePage.profileImg(1));
                    chooseProfilePage.clickOnProfile(1);

                    waitToBeClickable(profilePage.deleteButton);
                    scroll(profilePage.deleteButton);
                    profilePage.clickOnDeleteButton();

                    waitToBeClickable(chooseProfilePage.profileImg(0));

                    actualNumberOfCreatedProfiles = chooseProfilePage.listOfCreatedProfiles.size();

                    Assert.assertEquals(actualNumberOfCreatedProfiles, 1);

                    waitToBeClickable(chooseProfilePage.createNewProfileButton);
                    chooseProfilePage.clickOnCreateNewProfileButton();
                }
            }
    }

    @Test(priority = 30)
    public void verifyThatUserCanCreateMaximum5Profiles() throws InterruptedException {
        waitToBeClickable(chooseProfilePage.createNewProfileButton);
        chooseProfilePage.clickOnCreateNewProfileButton();

        for (int i = 0; i <= 5; i++) {
            String actualProfileName = excelReader.getStringData("sheet", i + 1, 0);
            waitToBeClickable(createProfilePage.profileNameInputField);
            createProfilePage.enterProfileName(actualProfileName);
            if (i == 5) {
                createProfilePage.selectAge(i - 1);
                if ( createProfilePage.listOfAges.get(i -1).getText().equals("18+")) {
                    int birthYear = excelReader.getIntegerData("sheet", 2, 1);
                    waitToBeClickable(createProfilePage.birthYearInputField);
                    createProfilePage.enterBirthYear(birthYear);
                }
            } else {
                createProfilePage.selectAge(i);
                if ( createProfilePage.listOfAges.get(i).getText().equals("18+")) {
                    int birthYear = excelReader.getIntegerData("sheet", 1, 1);
                    waitToBeClickable(createProfilePage.birthYearInputField);
                    createProfilePage.enterBirthYear(birthYear);
                }
            }

            Thread.sleep(3500);

            scroll(createProfilePage.listOfAvatars.get(i));
            createProfilePage.selectAvatar(i);

            createProfilePage.clickOnCreateProfileButton();

            if (i < 5 ) {
                profilePage.clickOnCreateProfileButton();
            }
        }
        String actualDisplayedErrorMessage = "Maximum number of profiles reached for this user.";
        String currentDisplayedErrorMessage = createProfilePage.getErrorMessage();

        Assert.assertEquals(actualDisplayedErrorMessage, currentDisplayedErrorMessage);

        scroll(createProfilePage.homeButton);
        createProfilePage.clickOnHomeButton();

        profilePage.clickOnChooseProfileButton();

        int actualNumberOfCreatedProfiles = chooseProfilePage.listOfCreatedProfiles.size();

        Assert.assertEquals(actualNumberOfCreatedProfiles, 6);
    }


    @Test( priority = 20)
    public void verifyThatAllAvatarsAreUnique() throws InterruptedException {
        waitToBeClickable(chooseProfilePage.createNewProfileButton);
        chooseProfilePage.clickOnCreateNewProfileButton();

        for (int i = 0; i< createProfilePage.listOfAges.size(); i++) {
            createProfilePage.selectAge(i);
            Thread.sleep(1000);
            int sizeOfAvatars = createProfilePage.listOfAvatars.size();
            Thread.sleep(3500);

            for (int j = 0; j < sizeOfAvatars; j++) {
                scroll(createProfilePage.listOfAvatars.get(j));
                createProfilePage.selectAvatar(j);
                String actualAvatarSrc = createProfilePage.getAvatarSrc(j);
                for (int k = j + 1; k < sizeOfAvatars; k++) {
                    createProfilePage.selectAvatar(k);
                    String currentAvatarSrc = createProfilePage.getAvatarSrc(k);

                    Assert.assertNotEquals(actualAvatarSrc, currentAvatarSrc);
                }

            }
        }

    }


}

