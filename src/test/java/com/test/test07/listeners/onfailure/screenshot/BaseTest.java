package com.test.test07.listeners.onfailure.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import com.util.TestUtil;

public class BaseTest {

	public static WebDriver driver;

	public void initialize(String url, String browserName) {

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(url);
	}

	public void screenshotFailedTest(String testMethodName) {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// creates src/FailedTestsScreenshots folder after execution
		// String filePath =
		// System.getProperty("user.dir")+"/FailedTestsScreenshots/failshot_"+this.getClass().getName()+"_"+date+".png";
		String filePath = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + testMethodName + "_" + date
				+ ".png";
		File destination = new File(filePath);

		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, see "src/ExtentReportsscreenshots" folder
		String fileUrl = System.getProperty("user.dir") + "/ExtentReportsScreenshots/" + screenshotName + date + ".png";
		File destination = new File(fileUrl);
		FileUtils.copyFile(source, destination);
		return fileUrl;
	}

	public void killBrowser() {
		if (driver != null) {
			driver.quit();
		}
		Reporter.log("Browser Session End");
	}

}