package view;

import com.sun.xml.internal.bind.v2.TODO;
import controller.AuthenticationController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SignUpFormView {

    private AuthenticationController authController;
    private SababaFlightsProgram sababaFlightsProgram;

    public SignUpFormView(){
        this.authController = new AuthenticationController();
    }

    public void signUp() {
        long id;
        boolean validFlag;
        String username;
        String password;
        boolean passwordRequirements = true;
        String email;
        String birthDateStr;
        String firstName;
        String lastName;
        String idStr;
        LocalDate birthDate = null;
        DateTimeFormatter formatter ;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Sign up page!"+"\n");

        //Username input and validation
        do{
            System.out.println("User name: ");
            username = scanner.nextLine();
            validFlag = this.authController.usernameValidation(username);
        }    while(!validFlag) ;

//        //Password input and validation
//        do{
//            System.out.println("Password: ");
//            password = scanner.nextLine();
//            validFlag = this.authController.passwordValidation(password);
//        }    while(!validFlag) ;

        //password validation
        do{
            System.out.println("Password:");
            if(passwordRequirements){
                System.out.println("(Password must consist at least one english letter, one digit and length of 8-32 characters)");
                passwordRequirements = false;
            }
            password = scanner.nextLine();
            try {
                validFlag = this.authController.passwordValidation(password);
            }catch (IllegalArgumentException e){
                validFlag = false;
                System.out.println(e.getMessage()+", please try again");
            }

        } while(!validFlag);

        //Email input and validation
        do{
            System.out.println("Email: ");
            email = scanner.nextLine();
            validFlag = this.authController.isValidEmail(email);
        }    while(!validFlag);

        //Date of birth input and validation
        do {
            validFlag=true;
            System.out.println("Date of birth in dd/MM/yyyy format: ");
            birthDateStr = scanner.nextLine();
            try {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                formatter.parse(birthDateStr);
                birthDate = LocalDate.parse(birthDateStr, formatter);
            } catch (Exception e) {
                validFlag=false;
                System.out.println("Invalid date of birth format!" +"\n");
            }
        }while(!validFlag);

        //Agent is below 18 years old
        if(!authController.minimumAge(birthDate))
        {
            sababaFlightsProgram=new SababaFlightsProgram();
            System.out.println("Returning..."+"\n");
            sababaFlightsProgram.loginScreen();
        }

        //Name inputs and validations
        do {
            System.out.println("First name:");
             firstName = scanner.nextLine();
             validFlag=authController.validName(firstName);
             if(!validFlag)
                 System.out.println("Invalid first name. Please try again");
            }while(!validFlag);

        do {
            System.out.println("Last name:");
            lastName = scanner.nextLine();
            validFlag=authController.validName(lastName);
            if(!validFlag)
                System.out.println("Invalid last name. Please try again");
        }while(!validFlag);

        //ID input and validation
        do {
            System.out.println("ID: ");
            idStr = scanner.nextLine();
            validFlag=authController.idValidation(idStr);
           }while(!validFlag);
          id = Long.parseLong(idStr);

        boolean signUpFlag = this.authController.agentSignUp(firstName, lastName, id, email, birthDateStr, false, username, password);
        if(signUpFlag){
            System.out.println("\n"+"Sign up successfully!"+"\n");
        }
    }
}
