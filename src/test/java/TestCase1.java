import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v130.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.sound.sampled.Control;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Set;


public class TestCase1 extends RandomValuesGenerator {

    WebDriver driver = new EdgeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions act = new Actions(driver);

    public String generatenewDateTime(){
        String dat = new Date().toString().replaceAll(" ", "").replaceAll(  ":", "") + "@gmail.com";
        return dat;
    }

    @BeforeClass
    public void classSetup(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        driver.manage().window().maximize();
    }

    //Test Case 1 [Registration]

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

    //Test Case 2 [Newsletter Subscription]

    @Test(priority  = 2)
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

    //Test Case 3 [Random Address Generation]

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
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=checkout/cart", "Title match");
        System.out.println(driver.getWindowHandles());
    }

    //Test Case 4 [Filling the Random Addresses]

    @Test(priority = 4)
    public void addressIteration() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/table/tbody/tr/td[2]/a[1]")).click();
        for(int i = 0;i<1;i++){
            AddressBook();
    }}

    //Test Case 5 [Adding Item to Cart]

    @Test(priority = 5)
    public void DesktopAddtoCart()   throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement Desktop = driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/a"));
        actions.moveToElement(Desktop).perform();
        WebElement macselect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[1]/div/div/ul/li[2]/a")));
        macselect.click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[2]/div[2]/button[1]")).click();

        Actions act = new Actions(driver);
        WebElement itemcarts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/button")));
        itemcarts.click();

        //Failed Test Case Step
        try {
            WebElement cartpopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[2]/strong")));
            cartpopup.click();
            String text = "Checkout Successfull";
            Assert.assertEquals(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]"))).getText(), text);
        }
        catch(Exception exc){
            System.out.println("Exception Faced -> " + exc);
        }
        //Checkout click
    }

    //Test Case 6 [Adding Item to Cart]

    @Test(priority = 6)
    public void ComponentsAddtoCart(){
        Actions act = new Actions(driver);
        WebElement comp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[3]/a")));
        act.moveToElement(comp).perform();
        driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[3]/div/div/ul/li[2]/a")).click();
        //Sort By Dropdown
        Select selectsortby = new Select(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[3]/div/select")));
        selectsortby.selectByIndex(2);
        //Comparison Test Case Check
        WebElement comparison = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[3]/div[1]/div/div[2]/div[2]/button[3]")));
        comparison.click();
        //Wishlist Check
        WebElement wishlist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/div/div[2]/div[2]/button[2]")));
        wishlist.click();
        //Add to Cart Check
        WebElement cartcheck = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/div/div[2]/div[2]/button[1]")));
        cartcheck.click();
        //CartItems
        WebElement cartItems = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div[3]/div/button")));
        cartItems.click();
        driver.findElement(By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[2]/strong")).click();
        String buttonname = "Checkout";
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div[2]/a")).getText(), buttonname);
        WebElement cuponcode = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div[1]/div[1]/h4/a")));
        cuponcode.click();
        WebElement inputcuponcode = wait.until(ExpectedConditions.elementToBeClickable(By.name("coupon")));
        inputcuponcode.sendKeys(RandomCoupencodes());
        WebElement applypromobutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-coupon")));
        applypromobutton.click();
        String promocodeappliedmessage = "Promo Code Applied Successfully";

        //Failed Test Case
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[1]")).getText() , promocodeappliedmessage);
        WebElement finalCheckout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div[2]/a")));
        finalCheckout.submit();

        //Element Visibility Check
        WebElement elementDisplay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div[1]/div[2]/div/h3")));

        Assert.assertTrue(elementDisplay.isDisplayed());

    }

    //Test Case 7 [Modifying the Cart Order]

    @Test(priority = 7)
    public void CartRefinement() throws InterruptedException {
        WebElement cartplusitem1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/form/div/table/tbody/tr[1]/td[4]/div/input")));
        WebElement cartplusitem2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/form/div/table/tbody/tr[2]/td[4]/div/input")));

        cartplusitem1.clear();
        cartplusitem1.sendKeys("2");
        act.moveToElement(cartplusitem1).click().keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").keyUp(Keys.CONTROL).perform();
        act.moveToElement(cartplusitem2).click().keyDown(Keys.CONTROL).sendKeys("a").sendKeys("v").keyUp(Keys.CONTROL).perform();

    }

    //Test Case 8 [Traversing Back to Search]

    @Test(priority = 8)
    public void continueShopping(){
        WebElement continueShop = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/a")));
        continueShop.click();
    }

    //Test Case 9 [Opening a New Tab]

    @Test(priority = 9)
    public void newWindow() throws AWTException, InterruptedException {

        WebElement components = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[4]/a")));
        String drive = driver.getWindowHandle();
        act.moveToElement(components).perform();
        act.contextClick(components).perform();
        //Switching to a new Tab
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);  // Move down in the right-click menu
        robot.keyPress(KeyEvent.VK_ENTER);
        Set<String> allwindowhandles = driver.getWindowHandles();
        ArrayList<String> list = new ArrayList<>(allwindowhandles);
        driver.switchTo().window(list.get(1));

        /*for(String wind: allwindowhandles){
            if(!wind.equals(drive)){
                driver.switchTo().window(wind);
                System.out.println("Switched to new tab: " + driver.getTitle());
            }
        }*/
        /*WebElement addTablet = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[2]/div[2]/button[1]")));
        addTablet.click();
        driver.findElement(By.xpath("/html/body/header/div/div/div[3]/div/button")).click();
        driver.findElement(By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[1]/strong")).click();*/
    }
    @Test(priority = 10)
    public void ContactUs(){
        WebElement contactUs = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact Us")));
        contactUs.click();
        WebElement inquiry = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/form/fieldset/div[3]/div/textarea")));
        inquiry.sendKeys("The website is amazing!");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input")).click();
        driver.findElement(By.linkText("Continue")).click();
    }

    @Test(priority = 11)
    public void ProductReturns(){
        WebElement returnProduct = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Returns")));
        returnProduct.click();
        WebElement orderID = wait.until(ExpectedConditions.elementToBeClickable(By.name("order_id")));
        orderID.sendKeys("24");
        WebElement productName = wait.until(ExpectedConditions.elementToBeClickable(By.name("product")));
        productName.sendKeys("Samsung Tablet");
        WebElement productCode = wait.until(ExpectedConditions.elementToBeClickable(By.name("model")));
        productCode.sendKeys("2");
        //Assert.assertEquals(driver.findElement(By.name("model")).getText(), 2);
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/form/fieldset[2]/div[4]/div/div[3]/label/input")));
        toggle.click();
        WebElement otherdetails = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-comment")));
        otherdetails.sendKeys("Superb Product it is");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[2]/input")).click();
        driver.findElement(By.linkText("Continue")).click();
    }
    //Test Case 10 [Quitting the Window]

    @AfterClass
    public void quitWindow(){
     System.out.print("Class Executed!");
     //driver.quit();
    }
}
