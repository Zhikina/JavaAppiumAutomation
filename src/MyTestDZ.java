import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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

public class MyTestDZ {
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
    public void  testSaveArcicalAndDelet()
    {
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


    waitForElementAndClic(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find 'Search Wikipedia' input",
                    5
                    );

    waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find artical title",
                    15
                    );

        waitForElementAndClic(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find buttonto open artice options",
                5
        );




        waitForElementAndClic(
            By.xpath("//*[@text='Add to reading list']"),
        "Cannot find options to articale to reading list FOR 1",
                12
                );

    waitForElementAndClic(
            By.id("org.wikipedia:id/onboarding_button"),
        "Cannot find 'Got it' tip owerlay",
                15
                );

    waitForElementClear(
            By.id("org.wikipedia:id/text_input"),
        "Cannot find input",
                5
                );



    String name_of_folder = "ForMyTest";
    waitForElementAndSendKeys(
            By.id("org.wikipedia:id/text_input"),
             name_of_folder,
            "Cannot put text inte artical",
            5
            );

    waitForElementAndClic(
            By.xpath("//*[@text='OK']"),
            "Cannot press OK button",
                    5
                    );


    waitForElementAndClic(
            By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot close articalfor x",
                    5
                    );


    //снова поиск, открыть статью и добавить ее

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


        waitForElementAndClic(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java version history']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find artical title",
                15
        );



        String title_befor_rotater = woitForElementAndGetAtributr(
                By.id("org.wikipedia:id/view_page_title_text"),
                //By.id('org.wikipedia:id/view_page_title_text'),
                "text",
                "Cannot finf title of  artical",
                15
        );




        waitForElementAndClic(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find buttonto open artice options",
                5
        );


        waitForElementAndClic(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find options to articale to reading list",
                5
        );


        waitForElementAndClic(
                By.xpath("//*[@text='"+name_of_folder+"']"),
                "Cannot find options to articale to reading li2st",
                5
        );


        waitForElementAndClic(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close articalfor x",
                5
        );








    waitForElementAndClic(
            By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
            "Cannot find novigation button  to my list",
                    5
                    );

    waitForElementAndClic(
            By.xpath("//*[@text='"+name_of_folder+"']"),
            "Cannot find created folder",
                    5
                    );

    swipeElementToLeft(
            By.xpath("//*[@text='Java (programming language)']"),
        "Cannot find saved article"
                );
    waitForElementNotPresent(
            By.xpath("//*[@text='Java (programming language)']"),
            "Cannot delete saved artical",
                    5
                    );



    // проверить что есть одна статья и кликнуть по нет

        int amount_of_search_results = getAmountOfElements(
                By.id("org.wikipedia:id/page_list_item_container")
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results == 1
        );

        waitForElementAndClic(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java version history']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );


        String title_after_second_rotater = woitForElementAndGetAtributr(
                By.id("org.wikipedia:id/view_page_title_text"),
                //By.id('org.wikipedia:id/view_page_title_text'),
                "text",
                "Cannot finf title of  artical",
                15
        );

        Assert.assertEquals(
                "artical title have been change after scrin rotate",
                title_befor_rotater,
                title_after_second_rotater
        );




}

    private WebElement waitForElementAndClic(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
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

    protected  void swipeElementToLeft(By by, String error_message)
    {
        WebElement element =  waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int midle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action.
                press(right_x,midle_y)
                .waitAction(300)
                .moveTo(left_x,midle_y)
                .release()
                .perform();


    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message+ "/n");

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private  String woitForElementAndGetAtributr(By by, String attribute, String error_message, long timeoutInSecond)
    {
        WebElement element =  waitForElementPresent(by,error_message,timeoutInSecond);
        return element.getAttribute(attribute);
    }

    protected int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

}
