package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.partialLinkText("user"));
        element.click();
    }
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndMatchingPasswordsAreGiven(String username, String password) {
        createUser(username, password);
    }
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("a new user is created")
    public void userIsCreated() {
        pageHasContent("Welcome");
    }
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Given("user with username {string} with password {string} is successfully created")
    public void userIsCreatedCorrectly(String username, String password) {
        newUserIsSelected();
        createUser(username, password);
        logoutAfterCreation();
    }
    
    @Given("user with username {string} and password {string} is tried to be created")
    public void userIsCreatedIncorrectly(String username, String password) {
        newUserIsSelected();
        createUser(username, password);
    }
    
    @When("a valid username {string}, password {string} and password confirmation {string} are entered")
    public void validUserButMismatchedPasswords(String user, String password, String confirmation) {
        createUser(user, password, confirmation);
    }
    
    @When("a too short username {string} and password {string} and matching password confirmation are entered")
    public void tooShortUsername(String username, String password) {
        createUser(username, password);
    }
    
    @Then("user is not created and error {string} is reported")
    public void userNotCreatedAndError(String error) {
        pageHasContent("Create username and give password");
        pageHasContent(error);
    }
    
    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPassworAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @When("a valid username {string} and too short password {string} and matching password confirmation are entered")
    public void validUsernameTooShortPassword(String username, String password) {
        createUser(username, password);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
    
    private void createUser(String user, String pass, String confirmation) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        element = driver.findElement(By.name("password"));
        element.sendKeys(pass);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);

        element = driver.findElement(By.name("signup"));

        element.submit();
    }
    
    private void createUser(String user, String pass) {
        createUser(user, pass, pass);
 
    }
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void logoutAfterCreation() {
        WebElement element = driver.findElement(By.partialLinkText("continue"));
        element.click();
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
}
