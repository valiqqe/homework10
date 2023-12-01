package UsersJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


public class Main {
    public static void main(String[] args) {

        String inputFilePath = "file1.txt";
        String outputJsonFilePath = "user.json";

        List<User> users = readFromFile(inputFilePath);
        System.out.println("Read " + users.size() + " users from file.");
        writeToJson(users, outputJsonFilePath);
        System.out.println("Conversion completed. JSON file created: " + outputJsonFilePath);
    }

    private static List<User> readFromFile(String filePath) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\s");
                String name = values[0];
                int age = Integer.parseInt(values[1]);
                users.add(new User(name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static void writeToJson(List<User> users, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
