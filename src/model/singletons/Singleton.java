package model.singletons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class Singleton<T> {

    protected final String fileName;
    protected final ObjectMapper mapper;

    protected Singleton(String fileName) {
        this.fileName = fileName;
        mapper = new ObjectMapper();
    }

    /*
     * T - the type of class trying to read
     * e.g: Agent
     *
     * Constructs a list of all objects of type T in the corresponding file name.
     */
    public Set<T> read(Class<T> classType) {
        try {

            JavaType type = mapper.getTypeFactory().constructCollectionType(Set.class, classType);
            return new ObjectMapper().readValue(new File(this.fileName), type);

            /* Errors handling */
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Return List<T> */
        return null;
    }

    /*
     * Converts object to json format.
     * Returns an empty string if failed.
     */
    private String objToJson(Set<T> obj) {
        String jsonSting;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            jsonSting = ow.writeValueAsString(obj);
            return jsonSting;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
     * Saves a list of type T to file `fileName`
     *
     * Params:
     * 1. List of objects to insert to the json file.
     * 2. Name of the file the data should be inserted to.
     */
    public boolean saveSet(Set<T> obj) {
        try {
            /* Opens the file for writing */
            FileWriter myWriter = new FileWriter(this.fileName);

            /*
             * Sends the object to objToJson which convert the object to json.
             * Writes to file.
             * Return true if successful.
             */
            myWriter.write(this.objToJson(obj));
            myWriter.close();
            return true;
            /* Error handling. Returns false if could not write to file. */
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

}
