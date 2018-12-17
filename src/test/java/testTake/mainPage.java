package testTake;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class mainPage {
    private final WebDriver webDriver;

    public mainPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(id="imysearchstring")
    public WebElement filterField;

    @FindBy(id = "submit_deliveryarea")
    public WebElement submitButton;

    @FindBy(css = "#iautoCompleteDropDownContent > a:nth-child(1) > span")
    public WebElement firstElement;

    @FindBy(css = "#iautoCompleteDropDownContent > a:nth-child(2) > span")
    public WebElement notFirstElement;

    @FindBy(css = "div.suggestions-location")
    public WebElement errorMessage;

	@FindBy(css="#ideliveryareaerror")
	public WebElement messageWithError;


	@FindBy(css="span.atom-dropdown-text")
	public WebElement addressOnPage;

	@FindBy(css = "h1.headline-xl-raised")
    public WebElement title;
}
