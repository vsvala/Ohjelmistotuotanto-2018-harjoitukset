package ohtu;


import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();  // ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    } 
       @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
    }
        @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
        public void user_with_username_with_password_is_successfully_created(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        command_new_user_is_selected();
        createWith(username, password, password);
    }
        @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
        public void user_with_username_and_password_is_tried_to_be_created(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        command_new_user_is_selected();
        createWith(username, password, password);
        WebElement element = driver.findElement(By.linkText("back to home"));    
        element.click(); 
    }
  
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }


    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    
    @When("^nonexistent username \"([^\"]*)\" and correct password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_correct_password_are_given(String username, String password) throws Throwable {
           logInWith(username, password);
    }


    @When("^When  a valid username \"([^\"]*)\" and password \"([^\"]*)\" and notmatching password confirmation \"([^\"]*)\" are entered$")
    public void when_a_valid_username_and_password_and_notmatching_password_confirmation_are_entered(String arg1, String arg2, String arg3) throws Throwable {
         createWith(arg1, arg2, arg3);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation \"([^\"]*)\" are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String arg1, String arg2, String arg3) throws Throwable {
     createWith(arg1, arg2, arg3);
    }

    @When("^a too short username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation\"([^\"]*)\" are entered$")
    public void a_too_short_username_and_password_and_matching_password_confirmation_are_entered(String arg1, String arg2, String arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       createWith(arg1, arg2, arg3);
    }
    
        @When("^uncorrect username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
        public void uncorrect_username_and_password_are_given(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
         createWith(arg1, arg2, arg2);
    }

    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String content) throws Throwable {
    pageHasContent(content);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
    pageHasContent("Ohtu Application main page");
    }
    
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    @Then("^a new user is created$")
    public void a_new_user_is_created()  throws Throwable {
    pageHasContent("Welcome to Ohtu Application!");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
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
       private void createWith(String username, String password, String con) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(con);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    } 
}
