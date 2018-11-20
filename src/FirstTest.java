import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
//import org.apache.xpath.operations.String;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
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


    @Test
    public void  testSwipeAtricle()
    {
        waitForElementAndClic(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannnot find search imput",
                5
        );


        waitForElementAndClic(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium Search ' input",
                5
        );

         waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find artical title",
                15
        );



        swipeUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Cannot find the and of the artical",
                20
        );

    }


@Test
public void saveFirstArticleToMyList()
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
        "Cannot find options to articale to reading list",
        5
);

waitForElementAndClic(
        By.id("org.wikipedia:id/onboarding_button"),
        "Cannot find 'Got it' tip owerlay",
        5
);

waitForElementClear(
        By.id("org.wikipedia:id/text_input"),
        "Cannot find input",
        5
);



String name_of_folder = "Learning programing";
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





}
@Test
public void testAmountOfNotEmptySearch() {
    waitForElementAndClic(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            5
    );
    String search_line = "linkin park discography";
    waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search…')]"),
            search_line,
            "Cannnot find search imput",
            5
    );


    String search_result_locater = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[ @resource-id='org.wikipedia:id/page_list_item_container']";


    waitForElementPresent(
            By.xpath(search_result_locater),
            "Cannot find anything by the request " + search_line,
            15

    );


    int amount_of_search_results = getAmountOfElements(
            By.xpath(search_result_locater)
    );

    Assert.assertTrue(
            "We found too few results",
            amount_of_search_results > 0
    );


}
    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClic(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "tytytytytytytyty";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannnot find search imput",
                5
        );


        String search_result_locater =  "//*[@resource-id='org.wikipedia:id/search_results_list']/*[ @resource-id='org.wikipedia:id/page_list_item_container']"  ;
        String empty_result_lable = "//*[@text = 'No results found']";

      waitForElementPresent(
              By.xpath(empty_result_lable),
              "Cannot find empty results lable by the request "+ search_line,
              15
      );

        assertElementNotPresent(
                By.xpath(search_result_locater),
                "We've found sone result by request " + search_line
        );




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
String title_befor_rotater = woitForElementAndGetAtributr(
        By.id("org.wikipedia:id/view_page_title_text"),
        "text",
        "Cannot finf title of  artical",
        15
);


driver.rotate(ScreenOrientation.LANDSCAPE);

    String title_after_rotater = woitForElementAndGetAtributr(
            By.id("org.wikipedia:id/view_page_title_text"),
            //By.id('org.wikipedia:id/view_page_title_text'),
            "text",
            "Cannot finf title of  artical",
            15
    );


    Assert.assertEquals(
            "artical title have been change after scrin rotate",
            title_befor_rotater,
            title_after_rotater
    );


    driver.rotate(ScreenOrientation.PORTRAIT);
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


@Test
public  void testCheckSearchArticalinBackground()
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


    waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find 'Search Wikipedia' input",
            5
    );


    driver.runAppInBackground(2);


    waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find artical auto return",
            5
    );


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





    protected  void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height*0.8);
        int end_y = (int) (size.height*0.2);
        action.press(x,start_y).waitAction(timeOfSwipe).moveTo(x,end_y).release().perform();
    }


    protected void swipeUpQuick()
    {
        swipeUp(200);
    }


    protected void swipeUpToFindElement(By by, String errar_message, int max_swips)
    {
         int already_swipe = 0;
        while (driver.findElements(by).size() ==0){
          if(already_swipe > max_swips){
              waitForElementPresent(by,"Cannot find element by swipping up.\n " + errar_message,0);
              return;
          }
            swipeUpQuick();
            ++already_swipe;
        }
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

protected int getAmountOfElements(By by)
{
    List elements = driver.findElements(by);
    return elements.size();
}


protected void assertElementNotPresent(By by, String error_message)
{
    int amaut_of_elements = getAmountOfElements(by);
    if(amaut_of_elements>0){
        String defoult_message = "An eelement '" + by.toString()+ " ' supposed to be not present";
        throw  new AssertionError(defoult_message + " " + error_message);
    }
}

private  String woitForElementAndGetAtributr(By by, String attribute, String error_message, long timeoutInSecond)
{
    WebElement element =  waitForElementPresent(by,error_message,timeoutInSecond);
    return element.getAttribute(attribute);
}




}