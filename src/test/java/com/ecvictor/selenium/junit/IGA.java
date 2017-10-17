package com.ecvictor.selenium.junit;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.mozilla.javascript.regexp.SubString;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class IGA {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

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

        baseUrl = "https://www.iga.net/en";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //select 3 items and check the total price
    @Test
    public void testUntitled() throws Exception {
        driver.get(baseUrl);

        Actions actions = new Actions(driver);
        WebElement menu = driver.findElement(By.id("header_0_ctl08_MenuItemList_SubMenuRegularMenu_0_LandingPageLink_0"));
        actions.moveToElement(menu).perform();

        WebElement subMenu = driver.findElement(By.linkText("In Flyer"));
        actions.moveToElement(subMenu);
        actions.click().build().perform();

        driver.findElement(By.xpath("//div[@id='body_0_main_1_ProductSearch_GroceryBrowsing_TemplateResult_SearchResultListView_ctrl0_ItemTemplatePanel_0']/div/div/div/div[3]/div/div/div/span/button")).click();
        driver.findElement(By.xpath("id('MyStoreSidebar_Screen1Panel')/div[3]/div[2]/div[2]/a")).click();
        Thread.sleep(1000);
        String x=driver.findElement(By.xpath("//div[@id='body_0_main_1_ProductSearch_GroceryBrowsing_TemplateResult_SearchResultListView_ctrl0_ctl00_0_ListOfPrices_0']/div/div[1]/div/span/span")).getText();
        float price1=Float.parseFloat(x.substring(1));
        String y=driver.findElement(By.xpath("//div[@id='body_0_main_1_ProductSearch_GroceryBrowsing_TemplateResult_SearchResultListView_ctrl0_ctl00_1_ListOfPrices_1']/div/div[1]/div/span/span")).getText();
        driver.findElement(By.xpath("//div[@id='body_0_main_1_ProductSearch_GroceryBrowsing_TemplateResult_SearchResultListView_ctrl0_ItemTemplatePanel_1']/div/div/div/div[3]/div/div/div/span/button")).click();
        float price2=Float.parseFloat(y.substring(1));
        String z=driver.findElement(By.xpath("//div[@id='body_0_main_1_ProductSearch_GroceryBrowsing_TemplateResult_SearchResultListView_ctrl0_ctl00_2_ListOfPrices_2']/div/div[1]/div/span/span")).getText();
        driver.findElement(By.xpath("//div[@id='body_0_main_1_ProductSearch_GroceryBrowsing_TemplateResult_SearchResultListView_ctrl0_ItemTemplatePanel_2']/div/div/div/div[3]/div/div/div/span/button")).click();
        float price3=Float.parseFloat(z.substring(1));
        //System.out.println(Math.round(price1+price2+price3));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float total1= Float.valueOf(decimalFormat.format(price1+price2+price3));
        System.out.println(total1);
        Thread.sleep(4000);

        //check the cart price
        String m=driver.findElement(By.xpath("//span[@class='text--unbreakable']/span[4]")).getText();
        float total=Float.parseFloat(m.substring(1));
        System.out.println(total);

        assertEquals(total,total1,0.00);
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
