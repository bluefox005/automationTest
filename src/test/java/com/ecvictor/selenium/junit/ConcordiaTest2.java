package com.ecvictor.selenium.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by huao on 2017/6/2.
 */
public class ConcordiaTest2 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    private int timeout = 5000;
    private int interval = 500;

    @Before
    public void setUp() throws Exception {
        //chose driver type
        String os = (System.getProperty("os.name"));

        if (os.equalsIgnoreCase("Mac OS X"))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        else System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--kiosk");
        driver = new ChromeDriver(chromeOptions);

        baseUrl = "https://www.concordia.ca";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

     @Test
    public void testSearch() throws Exception {
        driver.get(baseUrl + "/");
        //driver.findElement(By.xpath("//div[@id='main-navbar']/div/div/a")).click();
        driver.findElement(By.xpath("//a[@data-toggle=\"collapse\" and @class=\"search hidden-phone concordia-icon concordia-search-icon\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("input-search")).clear();
        driver.findElement(By.id("input-search")).sendKeys("tax\n");
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//a[@class=\"gs-title\" and @dir=\"ltr\" and @data-ctorig=\"https://www.concordia.ca/students/birks/tuition-and-fees.html\"]")).click();

         driver.findElement(By.xpath("id('___gcse_0')/div/div/div/div[5]/div[2]/div[1]/div/div[2]/div[1]/table/tbody/tr/td[2]/div[1]/a")).click();
         Thread.sleep(3000);

         //switch windows tabs
         Set<String> tab_handles = driver.getWindowHandles();
         int number_of_tabs = tab_handles.size();
         int new_tab_index = number_of_tabs-1;
         driver.switchTo().window(tab_handles.toArray()[new_tab_index].toString());

        //assertEquals("Tuition and fees", driver.findElement(By.xpath("id('content-main')/div/div/div[2]/div[1]/div/h1")).getText());
        assertEquals("Tuition and fees", driver.getTitle());
        // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | gtm_autoEvent_1496457396370 | 30000]]
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
