package testTake;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Steps {
    private final WebDriver webDriver;
    private mainPage page;
    WebDriverWait wait;

    public Steps(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        page = new mainPage(webDriver);
        wait =new WebDriverWait(webDriver, 20);
        PageFactory.initElements(this.webDriver, this);
    }

    public void waitForLoad(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imysearchstring")));
    }

    public void  putIdexToSearchField(String index){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imysearchstring")));
        page.filterField.clear();
        page.filterField.sendKeys(index);
        page.filterField.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.autoCompleteDropDown")));

    }

    public void pressFindButton(){
        page.title.click();
        page.submitButton.click();
    }

    public void pressOnTitle(){
        page.title.click();
    }
    public void checkErrorMessageOnPage(String errorMessage){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ideliveryareaerror")));
        Assert.assertEquals(page.messageWithError.getText(), errorMessage,
                "There is another error message on main page");
    }

    public void checkPageWithOffers(String addressFromList){
        if(page.addressOnPage.isDisplayed()){
            Assert.assertTrue(page.addressOnPage.getText().toLowerCase().contains(addressFromList.toLowerCase()),
                    "There is another address on page with results");
        }
    }

    public void choosingFromlist(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iautoCompleteDropDownContent")));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("#iautoCompleteDropDownContent > a.item.selected > span")));
        page.firstElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.atom-dropdown-text")));
    }

    public void pressEnterButton(){
        page.filterField.sendKeys(Keys.ENTER);
    }

    public void errorMessageIsShown(String erMessage){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.suggestions-location")));
        Assert.assertEquals(page.errorMessage.getText(), erMessage, "Here is another error message");
    }

    public void chooseNotFirst(){
        page.notFirstElement.click();
    }

    public String getText(){
        return page.notFirstElement.getText();
    }

    public void checkListOfAddresses(String addressFromList){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iautoCompleteDropDownContent")));
        Assert.assertTrue(checkAddressesInList(addressFromList),
                "Drop-down list has not address from Search field");

    }
    public void checkAddress(String expectedAddress){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.atom-dropdown-text")));
        Assert.assertTrue(page.addressOnPage.getText().contains(expectedAddress), "On page is another address");
    }

    public void checkPlace(String place){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("irestaurantlist")));
        Assert.assertTrue(checkRestaurant(place), "There is no such restaurant on page" );
    }

    //Check addresses in drop-down list. List should contain addresses which contain searching item
    public boolean checkAddressesInList(String address){
        List<WebElement> names = webDriver.findElements(By.cssSelector("a.item> span"));
        for (int i=0; i<names.size(); i++){
            if (!names.get(i).getText().contains(address)){
                return false;
            }
        }
        return true;
    }

    //Searching restaurant should be in first names in list of restaurants
    public boolean checkRestaurant(String place){
        List<WebElement> restaurantNames = webDriver.findElements(By.cssSelector("h2.restaurantname >a "));
        System.out.println("Here is quantity: " + restaurantNames.size());
        for (int i=0; i<10; i++){
            if (restaurantNames.get(i).getText().toLowerCase().contains(place.toLowerCase())){
                return true;
            }
        }
        return false;
    }

}
