package ohtu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {
    App app;
    StubIO io;
    UserDao userDao = new InMemoryUserDao();
    AuthenticationService auth = new AuthenticationService(userDao);
    List<String> inputLines = new ArrayList<>();
    
    @Given("^command login is selected$")
    public void command_login_selected() throws Throwable {
        inputLines.add("login");
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are entered$")
    public void a_username_and_password_are_entered(String username, String password) throws Throwable {
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }
    
    
    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        inputLines.add("new");
    }
    
       @When("^valid username  \"([^\"]*)\"  and password \"([^\"]*)\" are created$")
    public void valid_username_and_password_are_created(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

       inputLines.add(arg1);
       inputLines.add(arg2);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
     }   
    
    @Then("^system will respond  with \"([^\"]*)\"$")
    public void a_new_user_is_registered(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }



    @When("^username already taken \"([^\"]*)\" and valid password \"([^\"]*)\" are entered$")
    public void username_already_taken_and_valid_password_are_entered(String arg1, String arg2) throws Throwable {
       inputLines.add(arg1);
       inputLines.add(arg2);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
 

    @When("^username \"([^\"]*)\" and valid password \"([^\"]*)\" are entered$")
    public void username_and_valid_password_are_entered(String arg1, String arg2) throws Throwable {
      inputLines.add(arg1);
       inputLines.add(arg2);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
 

    @When("^username \"([^\"]*)\" and too short password \"([^\"]*)\" are entered$")
    public void username_and_too_short_password_are_entered(String arg1, String arg2) throws Throwable {
      inputLines.add(arg1);
       inputLines.add(arg2);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }

    @When("^username \"([^\"]*)\" and  \"([^\"]*)\" are entered$")
    public void username_and_are_entered(String arg1, String arg2) throws Throwable {
       inputLines.add(arg1);
       inputLines.add(arg2);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    

    
    @Given("^user \"([^\"]*)\" with password \"([^\"]*)\" is created$")
    public void user_with_password_is_created(String arg1, String arg2) throws Throwable {
       inputLines.add("new");
       inputLines.add(arg1);
       inputLines.add(arg2);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    

    @When("^user \"([^\"]*)\" and  password \"([^\"]*)\" are entered$")
    public void user_and_password_are_entered(String arg1, String arg2) throws Throwable {
       inputLines.add(arg1);
       inputLines.add(arg2);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();       
}
    
     @Then("^system will respond withb \"([^\"]*)\"$")
    public void system_will_respond_withb(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }
}
