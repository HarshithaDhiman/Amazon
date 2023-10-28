package pageObject;

import static org.testng.Assert.assertEquals;

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
import primary.LoadProperties;

public class Language extends LoadProperties{
	WebDriver driver;
	WebDriverWait wait;
	ExcelDataIntegration exceldata;
	Actions act;

	public Language(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = wait;
		exceldata = new ExcelDataIntegration();
		act = new Actions(driver);
	}
	@FindBy(xpath = "//div[@id='nav-flyout-icp']//a[contains(@href,'#switch-lang')]") private List<WebElement> NoOfLanguages;

	@FindBy(xpath = "//span[@class='icp-nav-link-inner']") private WebElement LanguageSelection;
	@FindBy(xpath = "(//i[@class='icp-radio icp-radio-active'])[1]/following-sibling::span[2]") private WebElement DefaultLangSelected;
	@FindBy(xpath = "(//div[@id='nav-tools']/descendant::div)[1]") private WebElement LangSelected;
	@FindBy(xpath = "//div[@id='nav-flyout-icp']//a[2]//span[1]//i[1]") private WebElement HindiLanguage;
	@FindBy(xpath = "//div[@id='nav-flyout-icp']//a[4]//span[1]//i[1]") private WebElement TeluguLanguage;
	@FindBy(xpath = "//div[@id='nav-flyout-icp']//a[8]//span[1]//i[1]") private WebElement MarathiLanguage;

	public void hoverOverLanguage() {
		wait.until(ExpectedConditions.visibilityOf(LanguageSelection));
		Action hoverOver = act.moveToElement(LanguageSelection).build();
		hoverOver.perform();
	}

	public void defaultLanguageVerification() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("LanguageData");
		Assert.assertTrue(DefaultLangSelected.getText().equals(sheet.getRow(1).getCell(0).toString()));
		ColorLog.d("By Default English Language is selected");
	}

	public void selectTeluguLang() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("LanguageData");
		TeluguLanguage.click();		
		driver.navigate().refresh();
		//System.out.println(LangSelected.getText());
		Assert.assertTrue(LangSelected.getText().equals(sheet.getRow(1).getCell(1).toString()));
		ColorLog.d("When Language 'Telugu' is selected, 'TE' is displayed instead of 'EN'");
	}

	public void selectMarathiLanguage() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("LanguageData");
		MarathiLanguage.click();		
		driver.navigate().refresh();
		//System.out.println(LangSelected.getText());
		Assert.assertTrue(LangSelected.getText().equals(sheet.getRow(1).getCell(2).toString()));
		ColorLog.d("When Language 'Marathi' is selected, 'MR' is displayed instead of 'EN'");
	}

	public void selectHindiLanguage() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("LanguageData");
		HindiLanguage.click();		
		driver.navigate().refresh();
		//System.out.println(LangSelected.getText());
		Assert.assertTrue(LangSelected.getText().equals(sheet.getRow(1).getCell(3).toString()));
		ColorLog.d("When Language 'Hindi' is selected, 'HI' is displayed instead of 'EN'");
	}

	public void numberOfLanguages() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("LanguageData");
		for (int i = 0; i<NoOfLanguages.size();i++) {
			assertEquals(NoOfLanguages.get(i).getText(), sheet.getRow(5).getCell(i).toString());
		}
	}
}
