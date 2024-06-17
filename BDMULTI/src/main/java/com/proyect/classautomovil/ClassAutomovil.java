package com.proyect.classautomovil;

public class ClassAutomovil {
    public static class Mustang {
        // Propiedades de la clase Mustang
        private String model;
        private String motor;
        private int price;

        // Constructor de la clase Mustang
        public Mustang(String model, String motor, int price) {
            this.model = model;
            this.motor = motor;
            this.price = price;
        }

        // Métodos getter y setter para cada propiedad
        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getMotor() {
            return motor;
        }

        public void setMotor(String motor) {
            this.motor = motor;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
