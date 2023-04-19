package model;


import java.util.*;
import model.SimpleArray;
public class Order {

      SimpleArray simpleArray = new SimpleArray();

    public String[] ingredients = Arrays.copyOf(simpleArray.getSimpleArray(),new Random().nextInt(8 - 2) + 2);


    public Order(){

    }


    public Order(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
