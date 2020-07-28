import controller.AuthenticationController;
import model.repository.AuthenticationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;

public class AuthenticationTest {

    @Test
    public void weakPasswordSignUp(){
        AuthenticationController authenticationController = new AuthenticationController();
        String pass = "james13";
        try{
            authenticationController.passwordValidation(pass);
            fail("Password is valid, there's a logic error");
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("Password is not valid",e.getMessage());
        }
    }

    @Test
    public void failLoginEmptyPassword(){
        AuthenticationController authenticationController = new AuthenticationController();
        Assertions.assertFalse(authenticationController.login("aaaddd", ""));
    }

    @Test
    public void failLoginEmptyUser(){
        AuthenticationController authenticationController = new AuthenticationController();
        Assertions.assertFalse(authenticationController.login("", "gogo1234"));
    }

    @Test
    public void loginTest() {
        AuthenticationController authenticationController = new AuthenticationController();
        Assertions.assertTrue(authenticationController.login("asdf", "asdf"));
    }

    @Test
    public void birthdayNotValid(){
        AuthenticationController authenticationController = new AuthenticationController();
        if (!authenticationController.isDate("1/1/1990")) {
            System.out.println( "Date is not valid,should match the pattern dd/mm/yyyy");
        }
        Assertions.assertFalse(authenticationController.isDate("1/1/1990"));
    }
    @Test
    public void birthdayIsValid(){
        AuthenticationController authenticationController = new AuthenticationController();
        Assertions.assertTrue(authenticationController.isDate("01/01/1993"));
    }

}
