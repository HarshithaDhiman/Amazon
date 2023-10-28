package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import primary.ColorLog;
import primary.ExcelDataIntegration;

public class GlobalSearchDropdown extends ExcelDataIntegration{
	WebDriver driver;
	WebDriverWait wait;
	ExcelDataIntegration exceldata;
	public GlobalSearchDropdown(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = wait;
		exceldata = new ExcelDataIntegration();
	}

	@FindBy(xpath = "//div[@id='nav-main']") private WebElement AllHeaderElements;
	@FindBy(id = "twotabsearchtextbox") private WebElement GlobalSearch_TextBox;
	@FindBy(id = "nav-search-submit-button") private WebElement SearchSymbol;
	@FindBy(xpath = "(//div[@class='s-no-outline'])[1]") private WebElement TShirtSearchText;
	@FindBy(xpath = "(//a[@class='a-link-normal s-no-outline'])[1]") private WebElement Tshirt1;
	@FindBy(xpath = "(//a[@class='a-link-normal s-no-outline'])[2]") private WebElement Tshirt2;
	@FindBy(xpath = "//span[@id='productTitle']") private WebElement TshirtName;
	@FindBy(xpath = "//a[@id='bylineInfo']") private WebElement TshirtBrand;
	@FindBy(xpath = "(//span[@aria-hidden='true']//span[@class='a-price-whole'])[1]") private WebElement TshirtPrice;
	@FindBy(xpath = "//div[@id='availability']//span") private WebElement InStock;
	@FindBy(xpath = "//a[@title='Add to Wish List']") private WebElement WishList;
	@FindBy(xpath = "(//input[@value='Delete'])[1]") private WebElement DeleteFirstTshirtItem;
	@FindBy(xpath = "(//input[@value='Save for later'])[3]") private WebElement Shirt2SaveForLater;
	@FindBy(xpath = "//a[text()='Privacy Notice' and 'Conditions of Use']") private WebElement SignInValidation;
	@FindBy(xpath = "//select[@id='searchDropdownBox']/option") private List<WebElement> AllDropDownElements;
	@FindBy(xpath = "//div[@class='nav-fill']/descendant::a") private List<WebElement> HeaderElements;
	@FindBy(xpath = "//a[@href='/electronics/b/?ie=UTF8&node=976419031&ref_=nav_cs_electronics']") private WebElement Electronics_Link;
	@FindBy(xpath = "//span[@id='nav-search-label-id']") private WebElement DropDownLabelId;
	@FindBy(xpath = "//a[@href='/Home-Kitchen/b/?ie=UTF8&node=976442031&ref_=nav_cs_home']") private WebElement HomeNKitchen_Link;


