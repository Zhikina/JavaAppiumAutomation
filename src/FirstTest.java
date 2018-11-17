import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;


public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void sepUP() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Swan\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testSearchValue() {
        waitForElementAndClic(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannnot find search imput",
                5
        );


        WebElement search_element = waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find artical title",
                15
        );

        List<WebElement> searche_pages = search_element.findElements(By.id("org.wikipedia:id/page_list_item_title"));

        int i = 0;
        int calk = 0;
        while (i <= searche_pages.size()-1) {
               String element_search = searche_pages.get(i).getAttribute("text");
               if(element_search.toUpperCase().contains("JAVA")) {
                   calk = calk+1;
               }
               i++;
        }

        if(calk == searche_pages.size() ){
            System.out.println("Every word has a word 'JAVA' ");

        }else {
            System.out.println(" NOT every word has 'JAVA' ");
        }


    }





    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    private WebElement waitForElementAndClic(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }


    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;


    }

    private  WebElement waitForElementClear(By by, String error_message, long timeoutInSecond)
    {
        WebElement element = waitForElementPresent(by, error_message,timeoutInSecond);
        element.clear();
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message+ "/n");

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }



}