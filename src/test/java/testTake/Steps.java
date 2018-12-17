package testTake;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Steps {
    private final WebDriver webDriver;
    private mainPage page;

    public Steps(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        page = new mainPage(webDriver);
        PageFactory.initElements(this.webDriver, this);
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
