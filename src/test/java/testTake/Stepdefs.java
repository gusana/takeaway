package testTake;

import config.BaseTest;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Stepdefs extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    mainPage page ;
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
        steps.waitForLoad();
    }

    @When("^User puts index \"(.*)\"$")
    public void user_puts_index(String index) {
        steps.putIdexToSearchField(index);
       }

    @Then("^User presses Find button$")
    public void user_presses_Find_button(){
        steps.pressFindButton();
    }

    @When("^User clicks on title$")
    public void pressTitle(){
        steps.pressOnTitle();
    }

    @Then("^User sees error message \"(.*)\"$")
    public void errorMessageIsOnPage(String errorMessage){
      steps.checkErrorMessageOnPage(errorMessage);
    }

    @Then("^Page with offers is shown$")
    public void page_with_offers_is_shown() {
        steps.checkPageWithOffers(addressFromList);
        }

    @Then("^User chooses from drop-down list$")
    public void chooseFromDropList() {
       steps.choosingFromlist();
    }

    @Then("^User presses Enter button$")
    public void pressEnter(){
        steps.pressEnterButton();
    }

    @Then("^Message \"(.*)\" should be shown$")
    public void errorMessageShown(String erMessage) {
       steps.errorMessageIsShown(erMessage);
    }


    @Then("^User chooses not first address from the list$")
    public void chooseNotFirstElement(){
       steps.chooseNotFirst();
       addressFromList = steps.getText();
    }

    @Then("^Addresses with this number should be shown$")
    public void listOfAdressesShown(){
       steps.checkListOfAddresses(addressFromList);
    }

    @Then("^Address \"(.*)\" will be shown on page$")
    public void addressIsShown(String expectedAddress) {
        steps.checkAddress(expectedAddress);
    }

    @Then("^Certain restaurant \"(.*)\" should be on the page$")
    public void findCertainPlace(String place) {
       steps.checkPlace(place);
    }

    @After
    public void CD(){
        driver.close();
        driver.quit();
    }

}