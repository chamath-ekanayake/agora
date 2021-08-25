package com.example.other;

import com.example.config.Constants;

import java.util.Random;

public class SequenceGenerator {
    public static String generateSequence(String Class){

        if(Class.equalsIgnoreCase("user"))
        {
            Random rnd = new Random();
            return Constants.AUTO_USER_SEQUENCE .concat(String.valueOf(rnd.nextInt(1000)));
        }
        if(Class.equalsIgnoreCase("vehicle"))
        {
            Random rnd2 = new Random();
            return Constants.AUTO_VEHICLE_SEQUENCE .concat(String.valueOf(rnd2.nextInt(1000)));
        }
        if(Class.equalsIgnoreCase("inquiry"))
        {
            Random rnd3 = new Random();
            return Constants.AUTO_INQUIRY_SEQUENCE .concat(String.valueOf(rnd3.nextInt(1000)));
        }
        if(Class.equalsIgnoreCase("payment"))
        {
            Random rnd4 = new Random();
            return Constants.AUTO_PAYMENT_SEQUENCE .concat(String.valueOf(rnd4.nextInt(1000)));
        }
        if(Class.equalsIgnoreCase("bookingVehicle"))
        {
            Random rnd4 = new Random();
            return Constants.AUTO_VEHICLEBOOKING_SEQUENCE .concat(String.valueOf(rnd4.nextInt(1000)));
        }

        if(Class.equalsIgnoreCase("market"))
        {
            for(int i =0; i < 100; i++) {
                Random rnd5 = new Random();
                return (String.valueOf(rnd5.nextInt(10) + 1));
            }
        }
        return"ID";
    }
}
