package com.ExcelRPorject3.Amazon;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObject.CountryPage;
import pageObject.CreateAccountAndLogin;
import pageObject.GlobalSearchDropdown;
import pageObject.HomePage;
import pageObject.Language;
import pageObject.ProductDetailsPage;
import primary.ColorLog;
import primary.SetUpAndLaunch;

@Listeners(com.ExcelRPorject3.Amazon.ListenerTest.class)
public class TestCasesImplementation {
	WebDriver driver;
	WebDriverWait wait;
	SetUpAndLaunch setup;
	HomePage home;
	CreateAccountAndLogin acclogin;
	GlobalSearchDropdown gsd;
	Language lang;
	CountryPage country;ProductDetailsPage product;

	@BeforeTest
	public void driverInitialisation() {
		setup = new SetUpAndLaunch();
		driver = setup.SetUpBrowserAndLaunch();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		home = new HomePage(driver, wait);
		acclogin = new CreateAccountAndLogin(driver, wait);
		gsd = new GlobalSearchDropdown(driver, wait);
		lang = new Language(driver, wait);
		country = new CountryPage(driver, wait);
		product = new ProductDetailsPage(driver, wait);
	}

	@Test(priority = 1)
	public void homePage_Validation() {
		String currentUrl = home.homePageVerification();
		System.out.println(currentUrl);
		assertEquals(currentUrl, "https://www.amazon.in/");
		assert driver.getTitle().contains("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in"): "Failed to launch Amazon India website";
		ColorLog.d("Successfully Landed on to the home page of 'Amazon.in'");
	}

	@Test(priority = 2)
	public void homePageSignIn_Validation() throws IOException {
		home.homePageValidation();
		home.hoverOverAndClickOnSignIn();
		home.signPageVerification();
	}

	@Test(priority = 3)
	public void createAccount() throws IOException {
		home.homePageValidation();
		home.hoverOverAndClickOnSignIn();
		acclogin.CreateAccountBtnVerification();
		acclogin.createAccount();
	}

	@Test(priority = 4)
	public void loginWithValidCredentials() throws IOException  {
		home.homePageValidation();
		home.hoverOverAndClickOnSignIn();
		acclogin.loginWithValidCredentials();
	}

	@Test(priority = 5)
	public void loginWithInvalidCredentials() throws IOException {
		home.homePageValidation();
		home.hoverOverAndClickOnSignIn();
		acclogin.loginWithInvalidCredentials();
	}

	@Test(priority = 6)
	public void HeaderAndAllDropdownElementsValidation() throws IOException {
		home.homePageValidation();
		gsd.headerElements();
		gsd.allDropDownVerification();
	}

	@Test(priority = 7)
	public void GlobalSearchBox_Validation() throws IOException {
		home.homePageValidation();
		gsd.globalSearchTextBoxVerification();
		gsd.searchImageVerification();
	}

	@Test(priority = 8)
	public void GlobalElectronics_Validation() throws IOException {
		home.homePageValidation();
		gsd.ElectronicsLinkVerificationWithValidData();
		gsd.ElectronicsLinkVerificationWithInvalidData();
	}

	@Test(priority = 9)
	public void GlobalHome_Kitchen_Validation() throws IOException {
		home.homePageValidation();
		gsd.HomeAndKitchenLinkVerificationWithValidData();
		gsd.HomeAndKitchenLinkVerificationWithInvalidData();
	}

	@Test(priority = 10)
	public void NoOfLanguages_Validation() throws IOException {
		home.homePageValidation();
		lang.hoverOverLanguage();
		lang.numberOfLanguages();
	}

	@Test(priority = 11)
	public void defaultLanguage_Validation() throws IOException {
		home.homePageValidation();
		lang.hoverOverLanguage();
		lang.defaultLanguageVerification();
	}

	@Test(priority = 12)
	public void otherLang_Validation() throws IOException {
		home.homePageValidation();
		lang.hoverOverLanguage();
		lang.selectTeluguLang();
		lang.hoverOverLanguage();
		lang.selectMarathiLanguage();
		lang.hoverOverLanguage();
		lang.selectHindiLanguage();
	}

	@Test(priority = 13)
	public void changeCountryToCanada_Validation() throws IOException {
		home.homePageValidation();
		country.countryNRegionLinkVerification();
		country.changeCountryCanada();
		country.windowHandles();
		country.changeCountryCanadaValidation();
		country.hoverOnCountryRegion();
		country.canadaPageValidation();
	}

	@Test(priority = 14)
	public void changeCountryToGermany_Validation() throws IOException {
		home.homePageValidation();
		country.countryNRegionLinkVerification();
		country.changeCountryGermany();
		country.windowHandles();
		country.changeCountryGermanyValidation();
		country.clickOnCookie();
		country.hoverOnCountryRegion();
		country.GermanyPageValidation();
	}

