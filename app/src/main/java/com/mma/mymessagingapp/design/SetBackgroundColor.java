package com.mma.mymessagingapp.design;

import android.graphics.Color;

import com.mma.mymessagingapp.R;

import java.util.Random;

/**
 * Created by tayo on 11/13/2015.
 */
public class SetBackgroundColor {

    public static String FirstLetter(String name){
        String index = "";
        char[] letters = name.toCharArray();
        String letter = String.valueOf(letters[0]).toUpperCase();

        index = letter;

        return index;
    }

    public static int Color(String name){
        int color = 0;
        char[] letters = name.toCharArray();
        String letter = String.valueOf(letters[0]).toLowerCase();
        switch (letter){
            case "a":
                color = R.color.red500;
                break;
            case "b":
                color = R.color.pink500;
                break;
            case "c":
                color = R.color.purple500;
                break;
            case "d":
                color = R.color.deepPurple500;
                break;
            case "e":
                color = R.color.indigo500;
                break;
            case "f":
                color = R.color.blue500;
                break;
            case "g":
                color = R.color.lightBlue500;
                break;
            case "h":
                color = R.color.cyan500;
                break;
            case "i":
                color = R.color.teal500;
                break;
            case "j":
                color = R.color.green500;
                break;
            case "k":
                color = R.color.lightGreen500;
                break;
            case "l":
                color = R.color.lime500;
                break;
            case "m":
                color = R.color.yellow500;
                break;
            case "n":
                color = R.color.amber500;
                break;
            case "o":
                color = R.color.orange500;
                break;
            case "p":
                color = R.color.deepOrange500;
                break;
            case "q":
                color = R.color.brown500;
                break;
            case "r":
                color = R.color.green500;
                break;
            case "s":
                color = R.color.blueGrey500;
                break;
            case "t":
                color = R.color.red900;
                break;
            case "u":
                color = R.color.pink900;
                break;
            case "v":
                color = R.color.purple900;
                break;
            case "w":
                color = R.color.indigo900;
                break;
            case "x":
                color = R.color.blue900;
                break;
            case "y":
                color = R.color.brown900;
                break;
            case "z":
                color = R.color.deepOrange900;
                break;
            default:
                Random random = new Random();
                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                color = Color.rgb(r,g,b);
                break;

        }

        return color;
    }
}
