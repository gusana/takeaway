package testTake;

import config.BaseTest;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

public class Stepdefs extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    mainPage page;
    Steps steps;

    public void setUP(String browser)
    {
        if (browser != null){
            protectedBrowser = Browser.Chrome;
        }
        this.driver = getDriver(protectedBrowser);
        setRestartFreq(10);
        wait =new WebDriverWait(driver, 20);
        page = new mainPage(driver);
        steps = new Steps(driver);
        waitForLoading();
    }

    private String addressFromList = "";

    @Given("^User navigates to \"(.*)\"$")
    public void user_navigates_to(String url){
        setUP("chrome");
        getLink(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imysearchstring")));
    }

    @When("^User puts index \"(.*)\"$")
    public void user_puts_index(String index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imysearchstring")));
        page.filterField.clear();
        page.filterField.sendKeys(index);
        page.filterField.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.autoCompleteDropDown")));
    }

    @Then("^User presses Find button$")
    public void user_presses_Find_button(){
        page.title.click();
        page.submitButton.click();
    }

    @When("^User clicks on title$")
    public void pressTitle(){
        page.title.click();
    }

    @Then("^User sees error message \"(.*)\"$")
    public void errorMessageIsOnPage(String errorMessage){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ideliveryareaerror")));
        Assert.assertEquals(page.messageWithError.getText(), errorMessage,
                "There is another error message on main page");
    }

    @Then("^Page with offers is shown$")
    public void page_with_offers_is_shown() {
        if(page.addressOnPage.isDisplayed()){
            Assert.assertTrue(page.addressOnPage.getText().toLowerCase().contains(addressFromList.toLowerCase()),
                    "There is another address on page with results");
        }

        }

    @Then("^User chooses from drop-down list$")
    public void chooseFromDropList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iautoCompleteDropDownContent")));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("#iautoCompleteDropDownContent > a.item.selected > span")));
        page.firstElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.atom-dropdown-text")));
    }

    @Then("^User presses Enter button$")
    public void pressEnter(){
        page.filterField.sendKeys(Keys.ENTER);
    }

    @Then("^Message \"(.*)\" should be shown$")
    public void errorMessageShown(String erMessage) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.suggestions-location")));
        Assert.assertEquals(page.errorMessage.getText(), erMessage, "Here is another error message");
    }


    @Then("^User chooses not first address from the list$")
    public void chooseNotFirstElement(){
        page.notFirstElement.click();
        addressFromList = page.notFirstElement.getText();
    }

    @Then("^Addresses with this number should be shown$")
    public void listOfAdressesShown(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iautoCompleteDropDownContent")));
        Assert.assertTrue(steps.checkAddressesInList(addressFromList),
                "Drop-down list has not address from Search field");
    }

    @Then("^Address \"(.*)\" will be shown on page$")
    public void addressIsShown(String expectedAddress) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.atom-dropdown-text")));
        Assert.assertTrue(page.addressOnPage.getText().contains(expectedAddress), "On page is another address");
    }

    @Then("^Certain restaurant \"(.*)\" should be on the page$")
    public void findCertainPlace(String place) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("irestaurantlist")));
        Assert.assertTrue(steps.checkRestaurant(place), "There is no such restaurant on page" );
    }

    @After
    public void CD(){
        driver.close();
        driver.quit();
    }

}