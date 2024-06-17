package com.proyect.classweapon;

public class ClassWeapon {
    public static class AK {
        // Propiedades de la clase ak
        private String model;
        private String ammunition;
        private int price;
 
        // Constructor de la clase ak
        public AK(String model, String ammunition, int price) {
            this.model = model;
            this.ammunition = ammunition;
            this.price = price;
        }
 
        // Métodos getter y setter para cada propiedad
        public String getmodel() {
            return model;
        }
 
        public void setmodel(String model) {
            this.model = model;
        }
 
        public String getammunition() {
            return ammunition;
        }
 
        public void setammunition(String ammunition) {
            this.ammunition = ammunition;
        }
 
        public int getprice() {
            return price;
        }
 
        public void setprice(int price) {
            this.price = price;
        }
     }
}
