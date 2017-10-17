package com.ecvictor.selenium.junit;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class kijiji {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private String currentURL = null;
    private String previousURL = null;
    private WebDriverWait wait = new WebDriverWait(driver, 10);


    @Before
    public void setUp() throws Exception {
        System.out.println("Called openBrowser");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        baseUrl = "http://www.kijiji.ca/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled2() throws Exception {
        //driver.get(baseUrl);
        //driver.findElement(By.linkText("City of Montréal")).click();
        driver.get(baseUrl + "b-ville-de-montreal/l1700281?siteLocale=en_CA");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("LoginEmailOrNickname")).clear();
        driver.findElement(By.id("LoginEmailOrNickname")).sendKeys("bluefox005.ca@gmail.com");
        driver.findElement(By.id("login-password")).clear();
        driver.findElement(By.id("login-password")).sendKeys("KEyu0618hu");
        driver.findElement(By.id("login-rememberMe")).click();
        driver.findElement(By.id("SignInButton")).click();
        Assert.assertEquals("My Kijiji","My Kijiji",driver.findElement(By.xpath("//a[@class='headerButtonMyKijiji-1949267083']")).getText());

        currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);

        driver.findElement(By.linkText("Post Ad")).click();
        driver.findElement(By.id("CategoryId145")).click();
        driver.findElement(By.id("PhoneNumber")).click();
        driver.findElement(By.id("PhoneNumber")).clear();
        driver.findElement(By.id("PhoneNumber")).sendKeys("4502330077");
        driver.findElement(By.id("PriceAmount")).clear();
        driver.findElement(By.id("PriceAmount")).sendKeys("250");
        driver.findElement(By.xpath("(//input[@id='forsaleby_s'])[2]")).click();
        driver.findElement(By.id("postad-title")).clear();
        driver.findElement(By.id("postad-title")).sendKeys("SCROLLING LED PROGRAMMABLE SIGNS test");
        driver.findElement(By.id("pstad-descrptn")).clear();
        driver.findElement(By.id("pstad-descrptn")).sendKeys("Our Strengths:\n• Local Manufacture With High Quality LED And Parts.\n• Can Support Indoor & Semi Outdoor & Outdoor\n• Many Choices On Single Color, Multi Color And Full Color\n• Pixel Ranges From P4 To P16\n• Size Can Be Customized, We Can Make As Big As You Wish\n• Fully Programmable From Pc, Full Control The Content Of Your Sign.\n• Support Multi-language Text, Video And Picture Display\n• Warranty Can Be Extended Up 5 Years\n• Free Software Provided\n• Unbeatable Price\n\nChoose for variety of sizes and colors, programmable LED display (software included)\n\nRed, Green ,Blue ,White or Yellow , P10, 41\"x9\", available for immediate pickup for $250.\nRed, Green ,Blue ,White or Yellow , P10, 41\"x15\", available for immediate pickup for $400.\nMulti color , P10, 41\"x9\", available for immediate pickup for $350.\nMulti color , P10, 41\"x15\", available for immediate pickup for $500.\nFor other products,please contact us.\n\nCall now and get a free quote for custom mode sizes. We are professional provider of LED products, more details, please visit our store for all your LED signs needs!!\n\nStore Location: 227 Boul. Des Laurentides，Laval，QC H7G 2T7\n\nTEL: 450-233-0077\nEmail: emaison.ca@gmail.com\nhttp://www.emaison.ca\nhttps://www.facebook.com/leddisplaymontreal");
        driver.findElement(By.id("PostalCode")).clear();
        driver.findElement(By.id("PostalCode")).sendKeys("H7G 2T7");
        //driver.findElement(By.id("ImageUploadButton")).click();
        //driver.findElement(By.id("html5_1bdr5b6ro43pjeki4t1adq1rtv3")).clear();
        //driver.findElement(By.id("html5_1bdr5b6ro43pjeki4t1adq1rtv3")).sendKeys("");
        driver.findElement(By.name("saveAndCheckout")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        previousURL = driver.getCurrentUrl();

        ExpectedCondition e = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (!d.getCurrentUrl().equals(previousURL));
            }
        };

        wait.until(e);
        currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        //Assert.assertEquals("test","test",driver.getCurrentUrl());

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
