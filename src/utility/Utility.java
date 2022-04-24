package utility;

import browsertesting.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {
    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    /* public String verifyPage(String actualMessage,By by,String message){
 return message;
     }
     */
    public void selectMenu(String menu) {
        if (menu == "Computers") {
            WebElement selectedElement = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
            selectedElement.click();
            String expectedResult = "Computers";
            WebElement msg = driver.findElement(By.xpath("//h1[text()='Computers']"));
            String actualResult = msg.getText();
            Assert.assertEquals("not in computer page", expectedResult, actualResult);
        } else if (menu == "Electronics") {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']")).click();
            String expectedResult = "Electronics";
            WebElement msg = driver.findElement(By.xpath("//h1[text()='Electronics']"));
            String actualResult = msg.getText();
            Assert.assertEquals("not in Electronics page", expectedResult, actualResult);
        } else if (menu.equals("Apparel")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Apparel']")).click();
            String expectedResult = "Apparel";
            WebElement msg = driver.findElement(By.xpath("//h1[text()='Apparel']"));
            String actualResult = msg.getText();
            Assert.assertEquals("not in Apparel page", expectedResult, actualResult);
        } else if (menu.equals("Digital downloads")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Digital downloads']")).click();
            String expectedResult = "Digital downloads";
            WebElement msg = driver.findElement(By.xpath("//h1[text()='Digital downloads']"));
            String actualResult = msg.getText();
            Assert.assertEquals("not in Electronics page", expectedResult, actualResult);
        } else if (menu.equals("Books")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Books']")).click();
            String expectedResult = "Books";
            WebElement msg = driver.findElement(By.xpath("//h1[text()='Books']"));
            String actualResult = msg.getText();
            Assert.assertEquals("not in Books page", expectedResult, actualResult);
        } else if (menu == "Jewelry") {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Jewelry']")).click();
            String expectedResult = "Jewelry";
            WebElement msg = driver.findElement(By.xpath("//h1[text()='Jewelry']"));
            String actualResult = msg.getText();
            Assert.assertEquals("not in Jewelry page", expectedResult, actualResult);
        } else if (menu == "Gift Cards") {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Gift Cards']")).click();
            String expectedResult = "Gift Cards";
            WebElement msg = driver.findElement(By.xpath("//h1[text()='Gift Cards']"));
            String actualResult = msg.getText();
            Assert.assertEquals("not in Gift Cards page", expectedResult, actualResult);
        }
    }
    public String verifyPage(String expectedResult,By by,String message){
        WebElement compare = driver.findElement(by);
        String actualResult = compare.getText();
        Assert.assertEquals(message, expectedResult, actualResult);
        return message;
    }
    public void selectFromDropDown(By by,String value){
        WebElement dropDown1=driver.findElement(by);
        dropDown1.click();
        Select select1=new Select(dropDown1);
        select1.selectByValue(value);
    }
    public String getFromMessage(String expectedResult, By by, String message){
        WebElement compare1 =driver.findElement(by);
        String actualResult = compare1.getText();
        Assert.assertEquals(message, expectedResult,actualResult);
        return message;

    }
    public void sendTextToElement(By by,String text) {
        driver.findElement(by).sendKeys(text);
    }
    public void clearValue(By by) {
        driver.findElement(by).clear();
    }
    public WebElement mouseHover(By by) {
        Actions actions = new Actions(driver);
        WebElement move = driver.findElement(by);
        actions.moveToElement(move).perform();
        return move;
    }
    public String getUrl() {
        String actualUrl = driver.getCurrentUrl();
        return actualUrl;
    }


}