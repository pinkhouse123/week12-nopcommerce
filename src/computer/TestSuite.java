package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import sun.management.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTable;
import utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {

    @Before
    public void setUp() {
        openBrowser();
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        clickOnElement(By.linkText("Desktop"));
        WebElement dropDown = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        dropDown.click();
        Select select = new Select(dropDown);
        select.selectByValue("6");
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='products-wrapper']//descendant::div[@class='item-box']"));
        List<String> names = new ArrayList<String>();
        for (WebElement e : products) {
            names.add(e.getText());
        }
        List<String> sortedNames = new ArrayList<String>(names);
        Collections.sort(sortedNames);
        System.out.println(sortedNames.equals(names));
        Assert.assertEquals("Sorting product names from Z to A is not working", names, sortedNames);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //clickOnElement(By.xpath("//a[text()='Computers' and @xpath='1']/parent::li"));

        //Click on Computer Menu.
        clickOnElement(By.linkText("Computers"));

        //Click on Desktop
        clickOnElement(By.linkText("Desktops"));

        //Select Sort By position "Name: A to Z"
        selectFromDropDown(By.xpath("//select[@id=\"products-orderby\"]"), "5");

        //Click on "Add To Cart"
        clickOnElement(By.xpath("//button[contains(@onclick,'return AjaxCart.addproducttocart_catalog(\"/addproducttocart/catalog/1/1/1\"),!1')]"));

        //Verify the Text "Build your own computer"
        String expectedResult = "Build your own computer";
        verifyPage(expectedResult, By.xpath("//h1[text()='Build your own computer']"), "User is not in correct page");

        //Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectFromDropDown(By.id("product_attribute_1"), "1");

        //Select "8GB [+$60.00]" using Select class.
        selectFromDropDown(By.xpath("//select[@id=\"product_attribute_2\"]"), "5");

        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));

        //Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));

        //Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        clickOnElement(By.xpath("//input[@id=\"product_attribute_5_12\"]"));

        //Verify the price "$1,475.00"
        String expectedResult1 = "$1,475.00";
        verifyPage(expectedResult1, By.xpath("//span[text()='$1,475.00']"), "data not matched");

        //Click on "ADD TO CARD" Button.
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedMessage2 = "The product has been added to your shopping cart";
        verifyPage(expectedMessage2, By.xpath("//p[@class='content']"), "data not matched");

        //Close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class='close']"));

        //Then MouseHover on "Shopping cart";
        Actions actions = new Actions(driver);
        WebElement mouseHover = driver.findElement(By.xpath("//span[@class='cart-label']"));
        actions.moveToElement(mouseHover).perform();

        //Click on "GO TO CART" button.
        driver.findElement(By.xpath("//button[@class='button-1 cart-button']")).click();

        //Verify the message "Shopping cart"
        String expectedMessage3 = "Shopping cart";
        verifyPage(expectedMessage3, By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart not match");

        //Change the Qty to "2"
        actions.doubleClick(driver.findElement(By.xpath("//input[@class='qty-input']"))).perform();
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");

        //Click on "Update shopping cart"
        clickOnElement(By.xpath("//button[contains(.,'Update shopping cart')]"));

        //Verify the Total"$2,950.00"
        String expectedMessage4 = "$2,950.00";
        verifyPage(expectedMessage4, By.xpath("//tr[@class='order-total']//td[@class='cart-total-right']"), "Price not Match");

        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id=\"termsofservice\"]"));

        //Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));
        Thread.sleep(2000);

        //Verify the Text “Welcome, Please Sign In!”
        String expectedMessage5 = "Welcome, Please Sign In!";
        verifyPage(expectedMessage5, By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "Sign in not match");

        //Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[@class='button-1 checkout-as-guest-button']"));

        //Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Umi");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Patel");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "umipatel@gmail.com");
        selectFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "123");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "10 Queen Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "H104AR");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07547852563");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));

        //Select “Master card” From Select credit card dropdown
        selectFromDropDown(By.xpath("//select[@id='CreditCardType']"), "MasterCard");

        //Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Umi Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5123890024351234");
        selectFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "14");
        selectFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2025");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "817");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //Verify “Payment Method” is “Credit Card”
        String expectedMessage6 = "Credit Card";
        verifyPage(expectedMessage6, By.xpath("//span[contains(text(),'Credit Card')]"), "Payment is not Credit Card");

        //Verify “Shipping Method” is “Next Day Air”
       /* String expectedMessage7= "Next Day Air";
        verifyPage(expectedMessage7,By.xpath("//input[@id='shippingoption_1']"),"Shipping is not Match");
        Thread.sleep(500);*/
        //label[normalize-s
        //Verify Total is “$2,950.00”
        String expectedMessage8 = "$2,950.00";
        verifyPage(expectedMessage8, By.xpath("//tbody/tr[4]/td[2]/span[1]/strong"), "Total Price is not Match");

        //Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //Verify the Text “Thank You”
        String expectedMessage9 = "Thank you";
        verifyPage(expectedMessage9, By.xpath("//h1[contains(text(),'Thank you')]"), "Message is not Match");

        //Verify the message “Your order has been successfully processed!”
        String expectedMessage10 = "Your order has been successfully processed!";
        verifyPage(expectedMessage10, By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Thankful message is not Match");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //Verify the text “Welcome to our store”
        String expectedMessage11 = "Welcome to our store";
        verifyPage(expectedMessage11, By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Promising message appear not Sucessfully");

    }

    @After
    public void tearUp() {
        closeBrowser();
    }

}
