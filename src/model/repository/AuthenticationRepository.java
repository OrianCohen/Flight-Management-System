package model.repository;

/*Login,Signup*/
public interface AuthenticationRepository {

    boolean userNameLogin(String userName, String password);
    void logOut();
    boolean isLoggedin();
    boolean signUp(String firstName, String lastName, long id, String email, String birthDate, boolean enabled , String userName, String password);
    boolean emailExists(String email);
    boolean userExist(String username);
}
