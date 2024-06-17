package com.proyect.classsmartphone;

public class ClassSmartphone {
    public static class Samsung {
       // Propiedades de la clase Mustang
       private String model;
       private String processor;
       private int price;

       // Constructor de la clase Mustang
       public Samsung(String model, String processor, int price) {
           this.model = model;
           this.processor = processor;
           this.price = price;
       }

       // Métodos getter y setter para cada propiedad
       public String getmodel() {
           return model;
       }

       public void setmodel(String model) {
           this.model = model;
       }

       public String getprocessor() {
           return processor;
       }

       public void setprocessor(String processor) {
           this.processor = processor;
       }

       public int getprice() {
           return price;
       }

       public void setprice(int price) {
           this.price = price;
       }
    }
}
