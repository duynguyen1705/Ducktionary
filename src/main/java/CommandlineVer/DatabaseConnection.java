package CommandlineVer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Code dung de import lan dau.
public class DatabaseConnection {

    public ArrayList<String> getJSONFromFile(String fileName) {
        ArrayList<String> commandLines = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(new File(fileName));

            for (JsonNode objectNode : jsonNode) {
                String name = objectNode.get("name").asText();
                String type = objectNode.get("type").asText();
                String definition = objectNode.get("definition").asText();

                // Replace single quotes with '-'
                definition = definition.replace("'", "-");

                // Use prepared statement to safely insert data
                String command = "INSERT INTO DICTIONARY (word_target, word_type, word_explain) VALUES (?, ?, ?)";
                commandLines.add(command);
                commandLines.add(name);
                commandLines.add(type);
                commandLines.add(definition);

                System.out.println(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandLines;
    }

    public static void addWordToDatabase(Connection connection) {
        ArrayList<String> commandLines = new DatabaseConnection().getJSONFromFile("D:\\project\\Ducktionary\\Ducktionary_1.0\\src\\main\\res\\wordList.json");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commandLines.get(0));
            for (int i = 1; i < commandLines.size(); i += 4) {
                preparedStatement.setString(1, commandLines.get(i));
                preparedStatement.setString(2, commandLines.get(i + 1));
                preparedStatement.setString(3, commandLines.get(i + 2));
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        /**
         * Hien tai chua co database online, neu muon chay demo thi tao rieng mot csdl o may.
         * Code tren su dung csdl mySQL, ket noi qua mySQL Connector cua Java. Thu vien Connector trong phan lib.
         * Add vao project duoi local bang cach an Ctrl + Alf + Shift + S, vao library, them file .jar vao.
         * Hoac dung maven cho nhanh :> Luu y la file maven t dang dung 8.0.30, check lai ma dung ban moi nhat
         */
        String url = "jdbc:mysql://localhost:3306/word_list"; //thay bang ten csdl
        String username = "root"; //nen dung root, thay username khac chua chac ket noi duoc
        String password = "Nguyen175@"; // thay bang password cua csdl

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
//            // Tạo table - run trong lần đầu chạy code
            Statement stm = connection.createStatement();

//            String addTable = "CREATE TABLE DICTIONARY " + "(word_target VARCHAR (50), " + "word_explain VARCHAR(511), "
//                    + "word_type VARCHAR(127), " + "word_example VARCHAR(1023) )";
//            stm.executeUpdate(addTable);

            //đưa từ vào table
            addWordToDatabase(connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


