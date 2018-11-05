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


public class FirstTest
{

    private AppiumDriver driver;

   @Before
    public void sepUP () throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\Swan\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");


        driver  = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void  testSearchValue()
    {
        waitForElementAndClic(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );



       WebElement search_element =  waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find artical title",
                15
        );


        String searche_text = search_element.getAttribute("text");

        Assert.assertEquals(
                "No desired value!",
                "Search…",
                searche_text
        );
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
      {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(ExpectedConditions.presenceOfElementLocated(by)
            );
      }


     private WebElement waitForElementAndClic(By by, String error_message, long timeoutInSeconds)
       {
             WebElement element =  waitForElementPresent(by,error_message,timeoutInSeconds);
             element.click();
             return element;
        }

}