	@Test(priority = 15)
	public void searchProductWithBrandFilter_Validation() throws IOException {
		home.homePageValidation();
		home.clickOnSearchWithText();
		home.searchProductWithBrandFilter();
		home.verifyDropdownWithBrandSelection();
	}

	@Test(priority = 16)
	public void searchProductWithPriceFilter_Validation() throws IOException {
		home.homePageValidation();
		home.clickOnSearchWithText();
		home.searchProductWithPriceFilter();
		home.verifyDropdownWithPriceSelection();
	}

	@Test(priority = 17)
	public void searchProductWithMultipleFilters_Validation() throws IOException {
		home.homePageValidation();
		home.clickOnSearchWithText();
		home.searchProductWithBrandFilter();
		home.searchProductWithMobilePriceFilter();
	}

	@Test(priority = 18)
	public void Product1Details_Validation() throws IOException {
		home.homePageValidation();
		product.clickOnAll();
		product.clickOnWomensFashions();
		product.clickOnWatches();
		product.clickOnMichaelKors();
		product.clickOnProduct1();
		product.windowHandles();
		product.productDetails();
	}

	@Test(priority = 19)
	public void Product2Details_Validation() throws IOException {
		home.homePageValidation();
		product.clickOnAll();
		product.clickOnWomensFashions();
		product.clickOnWatches();
		product.clickOnMichaelKors();
		product.clickOnProduct2();
		product.windowHandles();
		product.productDetails();
	}

	@Test(priority = 20)
	public void AddProductsToCart_Validation() throws IOException {
		home.homePageValidation();
		product.clickOnAll();
		product.clickOnWomensFashions();
		product.clickOnWatches();
		product.clickOnMichaelKors();
		product.clickOnProduct1();
		product.windowHandles();
		product.clickOnAddToCart();
		product.closeTab();
		product.switchToMainWindow();
		product.clickOnAll();
		product.clickOnWomensFashions();
		product.clickOnWatches();
		product.clickOnMichaelKors();
		product.clickOnProduct2();
		product.windowHandles();
		product.clickOnAddToCart();
		//product.skipWarranty();
		product.clickOnGoToCart();
		product.TotalItemsInCart();
	}

	@Test(priority = 21)
	public void changeInQuantityValidation() throws IOException {
		home.homePageValidation();
		product.clickOnAll();
		product.clickOnWomensFashions();
		product.clickOnWatches();
		product.clickOnMichaelKors();
		product.clickOnProduct1();
		product.windowHandles();
		product.clickOnAddToCart();
		product.clickOnGoToCart();
		product.TotalItemsInCart();
		product.changeQuantity();
		product.refresh();
		product.TotalItemsInCart();
	}

	@Test(priority = 22)
	public void proceedToBuyValidation() throws IOException {
		home.homePageValidation();
		product.clickOnAll();
		product.clickOnWomensFashions();
		product.clickOnWatches();
		product.clickOnMichaelKors();
		product.clickOnProduct1();
		product.windowHandles();
		product.clickOnAddToCart();
		product.clickOnGoToCart();
		product.TotalItemsInCart();
		product.proceedToBuy();
	}
	
	@Test(priority = 23)
	public void delteItemBeforeProceedToBuy_Validation() throws IOException {
		home.homePageValidation();
		gsd.searchImageVerification();
		gsd.clickOnTShirt1();
		product.windowHandles();
		product.clickOnAddToCart();
		product.closeTab();
		product.switchToMainWindow();
		gsd.clickOnTShirt2();
		product.windowHandles();
		product.clickOnAddToCart();
		product.clickOnGoToCart();
		product.TotalItemsInCart();
		gsd.clickOnDeleteItem();
		product.refresh();
		product.TotalItemsInCart();
	}
	
	@Test(priority = 24)
	public void addToSaveLaterBeforeProceedToBuy_Validation() throws IOException {
		home.homePageValidation();
		gsd.searchImageVerification();
		gsd.clickOnTShirt1();
		product.windowHandles();
		product.clickOnAddToCart();
		product.closeTab();
		product.switchToMainWindow();
		gsd.clickOnTShirt2();
		product.windowHandles();
		product.clickOnAddToCart();
		product.clickOnGoToCart();
		product.TotalItemsInCart();
		gsd.clickOnSaveForLater();
		product.refresh();
		product.TotalItemsInCart();
	}
	
	@Test(priority = 25)
	public void ProductThroughSearchTextBox_Validation() throws IOException {
		home.homePageValidation();
		gsd.searchImageVerification();
		gsd.clickOnTShirt1();
		product.windowHandles();
		gsd.TshirtDetails();
	}
	
	@Test(priority = 26)
	public void AddToWishListThroughSearchTextBox() throws IOException {
		home.homePageValidation();
		gsd.searchImageVerification();
		gsd.clickOnTShirt1();
		product.windowHandles();
		gsd.TshirtDetails();
		gsd.clickOnAddToWishList();
	}
	
}
