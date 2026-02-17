/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;


/**
 *
 * @author D1mis
 */
public class Recipe {
    private final String name;
    private final int IngredientsC;
    private final int calories;
    private final String category;
    private final String difficulty;
    private final String description;
    private final String photo_URL;
    private final int User_ID;
    
    
    public String getImage() {
         return this.photo_URL;
    }
    public String getName(){
        return this.name;
    }
    public int getIngredientsC(){
        return this.IngredientsC;
    }
    public int getCalories(){
        return this.calories;
    }
    public String getCategory(){
        return this.category;
    }
    public String getDifficulty(){
        return this.difficulty;
    }
    public String getDescription(){
        return this.description;
    }
    public Recipe(String name, int ingredientsC, int calories, String category, String difficulty, String description, String photo, int user_id){
        this.name = name;
        this.IngredientsC = ingredientsC;
        this.calories = calories;
        this.category = category;
        this.difficulty = difficulty;
        this.description = description;
        this.photo_URL = photo;
        this.User_ID = user_id;
    }
}
