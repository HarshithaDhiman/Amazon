package pageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import primary.ColorLog;
import primary.ExcelDataIntegration;

public class HomePage {
	WebDriverWait wait;
	WebDriver driver;
	Actions act;
	public HomePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		this.wait = wait;
	}

	@FindBy(xpath = "//a[@id='nav-link-accountList']") private WebElement HelloSignIn;
	@FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner'][normalize-space()='Sign in']") private WebElement SignIn_Btn;
	@FindBy(xpath = "//h1[@class='a-spacing-small']") private WebElement SignInPage;

	@FindBy(id = "twotabsearchtextbox") private WebElement GlobalSearch_TextBox;
	@FindBy(id = "nav-search-submit-button") private WebElement SearchSymbol;
	@FindBy(xpath = "//a[@aria-label='See more, Brand']//span[@class='a-expander-prompt'][normalize-space()='See more']") private WebElement BrandSeeMore;
	@FindBy(xpath = "//li[@id='p_89/iQOO']//i[@class='a-icon a-icon-checkbox']") private WebElement IQOOBrand;
	@FindBy(xpath = "//div[@id='brandsRefinements']//li[@id='p_89/Nokia']//i[@class='a-icon a-icon-checkbox']") private WebElement NokiaBrand;
	@FindBy(xpath = "//span[@id='nav-search-label-id']") private WebElement DropdownLabel;
	@FindBy(xpath = "//span[contains(text(),'Clear')]") private WebElement ClearSelection;
	@FindBy(xpath = "//div[@class='a-section']/div/div[2]/descendant::a[1]") private List <WebElement> SelectedBrandMobileProdcuts;

	@FindBy(xpath = "//div[@id='priceRefinements']//li[@id='p_36/1318503031']//a") private WebElement PriceSelection;
	@FindBy(xpath = "//span[contains(text(),'₹1,000 - ₹5,000')]") private WebElement MobilePriceSelection;
	@FindBy(xpath = "//span[text()='Results']") private WebElement ResultsPage;
	@FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']//div//div//span[@class='a-price']") private List<WebElement> SelectedPriceRangeMobileProducts;

	public String homePageVerification() {
		return driver.getCurrentUrl();
	}

	public void homePageValidation() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		wait.until(ExpectedConditions.visibilityOf(HelloSignIn));
		assertEquals(driver.getCurrentUrl(), sheet.getRow(1).getCell(0).toString());
		ColorLog.d("Home Page successfully visible!");
	}

	public void hoverOverAndClickOnSignIn() {
		wait.until(ExpectedConditions.visibilityOf(HelloSignIn));
		Action mouseHover = act.moveToElement(HelloSignIn).build();
		mouseHover.perform();
		SignIn_Btn.click();
	}

	public void signPageVerification() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		wait.until(ExpectedConditions.visibilityOf(SignInPage));
		assertEquals(SignInPage.getText(), sheet.getRow(3).getCell(0).toString());
	}

	public void clickOnSearchWithText() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		GlobalSearch_TextBox.sendKeys(sheet.getRow(5).getCell(0).toString());
		SearchSymbol.click();
	}

	public void searchProductWithBrandFilter() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		BrandSeeMore.click();
		//IQOOBrand.click();
		NokiaBrand.click();
		for (WebElement selectedBrandMobileProdcut : SelectedBrandMobileProdcuts) {
			Assert.assertTrue(selectedBrandMobileProdcut.getText().contains(sheet.getRow(7).getCell(0).toString()));
		}
		ColorLog.d("Products with selected brand is displayed");
	}

	public void verifyDropdownWithBrandSelection() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		assertEquals(DropdownLabel.getText(), sheet.getRow(9).getCell(0).toString());
		ColorLog.d("Products related to correspondig clicked brand is displayed");
		ClearSelection.click();
		assertNotEquals(DropdownLabel.getText(), sheet.getRow(9).getCell(1).toString());
		ColorLog.e("HomePage Class", "The global dropdown label does not change back to 'All'");
	}

	public void searchProductWithPriceFilter() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		PriceSelection.click();
		assertEquals(ResultsPage.getText(), sheet.getRow(11).getCell(0).toString());
		for (WebElement selectedPriceRangeMobileProduct : SelectedPriceRangeMobileProducts) {
			System.out.print(selectedPriceRangeMobileProduct.getText()+"\t");
		}
		ColorLog.d("Products with selected price is displayed");
	}

	public void verifyDropdownWithPriceSelection() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		assertEquals(DropdownLabel.getText(), sheet.getRow(13).getCell(0).toString());
		ColorLog.d("The global dropdown label changes to 'Electronics'");
	}

	public void searchProductWithMobilePriceFilter() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("HomePageData");
		MobilePriceSelection.click();
		assertEquals(ResultsPage.getText(), sheet.getRow(11).getCell(0).toString());
		for (WebElement selectedPriceRangeMobileProduct : SelectedPriceRangeMobileProducts) {
			System.out.print(selectedPriceRangeMobileProduct.getText()+"\t");
		}
		ColorLog.d("Mobiles with selected price is displayed");
	}
}

