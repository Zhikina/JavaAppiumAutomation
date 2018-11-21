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

public class ForMyDZ
{
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
    public void testChangeScreenOrientionOnSearchOnREsult()
    {
        waitForElementAndClic(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannnot find search imput",
                5
        );
        waitForElementAndClic(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'bject-orie' input",
                15
        );


        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Error! Title not found"

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



    protected void assertElementPresent(By by, String error_message)
    {
        int amaut_of_elements = getAmountOfElements(by);
        if(amaut_of_elements>0){
            System.out.println("Success! Title found on page");
        }else {

            throw  new AssertionError(error_message);
         }
    }


    protected int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
    }

}
