package config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterSuite;

import java.util.function.Function;

public class BaseTest {
    private static WebDriver driver;
    private static int count = 0;
    private static int restartFreq = Integer.MAX_VALUE;
    protected static Browser protectedBrowser = Browser.Chrome;

    public enum Browser{
        Chrome("CHROME");
        private String name;
        Browser(String name){
            this.name = name;
        }
    }

    public static WebDriver newDriver()
    {
        String source = BaseTest.class.getClassLoader().getResource("drivers/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", source);
        driver = new ChromeDriver(returnOptions());
        return driver;
    }

    private static ChromeOptions returnOptions()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("chrome.switches", "--disaible-extensions");
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        return options;
    }


    @AfterSuite(alwaysRun =  true)
    public static void closeDriver()
    {
        if (driver !=null)
        {
            try {
                driver.quit();
                driver = null;
            } catch (WebDriverException ex){
                ex.printStackTrace();
            }
        }
    }

    public static WebDriver getDriver(final Browser browser )
    {
        protectedBrowser = browser;
        count++;
        if (driver == null){
            return newDriver();
        }

        try {
            driver.getCurrentUrl();
        }catch (Throwable ex){
            return newDriver();
        }
        if (count>restartFreq){
            closeDriver();
            driver = null;

            return newDriver();
        }
        return driver;
    }

    public static void setRestartFreq(final int newRestartFreq){
        restartFreq = newRestartFreq;
    }

    protected void getLink(String url){
        if (driver !=null){
            driver.get(url);
        }
        else {
            driver = getDriver(protectedBrowser);
        }
    }

    public void waitForLoading(){
        Wait<WebDriver> wait = new WebDriverWait(driver, 60);

        wait.until(new Function<WebDriver, Boolean>(){
            public Boolean apply(WebDriver driver){
                System.out.println("Current Window State: " +
                        String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                .equals("complete");
            }

        });
    }

}
