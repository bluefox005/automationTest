//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ecvictor.selenium.junit;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class GuruTest5 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public GuruTest5() {
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("Called openBrowser");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.baseUrl = "http://www.guru99.com/";
        this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
    }

    //test drop-down list
    @Test
    public void testUntitled2() throws Exception {
        this.driver.get(this.baseUrl + "/");

        Actions builder= new Actions(driver) ;
        Action mouseOverTesting = builder
                .moveToElement(driver.findElement(By.xpath("//a[@href=\"/software-testing.html\" and @class]")))
                .build();
        mouseOverTesting.perform();
        Action mouseOverMT = builder
                .moveToElement(driver.findElement(By.linkText("Manual Testing")))
                .build();
        mouseOverMT.perform();
        driver.findElement(By.linkText("Manual Testing")).click();
        Assert.assertEquals("test", this.driver.getTitle(), "Software Testing Tutorial: Free Course");
    }

    @After
    public void tearDown() throws Exception {
        this.driver.quit();
        String verificationErrorString = this.verificationErrors.toString();
        if(!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }

    }

    private boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (NoSuchElementException var3) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            this.driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException var2) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        String var3;
        try {
            Alert alert = this.driver.switchTo().alert();
            String alertText = alert.getText();
            if(this.acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }

            var3 = alertText;
        } finally {
            this.acceptNextAlert = true;
        }

        return var3;
    }
}
