import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Random;


public class TestCase1 {

    WebDriver driver = new EdgeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public String generatenewDateTime(){
        String dat = new Date().toString().replaceAll(" ", "").replaceAll(  ":", "") + "@gmail.com";
        return dat;
    }

    @Test(priority = 1)
    public void Registration() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
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
        driver.findElement(By.id("input-firstname")).sendKeys("Umar");
        //Assertion for Mandatory field "First Name"
        Assert.assertTrue(driver.findElement(By.id("input-firstname")).isEnabled());

        driver.findElement(By.id("input-lastname")).sendKeys("Hassan");
        //Assertion for Mandatory field "Last Name"
        Assert.assertTrue(driver.findElement(By.id("input-lastname")).isEnabled());

        driver.findElement(By.id("input-company")).sendKeys("Aqary International");

        driver.findElement(By.id("input-address-1")).sendKeys(arrayforaddress());
        //Assertion for Mandatory field "Address"
        Assert.assertTrue(driver.findElement(By.id("input-address-1")).isEnabled());

        driver.findElement(By.id("input-address-2")).sendKeys("AL Maryah Island Abu Dhabi");
        driver.findElement(By.id("input-city")).sendKeys("Abu Dhabi");

        driver.findElement(By.name("postcode")).sendKeys("5398");

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
    public void editAddress() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/table/tbody/tr/td[2]/a[1]")).click();
        for(int i = 0;i<3;i++){
            AddressBook();
    }}

    public String arrayforaddress(){
        String[] arr = {"Aqary", "FineHome", "Gravity", "Huawei", "Sunpo", "Wemart", "Greenhouse"};
        Random x = new Random();
        int randomIndex = x.nextInt(arr.length);

        // Return and print the random string
        String randomString = arr[randomIndex];
        return randomString;

    }
    /*public String FirstLastName(){
        String[] arrfirstname = {"Saad", "Umar", "Harry", "David", "Ali", "Lewis"};
        String[] arrlastname = {"Kevin", "Thomas", "Deeb", "Shelby", "Ali", "Lewis"};

    }*/

}
