package primary;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetUpAndLaunch extends LoadProperties{
	WebDriver driver;
	HashMap<String, Object> chromePrefs;
	ChromeOptions options;

	public WebDriver SetUpBrowserAndLaunch() {
		LoadProperties lp = new LoadProperties();
		chromePrefs = new HashMap<>();
		options = new ChromeOptions();
		String BrowserName = lp.getProp("Browser");
		switch(BrowserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			chromePrefs.put("profile.default_content_setting_values.notifications", 0);
			chromePrefs.put("profile.default_content_settings.popups",0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"//Downloads");
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("start-maximized");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			driver.get(lp.getProp("URL"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get(lp.getProp("URL"));
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get(lp.getProp("URL"));
			break;

		default:
			WebDriverManager.chromedriver().setup();
			chromePrefs.put("profile.default_content_setting_values.notifications", 0);
			chromePrefs.put("profile.default_content_settings.popups",0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"//Downloads");
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("start-maximized");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
		return driver;
	}

}
