package pageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import primary.ColorLog;
import primary.ExcelDataIntegration;

public class ProductDetailsPage {
	public WebDriver driver;
	WebDriverWait wait;
	ExcelDataIntegration exceldata;
	JavascriptExecutor js;
	Actions act;
	String ParentWindow;

	public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = wait;
		exceldata = new ExcelDataIntegration();
		js = (JavascriptExecutor)driver;
		act = new Actions(driver);
	}
	
	
	@FindBy(xpath = "//a[@id='nav-hamburger-menu']") private WebElement All;
	@FindBy(xpath = "//li[18]//a[1]//i[1]") private WebElement WomenUnderAllArrow;
	@FindBy(xpath = "//a[@data-menu-id='11']") private WebElement WomenUnderAll;
	@FindBy(xpath = "//ul[@class='hmenu hmenu-visible hmenu-translateX']//a[@class='hmenu-item'][normalize-space()='Watches']") private WebElement Watches;
	@FindBy(xpath = "//img[@alt='Min 30% Off + Extra 10% Off']") private WebElement MichaelKors;
	@FindBy(xpath = "//div[@data-csa-c-type='item']//span[@class='a-size-base-plus a-color-base']")
	private List<WebElement> ListOfWatches;
	@FindBy(xpath = "(//span[@class='a-size-base a-color-base a-text-bold'])[2]") private WebElement WatchName;
	@FindBy(xpath = "//li[@id='sobe_d_b_11_1']//a")	private WebElement MichaelWatches;
	@FindBy(xpath = "(//a[@class='a-link-normal s-no-outline'])[1]") private WebElement Watch1;
	@FindBy(xpath = "(//a[@class='a-link-normal s-no-outline'])[2]") private WebElement Watch2;
	@FindBy(xpath = "//span[@id='productTitle']") private WebElement WatchDescription;
	@FindBy(xpath = "(//span[@class='a-price-whole'])[7]") private WebElement WatchPrice;
	@FindBy(xpath = "//span[@class='a-size-medium a-color-success']") private WebElement ItemsLeftMsg;
	@FindBy(xpath = "//a[@id='bylineInfo']") private WebElement DetailPageBrand;
	@FindBy(xpath = "(//a[@href='javascript:void(0)'])[7]//span[@class='a-size-base a-color-base']") private WebElement MoveToRating;
	@FindBy(xpath = "(//span[@class='a-icon-alt'])[1]") private WebElement Rating;
	@FindBy(xpath = "//select[@id='quantity']") private WebElement SelectQuantity;
	@FindBy(xpath = "//span[@id='a-autoid-0-announce']//span[2]") private WebElement DisplayedQuantity;
	@FindBy(xpath = "//input[@id='add-to-cart-button']") private WebElement AddToCart;
	@FindBy(xpath = "//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']") private WebElement AddedToCart;
	@FindBy(xpath = "//span[@id='attachSiNoCoverage']//input[@type='submit']") private WebElement SkipExtendedWarranty;
	@FindBy(xpath = "//a[@href='/cart?ref_=sw_gtc']")private WebElement GoToCart;
	@FindBy(xpath = "//div[@data-name='Active Items']//div[@data-minquantity='1']") private List<WebElement> VarietyOfItems;
	@FindBy(xpath = "//span[@class='a-dropdown-prompt']") private WebElement EachItemQuantiyInCart;
	@FindBy(xpath = "//span[@id='sc-subtotal-label-activecart']") private WebElement SubTotal;
	@FindBy(name = "proceedToRetailCheckout") private WebElement ProceedToBuy;
	@FindBy(xpath = "//a[text()='Privacy Notice' and 'Conditions of Use']") private WebElement SignInValidation;
	@FindBy(xpath = "//a[text()='Create Account']") private WebElement ProceedToBuyCreateAccount;
	
	public void clickOnAll() {
		wait.until(ExpectedConditions.visibilityOf(All));
		All.click();
		ColorLog.d("Clicked on 'All'");
	}

	public void clickOnWomensFashions() {
		js.executeScript("arguments[0].scrollIntoView(true);", WomenUnderAll);
		wait.until(ExpectedConditions.visibilityOf(WomenUnderAllArrow));
		act.moveToElement(WomenUnderAll).click().build().perform();
		ColorLog.d("Clicked on 'Women's Fashions'");
	}

	public void clickOnWatches() {
		wait.until(ExpectedConditions.visibilityOf(Watches));
		js.executeScript("arguments[0].click()", Watches);
		//act.moveToElement(Watches).click().build().perform();
		ColorLog.d("Clicked on Watches");
	}

	public void clickOnMichaelKors() {
		js.executeScript("arguments[0].scrollIntoView(true);", MichaelWatches);
		js.executeScript("arguments[0].click()", MichaelWatches);
		//act.moveToElement(MichaelWatches).click().build().perform();
		ColorLog.d("Clicked on 'Michael Kors'");
		for(WebElement eachWatch:ListOfWatches) {
			Assert.assertTrue(eachWatch.getText().contains(WatchName.getText()));
		}
	}

	public void clickOnProduct1() {
		wait.until(ExpectedConditions.visibilityOf(Watch1));
		js.executeScript("arguments[0].click()", Watch1);
		//act.moveToElement(Watch1).click().build().perform();
	}

	public void clickOnProduct2() {
		wait.until(ExpectedConditions.visibilityOf(Watch2));
		js.executeScript("arguments[0].click()", Watch2);
		//act.moveToElement(Watch2).click().build().perform();
	}

	public void windowHandles() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> itr = handles.iterator();
		ParentWindow = itr.next();
		String ChildWindow = itr.next();
		driver.switchTo().window(ChildWindow);
	}

	public void productDetails() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("ProductsData");
		System.out.println("Description of selected Watch: "+WatchDescription.getText());
		System.out.println("Price of selected Watch: "+WatchPrice.getText());
		System.out.println("Items left for selected Watch: "+ItemsLeftMsg.getText());
		Select quantity = new Select(SelectQuantity);
		quantity.selectByValue(sheet.getRow(1).getCell(0).getRawValue());
		Assert.assertTrue(DetailPageBrand.getText().contains(sheet.getRow(1).getCell(1).toString()));
		System.out.println("Brand of selected Watch: "+DetailPageBrand.getText());
		System.out.println("Rating of selected Watch: "+MoveToRating.getText());
	}


	public void clickOnAddToCart() {
		wait.until(ExpectedConditions.visibilityOf(AddToCart));
		AddToCart.click();
	}

	public void closeTab() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		driver.close();
	}

	public void switchToMainWindow() {
		driver.switchTo().window(ParentWindow);
	}

	public void skipWarranty() {
		if(SkipExtendedWarranty.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(SkipExtendedWarranty));
			SkipExtendedWarranty.click();
		}
	}

	public void clickOnGoToCart() {
		wait.until(ExpectedConditions.visibilityOf(GoToCart));
		GoToCart.click();
		ColorLog.d("Clicked on 'Go To Cart'");
	}

	public void TotalItemsInCart() {
		act.moveToElement(EachItemQuantiyInCart).perform();
		//js.executeScript("arguments[0].scrollIntoView(true);", TotalQuantiyInCart);
		int TotalQuantity = 0; String total = null;
		for(int i = 0;i<VarietyOfItems.size();i++) {
			TotalQuantity = TotalQuantity + Integer.parseInt(EachItemQuantiyInCart.getText());
			total = String.valueOf(TotalQuantity);
		}
		Assert.assertTrue(SubTotal.getText().contains(total));
		ColorLog.d( VarietyOfItems.size()+" Variety of Items with quantity of "+total+" in all");
	}

	public void changeQuantity() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("ProductsData");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		Select sel = new Select(SelectQuantity);
		sel.selectByValue(sheet.getRow(1).getCell(0).getRawValue());
		ColorLog.d("Quantity changed to '2'");
	}

	public void proceedToBuy() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("ProductsData");
		wait.until(ExpectedConditions.visibilityOf(ProceedToBuy));
		js.executeScript("arguments[0].scrollIntoView(true);", ProceedToBuy);
		ProceedToBuy.click();
		Assert.assertTrue(SignInValidation.getText().contains(sheet.getRow(3).getCell(0).toString()));
		ColorLog.d("Landed on to sigin Page");
	}

	public void refresh() {
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		driver.navigate().refresh();
	}
}