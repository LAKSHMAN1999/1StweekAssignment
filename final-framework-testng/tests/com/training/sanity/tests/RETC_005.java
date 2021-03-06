package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ChangePassword;
import com.training.pom.LoginPOM;
import com.training.pom.Myprofile;
//import com.training.pom.LoginPOM1;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_005 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private Myprofile myprofile;
	private ChangePassword changepassword;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		myprofile =new Myprofile(driver);
		changepassword=new ChangePassword(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void validLoginTest() {
		loginPOM.sendUserName("LAKSHMAN");
		loginPOM.sendPassword("lucky@123");
		loginPOM.clickLoginBtn();
		myprofile.clickMyprofile();
		changepassword.clickChangePassword();
		changepassword.sendCurrentPassword("lucky@123");
		changepassword.sendNewPassword("mehadi");
		changepassword.sendConfirmNewPassword("mehadi");
		changepassword.clickSaveChanges();
		screenShot.captureScreenShot("First");
		//Exporting to GIT
	}
}
