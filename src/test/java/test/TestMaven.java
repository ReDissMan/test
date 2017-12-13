package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class TestMaven
{
    static final String PORT = "4502";

    static String titleTransmitt = "TITLE",
            titleReceive = "",

    hedingTransmitt = "Heading",
            headingReceive = "";

    static WebDriver webDriver = new FirefoxDriver();

    @org.testng.annotations.Test
    public static void checkTest() throws InterruptedException
    {
        //System.setProperty("webdriver.gecko.driver", "D:\\Мое\\Практика\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        webDriver.get("http://localhost:"+PORT+"/editor.html/content/we-retail/language-masters/en/about-us.html");

        webDriver.findElement(By.id("password")).sendKeys("admin");
        webDriver.findElement(By.id("username")).sendKeys("admin");

        webDriver.findElement(By.id("submit-button")).click();

        Thread.sleep(30000);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.id("OverlayWrapper")).click();

        Thread.sleep(1000);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath("//div[@id='EditableToolbar']/button[1]")).click();

        Thread.sleep(1000);
        webDriver.findElement(By.name("./heading")).clear();
        webDriver.findElement(By.name("./heading")).sendKeys(hedingTransmitt);

        webDriver.findElement(By.name("./title")).clear();
        webDriver.findElement(By.name("./title")).sendKeys(titleTransmitt);

        webDriver.findElement(By.xpath("//coral-dialog-header/div/button[4]")).click();

        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//button[@data-layer='Preview']")).click();

        Thread.sleep(10000);
        webDriver.switchTo().frame(0);
        titleReceive = webDriver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/div/strong")).getText();
        headingReceive = webDriver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/div/p")).getText();

        Assert.assertEquals(titleTransmitt, titleReceive);
        Assert.assertEquals(hedingTransmitt, headingReceive);
    }

    @org.testng.annotations.AfterMethod
    public static void afterTest() throws InterruptedException
    {
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath(".//*[@id='Content']/div[1]/coral-actionbar/coral-actionbar-secondary/coral-actionbar-item[1]/button")).click();
        Thread.sleep(5000);

        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.id("OverlayWrapper")).click();

        Thread.sleep(1000);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath("//div[@id='EditableToolbar']/button[1]")).click();

        Thread.sleep(1000);
        webDriver.findElement(By.name("./heading")).clear();
        webDriver.findElement(By.name("./title")).clear();
        webDriver.findElement(By.xpath("//coral-dialog-header/div/button[4]")).click();

        webDriver.close();
    }
}
