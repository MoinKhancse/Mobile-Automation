package Mobile_Automation;

//import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class App_Automation {
	public AndroidDriver driver;
	
	@BeforeTest
	public AndroidDriver startApplication() throws MalformedURLException {
		//File f = new File("src\test\resources");
		//File general_store_apk = new File(f,"General-Store.apk");
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();
		desiredCapabilities.setCapability("platformName", "android");
		desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
		desiredCapabilities.setCapability("appium:deviceName", "local");
		desiredCapabilities.setCapability("appium:udid", "emulator-5554");
		desiredCapabilities.setCapability("appium:deviceName", "local");
		desiredCapabilities.setCapability("appium:appPackage", "com.androidsample.generalstore");
		desiredCapabilities.setCapability("appium:appActivity", "com.androidsample.generalstore.SplashActivity");
		
		//desiredCapabilities.setCapability("appium:app",general_store_apk.getAbsolutePath()); 
		
		URL remoteUrl = new URL("http://127.0.0.1:4723");
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    return driver;
	}
	
	@Test
	public void testGeneralStoreAPK() throws MalformedURLException, InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		WebElement bd_option = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Bangladesh\"))"));
		bd_option.click();
		//driver.findElement(By.xpath("//*[@text='Angola']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("adb");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]")).click();
		driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")).click();
		driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnProceed\"]")).click();	
	}

	@AfterTest
	public void quitApplication() {
		//driver.removeApp("com.androidsample.generalstore");
		//driver.quit();
	}

}
