package com.ecvictor.selenium.junit;

/**
 * Created by samsung on 2017/3/23.
 * 图片点击+xpath使用
 */


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class GuruTest3 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.out.println("Called openBrowser");
        //F:\huao\project\cucumber-jvm-template-master\src\test\resources\drivers\chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "F:\\huao\\project\\cucumber-jvm-template-master\\src\\test\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        baseUrl = "http://www.guru99.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("(//i)[4]")).click();  //好像程序没点,怎么点图片
//        driver.findElement(By.xpath("//*[contains(@class,'rt-grid-2 rt-omega')][2]")).click();  //好像程序没点,怎么点图片
        //validation
        assertEquals("Free Selenium Tutorials",driver.getTitle());
        //Assert.assertEquals("test",driver.getTitle(),"Java Tutorial for Beginners: Learn in 7 Days");
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



