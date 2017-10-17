package com.ecvictor.selenium.junit;

/**
 * Created by samsung on 2017/3/23.
 * 多级网页
 */


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class GuruTest2 {
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
    public void testUntitled2() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.linkText("Learn Test Management")).click();
        driver.findElement(By.linkText("Software Testing")).click();
        driver.findElement(By.linkText("Software Testing as your career")).click();
        //validation
        Assert.assertEquals("test",driver.getTitle(),"Software Testing as a Career - Complete Guide");
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


