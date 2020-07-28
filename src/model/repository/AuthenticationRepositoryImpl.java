package model.repository;

import model.objects.Agent;
import model.singletons.AgentSingleton;
import model.singletons.LoginSingleton;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    AgentSingleton agentSingleton;
    Set<Agent> agents;


    @Override
    public boolean userNameLogin(String userName, String password) {
        AgentRepositoryImpl agentRepository = new AgentRepositoryImpl();
        Set<Agent> agents = AgentSingleton.getInstance().agentSet;

        boolean foundUserName = false;
        for (Agent agent : agents) {
            if (agent.getUserName().equals(userName)) {
                foundUserName = true;
                if (agent.getPassword().equals(agentRepository.encryptPassword(password))) {
                    LoginSingleton.getInstance().logIn(agent);
                    System.out.println("Logged in successfully :)");
                    return true;
                } else {
                    System.out.println("Wrong password, please try again.");
                    break;
                }
            }
        }
        if (!foundUserName)
            System.out.println("Username does not exist in the system");
        return false;
    }

    @Override
    public void logOut() {
        LoginSingleton.getInstance().logOut();
        System.out.println("Logged out successfully");
    }

    @Override
    public boolean isLoggedin() {
        return LoginSingleton.getInstance().isLoggedIn();
    }

    @Override
    public boolean signUp(String firstName, String lastName, long id, String email, String birthDate,
                          boolean enabled, String userName, String password) {
            Agent newAgent = new Agent(id, firstName, lastName, email, userName, password, 1, birthDate);
            newAgent.setEncryptedPassword(password);
            agents.add(newAgent);
            agentSingleton.saveSet(agents);
            return true;
    }

    public boolean emailExists(String email) {
        //JSON parser object to parse read file
        agentSingleton = AgentSingleton.getInstance();
        agents = agentSingleton.agentSet;

        for (Agent agent : agents) {
            if (agent.getEmail().equals(email)) {
                System.out.println("\nEmail already exists!");
                return true;
            }
        }
        return false;
    }

    public boolean userExist(String username) {
        //JSON parser object to parse read file
        agentSingleton = AgentSingleton.getInstance();
        agents = agentSingleton.agentSet;

        for (Agent agent : agents) {
            if (agent.getUserName().equals(username)) {
                System.out.println("\nUser name already exists!");
                return true;
            }
        }
        return false;
    }
}
