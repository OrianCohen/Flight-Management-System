package model.objects;

import model.repository.AgentRepository;
import model.repository.AgentRepositoryImpl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Agent extends Person {

    private String userName, password;
    static int agentsCount;
    private long agentCode;
    private boolean isVerified;
    private int permissionLevel;

    static { agentsCount = 0; }

    public Agent() {
        super();
        agentsCount++;
    }

    public Agent(long id, String firstName, String lastName, String email, String userName, String password, int agentCode, String birthDateStr) {
        super(firstName, lastName, email, id, birthDateStr);
        this.id = agentsCount++;
        this.userName = userName;
        this.setPassword(password);
        this.agentCode = agentCode;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) { this.password = password; }
    public void setEncryptedPassword(String password) {
        AgentRepositoryImpl agentRepository = new AgentRepositoryImpl();
        this.password = agentRepository.encryptPassword(password);
    }
    public void setAgentCode(int agentCode) {
        this.agentCode = agentCode;
    }
    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
    public void setPermissionLevel(int permissionLevel) { this.permissionLevel = permissionLevel; }
    public void setVerified(boolean verified) {  isVerified = verified;}

    // Getters
    public String getUserName() { return this.userName; }
    public long getAgentCode() { return this.agentCode; }
    public boolean isVerified() { return this.isVerified; }
    public int getPermissionLevel() { return this.permissionLevel; }
    public String getPassword() { return this.password; }

    @Override
    public String toString() {
        return  super.toString() + "\n"+
                " User name: " + userName + "\n" +
                " Password: " + password  + "\n" +
                " Agent code: " + agentCode + "\n" +
                " Is verified: " + isVerified + "\n" +
                " Permission level: " + permissionLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return agentCode == agent.agentCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), agentCode);
    }
}

