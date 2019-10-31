package sample.Database;

import sample.model.User;
import sample.model.Task;
import java.sql.*;

public class DatabaseHandler extends Conf {
    Connection dbConnection;
    public  Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString;
        Class.forName("com.mysql.cj.jdbc.Driver");

        //jdbc:mysql://localhost:3307/app?autoReconnect=true&useSSL=false
        //connectionString = "jdbc:mysql://127.0.0.1:3306/id11284411_focus?autoReconnect=true&useSSL=false";
        connectionString = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7309865?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=WET";
        //problemy z czasem były, trzeba była jawnie zachodnioeuropejski dać, ale nie wiem czy to polski
        //Class.forName("com.mysql.cj.jdbc.Driver"); //w tej wersji już nie trzeba
        dbConnection = DriverManager.getConnection(connectionString, dataBaseUser, dataBasePassword);
        //dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/focus", "root", "focus");
        return dbConnection;
    }

    public void deleteTask(int userId, int taskId){
        String query = "DELETE FROM tasks WHERE id_user" + "=?" + " AND " + " id_task " + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, taskId);
            preparedStatement.execute();
            preparedStatement.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    // adding new user to our database
    public void createUser (User user1){
        String creator = "INSERT INTO " + "users" + "(" + "first_name" +"," + "last_name"+"," +"username"+"," +"password" + ")" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement prep = getDbConnection().prepareStatement(creator);
            prep.setString(1, user1.getFirstName());
            prep.setString(2, user1.getLastName());
            prep.setString(3, user1.getUserName());
            prep.setString(4, user1.getPassword() );

            prep.executeUpdate();
        }catch (java.sql.SQLException e){
            e.printStackTrace(); //metod exception wyrzucająca co i gdzie się wywaliło
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
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

    }

