import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Random;


public class TestCase1 extends RandomValuesGenerator{

    WebDriver driver = new EdgeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public String generatenewDateTime(){
        String dat = new Date().toString().replaceAll(" ", "").replaceAll(  ":", "") + "@gmail.com";
        return dat;
    }

    @BeforeClass
    public void classSetup(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void Registration() {
        driver.get("https://tutorialsninja.com/demo/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a")).click();
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("input-firstname")).sendKeys("Umar");
        driver.findElement(By.id("input-lastname")).sendKeys("Hassan");
        driver.findElement(By.id("input-email")).sendKeys(generatenewDateTime());
        driver.findElement(By.id("input-telephone")).sendKeys("980921-88");
        driver.findElement(By.id("input-password")).sendKeys("Pokemon88");
        driver.findElement(By.id("input-confirm")).sendKeys("Pokemon88");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]")).click();
        String text = "Your Account Has Been !";
        Assert.assertNotEquals(driver.findElement(By.xpath("/html/body/div[2]/div/div/h1")).getText(), text);

        //Next Page
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/a")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
    }
        @Test(priority = 2)
        public void Subscription(){
        WebElement subscribe = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Subscribe / unsubscribe to newsletter")));
        subscribe.click();
        String title = "Newsletter Subscription";
        Assert.assertEquals(driver.findElement(By.cssSelector("#content h1")).getText(), title);
        WebElement permission = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/form/fieldset/div/div/label[2]/input")));
        permission.click();
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset/div/div/label[2]/input")).isSelected());
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div[2]/input")).click();
    }

    @Test(priority = 3)
    public void AddressBook() throws InterruptedException {
        driver.findElement(By.linkText("Address Book")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/a")).click();
        String addresspageTitle = "Add Address";
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div/div/h2")).getText(), addresspageTitle);
        driver.findElement(By.id("input-firstname")).sendKeys(randomFirstNameGenerator());
        //Assertion for Mandatory field "First Name"
        Assert.assertTrue(driver.findElement(By.id("input-firstname")).isEnabled());

        driver.findElement(By.id("input-lastname")).sendKeys(randomLastNameGenerator());
        //Assertion for Mandatory field "Last Name"
        Assert.assertTrue(driver.findElement(By.id("input-lastname")).isEnabled());

        driver.findElement(By.id("input-company")).sendKeys("Aqary International");

        driver.findElement(By.id("input-address-1")).sendKeys(RandomAddressGenerator());
        //Assertion for Mandatory field "Address"
        Assert.assertTrue(driver.findElement(By.id("input-address-1")).isEnabled());

        driver.findElement(By.id("input-address-2")).sendKeys(RandomAreaGenerator());
        driver.findElement(By.id("input-city")).sendKeys("Abu Dhabi");

        driver.findElement(By.name("postcode")).sendKeys("23243");

        //Dropdown Fields (Country and Region/State)
        WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[8]/div/select")));
        Select dropdowns = new Select(country);
        dropdowns.selectByIndex(3);
        WebElement regionstate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[9]/div/select")));
        Select region = new Select(regionstate);
        region.selectByVisibleText("Delvine");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[2]/input")).click();

    }
    @Test(priority = 4)
    public void addressIteration() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/table/tbody/tr/td[2]/a[1]")).click();
        for(int i = 0;i<3;i++){
            AddressBook();
    }}

    @Test(priority = 5)
    public void AddtoCart()   throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement Desktop = driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/a"));
        actions.moveToElement(Desktop).perform();
        WebElement macselect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/div/div/ul/li[2]/a")));
        macselect.click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[2]/div[2]/button[2]")).click();
        //driver.wait(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[2]/div[2]/button[1]")).click();

        Actions act = new Actions(driver);
        WebElement itemcarts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/button")));
        itemcarts.click();
        //act.moveToElement(itemcarts).perform();

        //Failed Test Case Step
        WebElement cartpopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[2]/strong")));
        cartpopup.click();
        String text = "Checkout Successfull";
        Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]"))).getText(), text);


        //Checkout click


    }
    /*@AfterClass
    public void quitWindow(){
        driver.quit();
    }*/

}
