package model.repository;

import model.objects.Agent;
import model.singletons.AgentSingleton;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Set;

//import model.objects.Order;

public class AgentRepositoryImpl implements AgentRepository {

    private Set<Agent> agentSet;

    public AgentRepositoryImpl() {
        this.agentSet = AgentSingleton.getInstance().agentSet;
    }

    @Override
    public String encryptPassword(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(password.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            StringBuilder hashtext = new StringBuilder(bigInt.toString(16));
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashtext.length() < 32 ){
                hashtext.insert(0, "0" ).append(hashtext);
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    @Override
    public Agent getAgentById(long id) {
        for (Agent agent : this.agentSet) {
            if (agent.getId() == id) {
                return agent;
            }
        }
        System.out.println("Could not find an agent with this id");
        return null;
    }

    public  boolean removeAgent(long id)
    {
        if(getAgentById(id).getPermissionLevel()==10)
        {
            System.out.println("Can not remove an admin agent!");
            return false;
        }
        if(getAgentById(id) ==null)
        {
            System.out.println("Agent does not exist!");
            return false;
        }
        else
        {
            AgentSingleton.getInstance().agentSet.remove(getAgentById(id));
            AgentSingleton.getInstance().saveSet(agentSet);
            return true;
        }
    }

    public  boolean changePermissionLevel(long id)
    {
        int level;
        Agent agent =getAgentById(id);
        if(agent.getPermissionLevel()==10){
            System.out.println("Can not change admin's permission level");
            return false;
        }
        if(agent==null)
        {
            System.out.println("Agent does not exist!");
            return false;
        }
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("Please enter the permission level, must be in 1-10 range");
            level = scanner.nextInt();
        }while(level<1 || level>10);

        agent.setPermissionLevel(level);
        AgentSingleton.getInstance().agentSet.remove(getAgentById(id));
        agentSet.add(agent);
        AgentSingleton.getInstance().saveSet(agentSet);
        return true;
    }

   public boolean changeEmailAddress(long id,String email){
       Agent agent =getAgentById(id);
       if(agent.getPermissionLevel()==10){
           System.out.println("Can not change admin's email address");
           return false;
       }
       if(agent==null)
       {
           System.out.println("Agent does not exist!");
           return false;
       }
       else
       {
           agent.setEmail(email);
           AgentSingleton.getInstance().agentSet.remove(getAgentById(id));
           agentSet.add(agent);
           AgentSingleton.getInstance().saveSet(agentSet);
       }
       return true;
    }
}
