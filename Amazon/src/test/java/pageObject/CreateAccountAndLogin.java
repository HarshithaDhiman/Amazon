package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
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

public class CreateAccountAndLogin {
	WebDriver driver;
	WebDriverWait wait;
	public CreateAccountAndLogin(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = wait;
	}

	@FindBy(xpath = "//h1[@class='a-spacing-small']") private WebElement SignInPage;
	@FindBy(id = "createAccountSubmit") private WebElement CreateAccount_Btn;
	@FindBy(id = "ap_customer_name") private WebElement CreateAccountName;
	@FindBy(id = "ap_email")private WebElement CreateAccountEmail;
	@FindBy(id = "ap_password") private WebElement CreateAccountPassword;
	@FindBy(id = "continue") private WebElement VerifyMobileNum_Btn;
	@FindBy(id = "ab-registration-link") private WebElement CreateBusinessAccount;
	@FindBy(id = "ap_phone_number") private WebElement CreateAccountMobileNumber;

	@FindBy(xpath = "//input[@id='ap_email']") private WebElement SiginEmail;
	@FindBy(xpath = "//input[@id='continue']") private WebElement SigninContinue_Btn;
	@FindBy(xpath = "//input[@id='ap_password']") private WebElement SigninPassword;
	@FindBy(xpath = "//input[@id='signInSubmit']") private WebElement Signin_Btn;
	@FindBy(xpath = "//h1[@class='a-spacing-small']") private WebElement SigninValidation;

	@FindBy(xpath = "//span[@class='a-list-item']") private WebElement ErrorMsg;

	@FindBy(xpath = "(//span[@class='a-size-base-plus a-color-base a-text-normal'])[2]") private WebElement SonyDigiCam;
	@FindBy(xpath = "//input[@id='add-to-cart-button']") private WebElement AddToCart_Btn;
	@FindBy(xpath = "(//h4[text()='Added to Cart'])[2]") private WebElement AddedToCartMsg;
	@FindBy(xpath = "//span[@id=\"attach-sidesheet-view-cart-button\"]") private WebElement Form_Cart_Btn;
	@FindBy(xpath = "//h1[normalize-space()='Shopping Cart']") private WebElement ShoppingCartMsg;

	public void CreateAccountBtnVerification() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("AccountLoginData");
		wait.until(ExpectedConditions.visibilityOf(CreateAccount_Btn));
		Assert.assertTrue(CreateAccount_Btn.isDisplayed());
		Assert.assertTrue(CreateAccount_Btn.isEnabled());
		assertEquals(CreateAccount_Btn.getText(), sheet.getRow(1).getCell(0).toString());
		CreateAccount_Btn.click();
	}

	public void createAccount() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("AccountLoginData");
		CreateAccount_Btn.click();
		CreateAccountName.sendKeys(sheet.getRow(3).getCell(0).toString());
		CreateAccountMobileNumber.sendKeys(sheet.getRow(3).getCell(1).getRawValue());
		CreateAccountEmail.sendKeys(sheet.getRow(3).getCell(2).toString());
		CreateAccountPassword.sendKeys(sheet.getRow(3).getCell(3).toString());
		wait.until(ExpectedConditions.visibilityOf(VerifyMobileNum_Btn));
		VerifyMobileNum_Btn.click();
	}

	public void loginWithValidCredentials() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("AccountLoginData");
		wait.until(ExpectedConditions.visibilityOf(SiginEmail));
		SiginEmail.sendKeys(sheet.getRow(5).getCell(0).toString());
		SigninContinue_Btn.click();
		SigninPassword.sendKeys(sheet.getRow(5).getCell(1).toString());
		Signin_Btn.click();
		if(SigninValidation.getText().equals(sheet.getRow(5).getCell(2).toString())) {
			ColorLog.d("Successfully Signed In....");
		}
	}

	public void loginWithInvalidCredentials() throws IOException {
		XSSFSheet sheet = ExcelDataIntegration.readExcelData("AccountLoginData");
		SiginEmail.sendKeys(sheet.getRow(7).getCell(0).toString());
		SigninContinue_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(ErrorMsg));
		assertEquals(ErrorMsg.getText(), sheet.getRow(7).getCell(1).toString());
		ColorLog.d("We cannot find an account with that email address");
	}

}
