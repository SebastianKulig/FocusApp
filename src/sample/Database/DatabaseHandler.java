package sample.Database;

import sample.model.User;
import sample.model.Task;
import java.sql.*;

public class DatabaseHandler {
    Connection dbConnection;
    public  Connection getDbConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/focus?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=WET", "root", "focus");

        return dbConnection;
    }

    public void deleteTask(int userId, int taskId){
        String query = "DELETE FROM tasks WHERE id_user" + "=?" + " AND " + " id_task " + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, taskId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // adding new user to our database
    public boolean createUser (User user1){
        boolean ifcreated = false;
        if(checkUserNameAvailability(user1)) {
            String creator = "INSERT INTO " + "users" + "(" + "first_name" + "," + "last_name" + "," + "username" + "," + "password" + ")" + "VALUES(?,?,?,?)";
            try {
                PreparedStatement prep = getDbConnection().prepareStatement(creator);
                prep.setString(1, user1.getFirstName());
                prep.setString(2, user1.getLastName());
                prep.setString(3, user1.getUserName());
                prep.setString(4, user1.getPassword());
                prep.executeUpdate();
                ifcreated = true;
            } catch (java.sql.SQLException e) {
                e.printStackTrace(); //metod exception wyrzucająca co i gdzie się wywaliło
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{

            System.out.println("this user name is already taken");
            ifcreated = false;
        }
        return ifcreated;
    }

    public ResultSet returnUserFromDB(User user1){
        ResultSet result = null;
        if(!user1.getUserName().equals("") && !user1.getPassword().equals("")) {
            String instruction = "SELECT * FROM " + "users" + " WHERE " + "username" + "=?" + " AND " + "password" + "=?";
            try {
                PreparedStatement prepSt = getDbConnection().prepareStatement(instruction);
                prepSt.setString(1, user1.getUserName());
                prepSt.setString(2, user1.getPassword());
                result = prepSt.executeQuery();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //select all from users where username = user1 and password = user1
            //zwracamy odpowiedni wiersz

        }else{
            System.out.println("fail");
        }
        return result;
    }

    public void addTask(Task task){
        String creator = "INSERT INTO" +  " tasks " + " (" + " id_user "+  "," + " task " +"," + " description " + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement prep = getDbConnection().prepareStatement(creator);
            prep.setInt(1, task.getUserId());
            prep.setString(2, task.getTask_name());
            prep.setString(3, task.getDescription());


            prep.executeUpdate();
        }catch (java.sql.SQLException e){
            e.printStackTrace(); //metod exception wyrzucająca co i gdzie się wywaliło
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ResultSet getUserTasks(int userId){
        ResultSet resultTask = null;
        String query = "SELECT * FROM " + "tasks" + " WHERE " + "id_user " + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultTask = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultTask;
    }

    private boolean checkUserNameAvailability(User user1){
        ResultSet result = null;
        String query = "SELECT * FROM " + "users" + " WHERE " + "username" + "=?";
        try {
            PreparedStatement prepSt = getDbConnection().prepareStatement(query);
            prepSt.setString(1, user1.getUserName());
            result = prepSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean accessibility = false;
        try {
            if(!result.next()){ //pusty resultSet.next zwraca false
               accessibility = true;
            }else {
                accessibility = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accessibility;
    }

    }

