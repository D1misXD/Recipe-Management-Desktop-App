package com.mycompany.myapp;

import java.sql.*;
import java.time.LocalDate;
import javafx.scene.control.Alert;

public class DatabaseConnector {
    MenuController menucontroller;
    Connection conn;
    PreparedStatement pst;
    PreparedStatement pst2;
    public void Connect() {
        try {
            String url = "jdbc:mysql://HOST:PORT/DATABASE"; 
            String user = "root";
            String password = "your_DataBase_Password";
            
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
    
    boolean validateLogin(String usern, String pass) {
        try {
            String sql = "SELECT Username,pass FROM Userinfo WHERE Username = ? AND pass = ?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, usern);
            pst.setString(2, pass);
            
            ResultSet rs = pst.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {
            System.out.println("Database error in validateLogin: " + ex.getMessage());
            return false;
        }
    }
    
    public void setInfo(){
        String username = Session.getUsername();
        try {
            String sql = "SELECT * FROM Userinfo u JOIN Location l ON u.City_FK = l.City_N "
                    + "JOIN Countries c ON c.Country_Name = l.Country WHERE u.Username = ?";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            
            
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            Session.setUsers(rs.getInt("USER_ID"),
                             rs.getString("Username"),
                             rs.getString("Pass"),
                             rs.getString("Email"),
                             rs.getString("First_Name"),
                             rs.getString("Last_Name"),
                             rs.getString("Phone"),
                             rs.getDate("Age").toLocalDate(),
                             rs.getString("Gender"),
                             rs.getString("City_N"),
                             rs.getString("Country"),
                             rs.getInt("Recipe_Count"),
                             rs.getString("User_URL"),
                             rs.getString("Country_URL"));   
            
        } catch (SQLException ex) {
            System.out.println("Database error in validateLogin: " + ex.getMessage());
        }
    }
    
    public boolean updateSettings(String email, 
                               String F_N, 
                               String L_N, 
                               String phone, 
                               LocalDate age, 
                               String gender, 
                               String city_n, 
                               String country, 
                               String c_p,
                               String n_p){
            
        String username = Session.getUsername();
        try {
            String Sql3 = "SELECT (SELECT PASS FROM Userinfo WHERE USER_ID = ?) AS UserPass, (SELECT COUNTRY FROM Location WHERE City_N = ? AND Country = ?) AS CityCountry";
            pst = conn.prepareStatement(Sql3);
            pst.setInt(1, Session.getUSER_ID());
            pst.setString(2, city_n);
            pst.setString(3, country);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if(rs.getString("CityCountry") == null){
                new Alert(Alert.AlertType.WARNING, "Country named " + country + " does not have a city named " + city_n + "!"  ).show();
                return false;
            }
            else if(!email.equals("") && !F_N.equals("") && !L_N.equals("") && !phone.equals("") && age != null && !city_n.equals("")){
                
             if((rs.getString("UserPass").equals(c_p) && !n_p.equals(""))){
                 
                 String sql = "update Userinfo set Pass = ? WHERE USER_ID = ?";
                 pst = conn.prepareStatement(sql);
                 pst.setString(1, n_p);
                 pst.setInt(2, Session.getUSER_ID());
                 
                 pst.executeUpdate();
                 Session.setPass(n_p);
             }
             else if(!c_p.equals("") || !n_p.equals("")){
                 new Alert(Alert.AlertType.ERROR, "Wrong Current Password or New Password Can't be Empty").show();
                 return false;
             }
             
             
            String sql = "update Userinfo set "
                    + "Email = ?,"
                    + "First_Name = ?,"
                    + "Last_Name = ?,"
                    + "Phone = ?,"
                    + "Age = ?,"
                    + "Gender = ?,"
                    + "City_FK = ?"
                    + " WHERE USER_ID = ?";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, F_N);
            pst.setString(3, L_N);
            pst.setString(4, phone);
            pst.setDate(5, java.sql.Date.valueOf(age));
            pst.setString(6, gender);
            pst.setString(7, city_n);
            pst.setInt(8, Session.getUSER_ID());
            
            pst.executeUpdate();
            Session.setEmail(email);
            Session.setFirst_Name(F_N);
            Session.setLast_Name(L_N);
            Session.setPhone(phone);
            Session.setAge(age);
            Session.setGender(gender);
            Session.setCity_FK(city_n);
            Session.setCountry(country);
            String sql2 = "SELECT Country_URL,Country FROM Countries c JOIN Location l ON c.Country_Name = l.Country WHERE l.City_N = ?";
            
            pst = conn.prepareStatement(sql2);
            pst.setString(1, Session.getCity());
            
            ResultSet rs2 = pst.executeQuery();
            rs2.next();
            Session.setCountry(rs2.getString("Country"));
            Session.setCountry_URL(rs2.getString("Country_URL"));
            }
            else{
                new Alert(Alert.AlertType.WARNING, "No empty TextFields Please!").show();
                return false;
            }
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.WARNING, "Phone number must be 10 digits and the email has to be valid!").show();
            return false;
        }
        return true;
    }
    
    public boolean createUser(String email, 
                               String F_N, 
                               String L_N, 
                               String phone, 
                               LocalDate age, 
                               String gender, 
                               String city_n, 
                               String country, 
                               String password,
                               String passwordR,
                               String photoURL,
                               String username){
        try{
            String sqlCheck = "SELECT City_N,Country FROM Location WHERE City_N = ? AND Country = ?";
            pst = conn.prepareStatement(sqlCheck);
            pst.setString(1, city_n);
            pst.setString(2, country);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                new Alert(Alert.AlertType.ERROR, "There is no country named " + country + " with a city named " + city_n).show();
                return false;
            }
            
            String sqlCheck2 = "SELECT Username FROM Userinfo WHERE Username = ?";
            pst = conn.prepareStatement(sqlCheck2);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next()){
                new Alert(Alert.AlertType.ERROR, "The Username: " + username + " is already being used!").show();
                return false;
            }
            
            String sql = "INSERT INTO Userinfo (Username,Pass,Email,First_Name,Last_Name,Phone,Age,Gender,City_FK,Recipe_Count,User_URL) VALUES (?,?,?,?,?,?,?,?,?,0,?)";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setString(4, F_N);
            pst.setString(5, L_N);
            pst.setString(6, phone);
            pst.setDate(7, java.sql.Date.valueOf(age));
            pst.setString(8, gender);
            pst.setString(9, city_n);
            pst.setString(10, "/"+photoURL);
            pst.executeUpdate();
            return true;
        } catch(SQLException ex){
            new Alert(Alert.AlertType.ERROR, "Phone number must be 10 digits and the email has to be valid!").show();
            return false;
        }
    }
    
    public boolean createRecipe(Recipe recipe){
        try{
            String sql = "INSERT INTO Recipes (Recipe_Name,Ingredient_Count,Calories,Category,Difficulty,Descript,Recipe_URL,User_Id) VALUES (?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, recipe.getName());
            pst.setInt(2, recipe.getIngredientsC());
            pst.setInt(3, recipe.getCalories());
            pst.setString(4, recipe.getCategory());
            pst.setString(5, recipe.getDifficulty());
            pst.setString(6, recipe.getDescription());
            pst.setString(7, recipe.getImage());
            pst.setInt(8, Session.getUSER_ID());
            
            pst.executeUpdate();
            
            String sql2 = "SELECT COUNT(*) FROM Recipes WHERE USER_ID = ?";
            pst = conn.prepareStatement(sql2);
            pst.setInt(1, Session.getUSER_ID());
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            String sql3 = "UPDATE Userinfo SET Recipe_Count = ? WHERE USER_ID = ?";
            pst = conn.prepareStatement(sql3);
            pst.setInt(1, rs.getInt("COUNT(*)"));
            pst.setInt(2, Session.getUSER_ID());
            pst.executeUpdate();
            Session.setRecipe_Count(rs.getInt("COUNT(*)"));
            return true;
        } catch(SQLException ex){
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
        return false;
    }
    
    
    public void getRecipes(MenuController menucontroller){
        this.menucontroller = menucontroller;
        Recipe recipes;
        try{
            String sql = "SELECT Recipe_Name,Ingredient_Count,Calories,Category,Difficulty,Descript,Recipe_URL,User_Id FROM Recipes WHERE USER_ID = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Session.getUSER_ID());
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                recipes = new Recipe(rs.getString("Recipe_Name"),
                                     rs.getInt("Ingredient_Count"),
                                     rs.getInt("Calories"),
                                     rs.getString("Category"),
                                     rs.getString("Difficulty"),
                                     rs.getString("Descript"),
                                     rs.getString("Recipe_URL"),
                                     rs.getInt("User_Id"));
                menucontroller.setupRecipes(recipes);
            }
        } catch(SQLException ex) {
            new Alert(Alert.AlertType.WARNING, "Something went wrong!").show();
        }
    } 
    public void editRecipe(Recipe updatedRecipe){
        try {
            String sql = "UPDATE Recipes SET Recipe_Name = ?,Ingredient_Count = ?,Calories = ?,Category = ?,Difficulty = ?,Descript = ?,Recipe_URL = ? WHERE Recipe_Name = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, updatedRecipe.getName());
            pst.setInt(2, updatedRecipe.getIngredientsC());
            pst.setInt(3, updatedRecipe.getCalories());
            pst.setString(4, updatedRecipe.getCategory());
            pst.setString(5, updatedRecipe.getDifficulty());
            pst.setString(6, updatedRecipe.getDescription());
            pst.setString(7, updatedRecipe.getImage());
            pst.setString(8, updatedRecipe.getName());
            
            pst.executeUpdate();
        } catch(SQLException ex){
            new Alert(Alert.AlertType.WARNING, "Something went wrong!").show();
        }
    }
    
    public void deleteRecipe(Recipe recipe){
        try{
            String sql = "DELETE FROM Recipes WHERE Recipe_Name = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, recipe.getName());
            pst.executeUpdate();
            
            String sql2 = "SELECT COUNT(*) FROM Recipes WHERE USER_ID = ?";
            pst = conn.prepareStatement(sql2);
            pst.setInt(1, Session.getUSER_ID());
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            String sql3 = "UPDATE Userinfo SET Recipe_Count = ? WHERE USER_ID = ?";
            pst = conn.prepareStatement(sql3);
            pst.setInt(1, rs.getInt("COUNT(*)"));
            pst.setInt(2, Session.getUSER_ID());
            pst.executeUpdate();
            Session.setRecipe_Count(rs.getInt("COUNT(*)"));
        } catch(SQLException ex){
            new Alert(Alert.AlertType.WARNING, "Something went wrong!").show();
        }
    }
}