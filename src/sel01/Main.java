package sel01;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;
//        Buttons (additional task: click by position)
//        Wait to Disappear
//        Wait for Text Change
//        Radio Buttons
//        Checkboxes
//        * You should create separate method for each test.
public class Main {
    public static void main(String[] args) throws InterruptedException  {
        System.setProperty("webdriver.chrome.driver", "/Users/admin/Drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // or start-fullscreen for mac
        driver.get("http://www.leafground.com/pages/Window.html");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#contentblock > section > div:nth-child(4) > div > div > button")).click();
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> allHandles =driver.getWindowHandles();
        Iterator<String> iter= allHandles.iterator();
        String expectedTitleInteractWithButtons = "TestLeaf - Interact with Buttons";
        while(iter.hasNext()) {
            String child_window = iter.next();
            if (!parentWindowHandle.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
            } if (expectedTitleInteractWithButtons.equals(driver.switchTo().window(child_window).getTitle()) ){
                driver.switchTo().window(child_window);
                driver.manage().window().maximize();
                Thread.sleep(2000);
                WebElement h1Bond = driver.switchTo().window(child_window).findElement(By.tagName("h1"));
                System.out.println("h1 of the page is: " + h1Bond.getText());
            }
        }
        driver.switchTo().window(parentWindowHandle);
        Thread.sleep(2000);
//        Actions action = new Actions(driver);
//        action.keyDown(Keys.CONTROL).perform();
//        driver.findElement(By.xpath("ELEMENT")).click();
        driver.quit();
    }
}
