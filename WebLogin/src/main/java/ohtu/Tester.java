package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        
        
        WebElement element = driver.findElement(By.linkText("login"));
        
//        login(element, driver, "pekka", "akkep");
//        login(element, driver, "pekka", "aa");        
        Random r = new Random();
        createUser(element, driver, "arto"+r.nextInt(100000), "hajaksajdakj1");
        sleep(2);
        
        logoutAfterCreation(element, driver);
        
        sleep(2);
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static void login(WebElement element, WebDriver driver, String user, String pass) {
        element = driver.findElement(By.linkText("login"));

        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        element = driver.findElement(By.name("password"));
        element.sendKeys(pass);
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();
    }
    
    private static void logoutAfterCreation(WebElement element, WebDriver driver) {
        element = driver.findElement(By.partialLinkText("continue"));
        element.click();
        
        sleep(1);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();

    }
    
    private static void createUser(WebElement element, WebDriver driver, String user, String pass) {
        element = driver.findElement(By.partialLinkText("user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        element = driver.findElement(By.name("password"));
        element.sendKeys(pass);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pass);
        
        element = driver.findElement(By.name("signup"));
        sleep(2);
        
        element.submit();
    }
}
