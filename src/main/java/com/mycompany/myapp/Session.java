package com.mycompany.myapp;

import java.time.LocalDate;


public class Session {
    private static int USER_ID;
    private static String Username;
    private static String pass;
    private static String Email;
    private static String First_Name;
    private static String Last_Name;
    private static String Phone;
    private static LocalDate Age;
    private static String Gender;
    private static String City_FK;
    private static String Country;
    private static int Recipe_Count;
    private static String User_URL;
    private static String Country_URL;
    Session() {}

    public static void setUsers(int userid,
                                String username,
                                String password,
                                String mail,
                                String firstname,
                                String lastname,
                                String ph,
                                LocalDate age,
                                String gend,
                                String city_fk,
                                String country,
                                int recipe_count,
                                String user_url,
                                String country_url) {
        USER_ID = userid;
        Username = username;
        pass = password;
        Email = mail;
        First_Name = firstname;
        Last_Name = lastname;
        Phone = ph;
        Age = age;
        Gender = gend;
        City_FK = city_fk;
        Country = country;
        Recipe_Count = recipe_count;
        User_URL = user_url;
        Country_URL = country_url;
    }
    
    public static void setUsername(String username) { Username = username; }
    public static void setPass(String password) { pass = password; }
    public static void setEmail(String mail) { Email = mail; }
    public static void setFirst_Name(String F_N) { First_Name = F_N; }
    public static void setLast_Name(String L_N) { Last_Name = L_N; }
    public static void setPhone(String phone) { Phone = phone; }
    public static void setAge(LocalDate age) { Age = age; }
    public static void setGender(String gender) { Gender = gender; }
    public static void setCity_FK(String city) { City_FK = city; }
    public static void setCountry(String country) { Country = country; }
    public static void setCountry_URL(String country_url) { Country_URL = country_url; }
    public static void setRecipe_Count(int recipe_count) { Recipe_Count = recipe_count; };
    
    public static String getUsername() { return Username; }
    public static int getUSER_ID() { return USER_ID; }
    public static String getpass() { return pass; }
    public static String getEmail() { return Email; }
    public static String getFirst_Name() { return First_Name; }
    public static String getLast_Name() { return Last_Name; }
    public static String getPhone() { return Phone; }
    public static LocalDate getAge(){ return Age; }
    public static String getGender() { return Gender; }
    public static String getCity() { return City_FK; }
    public static String getCountry() { return Country; }
    public static int getRecipes() { return Recipe_Count; }
    public static String getUser_URL() { return User_URL; }
    public static String getCountry_URL() { return Country_URL; }
}