	public void allDropDownVerification() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		Assert.assertTrue(DropDownLabelId.getText().equals(sheet.getRow(11).getCell(0).toString()));
		for(int i = 0; i < AllDropDownElements.size();i++) {
			assertEquals(AllDropDownElements.get(i).getText(), sheet.getRow(3).getCell(i).toString());
		}
	}

	public void headerElements() throws IOException {
		System.out.println(AllHeaderElements.getText());
	}

	public void ElectronicsLinkVerificationWithValidData() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		Electronics_Link.click();
		Assert.assertTrue(DropDownLabelId.getText().equals(sheet.getRow(7).getCell(0).toString()));
		ColorLog.d("'Electronics' Link is available and upon clicking it the Global dropdown autoselected 'Electronics'");
		GlobalSearch_TextBox.sendKeys(sheet.getRow(7).getCell(1).toString());
		SearchSymbol.click();
		Assert.assertTrue(DropDownLabelId.getText().equals(sheet.getRow(7).getCell(0).toString()));
		ColorLog.d("Upon entering 'TV' in global search box the Global dropdown autoselected 'Electronics'");
	}

	public void ElectronicsLinkVerificationWithInvalidData() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		GlobalSearch_TextBox.clear();
		GlobalSearch_TextBox.sendKeys(sheet.getRow(7).getCell(2).toString());
		SearchSymbol.click();
		Assert.assertTrue(DropDownLabelId.getText().equals(sheet.getRow(11).getCell(0).toString()));
		ColorLog.d("Upon entering 'Tshirt' in global search box the Global dropdown autoselected 'All'");
	}

	public void HomeAndKitchenLinkVerificationWithValidData() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		HomeNKitchen_Link.click();
		Assert.assertTrue(DropDownLabelId.getText().equals(sheet.getRow(9).getCell(0).toString()));
		ColorLog.d("'Home & Kitchen' Link is available and upon clicking it the Global dropdown autoselected to 'Home & Kitchen'");
		GlobalSearch_TextBox.sendKeys(sheet.getRow(9).getCell(1).toString());
		SearchSymbol.click();
		Assert.assertTrue(DropDownLabelId.getText().equals(sheet.getRow(9).getCell(0).toString()));
		ColorLog.d("Upon entering 'Utensils' the Global dropdown autoselected to 'Home & Kitchen'");
	}

	public void HomeAndKitchenLinkVerificationWithInvalidData() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		GlobalSearch_TextBox.clear();
		GlobalSearch_TextBox.sendKeys(sheet.getRow(7).getCell(2).toString());
		SearchSymbol.click();
		Assert.assertTrue(DropDownLabelId.getText().equals(sheet.getRow(11).getCell(0).toString()));
		ColorLog.d("Upon entering 'Tshirt' the Global dropdown autoselected to 'All'");
	}
	
	public void globalSearchTextBoxVerification() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		Assert.assertTrue(GlobalSearch_TextBox.isDisplayed());
		GlobalSearch_TextBox.sendKeys(sheet.getRow(1).getCell(0).toString());
		String value = GlobalSearch_TextBox.getAttribute("value");
		assertEquals(value,sheet.getRow(1).getCell(0).toString());
		ColorLog.d("Global Search text box is displayed and data entered is visible");
	}

	public void searchImageVerification() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		Assert.assertTrue(SearchSymbol.isDisplayed());
		GlobalSearch_TextBox.clear();
		GlobalSearch_TextBox.sendKeys(sheet.getRow(1).getCell(0).toString());
		SearchSymbol.click();
		ColorLog.d("Clicked on Search");
		System.out.println(TShirtSearchText.getText());
		Assert.assertTrue(TShirtSearchText.getText().contains(sheet.getRow(1).getCell(1).toString()));
		ColorLog.d("Search Symbol beside search text box is available and clickable");

	}
	
	public void clickOnTShirt1() {
		wait.until(ExpectedConditions.visibilityOf(Tshirt1));
		Tshirt1.click();
		ColorLog.d("Clicked on T-Shirt1");
	}
	
	public void clickOnTShirt2() {
		wait.until(ExpectedConditions.visibilityOf(Tshirt2));
		Tshirt2.click();
		ColorLog.d("Clicked on T-Shirt2");
	}
	
	public void TshirtDetails() {
		System.out.println(TshirtName.getText());
		System.out.println(TshirtBrand.getText());
		System.out.println(TshirtPrice.getText());
		System.out.println(InStock.getText());
	}
	
	public void clickOnAddToWishList() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("GlobalSearchData");
		wait.until(ExpectedConditions.visibilityOf(WishList));
		WishList.click();
		ColorLog.d("Clicked on 'Add to Wishlist'");
		Assert.assertTrue(SignInValidation.getText().contains(sheet.getRow(13).getCell(0).toString()));
		ColorLog.d("Landed on to sign in page!");
	}
	
	public void clickOnDeleteItem() {
		wait.until(ExpectedConditions.visibilityOf(DeleteFirstTshirtItem));
		DeleteFirstTshirtItem.click();
		ColorLog.d("Clicked on 'Delete' T-Shirt1");
	}
	
	public void clickOnSaveForLater() {
		wait.until(ExpectedConditions.visibilityOf(Shirt2SaveForLater));
		Shirt2SaveForLater.click();
		ColorLog.d("Clicked 'Save For Later' for T-Shirt2");
	}

}
