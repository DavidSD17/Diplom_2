package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Order {

     public String[] ingredients = {"61c0c5a71d1f82001bdaaa70","61c0c5a71d1f82001bdaaa72","61c0c5a71d1f82001bdaaa75",
             "61c0c5a71d1f82001bdaaa76","61c0c5a71d1f82001bdaaa78","61c0c5a71d1f82001bdaaa79","61c0c5a71d1f82001bdaaa6f","61c0c5a71d1f82001bdaaa6d"};


    public Order(String[] ingredients){
       this.ingredients = ingredients;
   }

    public String[] CreateOrder() {
        return ingredients;
    }
    public Order(){

    }

}
