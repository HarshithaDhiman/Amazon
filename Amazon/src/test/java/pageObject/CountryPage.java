package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import primary.ColorLog;
import primary.ExcelDataIntegration;

public class CountryPage {
	WebDriver driver;
	WebDriverWait wait;
	ExcelDataIntegration exceldata;
	Actions act;

	public CountryPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = wait;
		exceldata = new ExcelDataIntegration();
		act = new Actions(driver);
	}
	@FindBy(xpath = "//span[@class='icp-nav-link-inner']") private WebElement LanguageSelection;
	@FindBy(xpath = "//div[@id='nav-flyout-icp']//div[@class='icp-mkt-change-lnk']") private WebElement ChangeCountry;
	@FindBy(xpath = "//h3[@class='a-spacing-extra-large']") private WebElement SelectCountryPage; //Website (Country/Region)
	@FindBy(xpath = "//span[normalize-space()='Select your preferred country/region website:']") private WebElement SelectCoountryText;//'Select your preferred country/region website:'
	@FindBy(xpath = "//span[contains(text(),'India')]") private WebElement DefaultCountry; //'India'
	@FindBy(id = "icp-dropdown") private WebElement SelectCountryDropdown;
	@FindBy(xpath = "//span[@class='a-dropdown-prompt']") private WebElement CountryDropdownText;
	@FindBy(xpath = "//span[@id='icp-save-button']//input") private WebElement GoToWebsite; //Egypt (مصر)
	@FindBy(xpath = "//body/div[@id='a-page']/header[@id='navbar-main']/div[@id='navbar']/div[@id='nav-flyout-anchor']/div[@id='nav-flyout-icp']/div[contains(@class,'nav-template nav-flyout-content nav-tpl-itemList')]/span[4]/span[1]") 
	private WebElement CountryCorrespondingMsg;
	@FindBy(xpath = "//div[@id='nav-flyout-icp']//span[contains(@class,'nav-item')]//span[@class='nav-text']") private WebElement CanadaCountryMsg;
	@FindBy(xpath = "//input[@id='sp-cc-accept']") private WebElement AcceptCookie;
	/*@FindBy(xpath = "") private WebElement;*/

	public void countryNRegionLinkVerification() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("CountryData");
		Action hoverOver = act.moveToElement(LanguageSelection).build();
		hoverOver.perform();
		ChangeCountry.click();
		assertEquals(SelectCountryPage.getText(), sheet.getRow(1).getCell(0).toString());
		assertEquals(SelectCoountryText.getText(), sheet.getRow(1).getCell(1).toString());
		assertEquals(DefaultCountry.getText(), sheet.getRow(1).getCell(2).toString());
	}
	
	public void hoverOnCountryRegion() {
		Action hoverOver = act.moveToElement(LanguageSelection).build();
		hoverOver.perform();
	}

	public void changeCountryCanada() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("CountryData");
		Select country =  new Select(SelectCountryDropdown);
		country.selectByVisibleText(sheet.getRow(3).getCell(0).toString());
		act.sendKeys(Keys.ENTER).build().perform();
		wait.until(ExpectedConditions.visibilityOf(CountryDropdownText));
		CountryDropdownText.getText().equals(sheet.getRow(3).getCell(0).toString());
		ColorLog.d("Country 'Canada' is selected and displayed on dropdown");
		wait.until(ExpectedConditions.visibilityOf(GoToWebsite));
		GoToWebsite.click();
	}
	
	public void changeCountryCanadaValidation() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("CountryData");
		String NewUrl = driver.getCurrentUrl();
		Assert.assertTrue(NewUrl.contains(sheet.getRow(3).getCell(1).toString()));
	}
	
	public void canadaPageValidation() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("CountryData");
		assertEquals(CanadaCountryMsg.getText(), sheet.getRow(3).getCell(2).toString());
		ColorLog.d("You are shopping on Amazon.ca");
	}
	public void windowHandles() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> itr = handles.iterator();
		String ParentWindow = itr.next();
		ParentWindow.hashCode();
		String ChildWindow = itr.next();
		driver.switchTo().window(ChildWindow);
	}

	public void changeCountryGermany() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("CountryData");
		Select country =  new Select(SelectCountryDropdown);
		country.selectByVisibleText(sheet.getRow(5).getCell(0).toString()); //Germany (Deutschland) 
		act.sendKeys(Keys.ENTER).build().perform();
		wait.until(ExpectedConditions.visibilityOf(CountryDropdownText));
		CountryDropdownText.getText().equals(sheet.getRow(5).getCell(1).toString());
		ColorLog.d("Country 'Germany' is selected and displayed on dropdown");
		wait.until(ExpectedConditions.visibilityOf(GoToWebsite));
		GoToWebsite.click();
	}
	
	public void changeCountryGermanyValidation() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("CountryData");
		String NewUrl = driver.getCurrentUrl();
		Assert.assertTrue(NewUrl.contains(sheet.getRow(5).getCell(2).toString()));
	}
	
	public void clickOnCookie() {
		wait.until(ExpectedConditions.visibilityOf(AcceptCookie));
		AcceptCookie.click();
	}
	public void GermanyPageValidation() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("CountryData");
		wait.until(ExpectedConditions.visibilityOf(CountryCorrespondingMsg));
		assertEquals(CountryCorrespondingMsg.getText(), sheet.getRow(5).getCell(3).toString());
		ColorLog.d("You are shopping on Amazon.de");
	}
}
