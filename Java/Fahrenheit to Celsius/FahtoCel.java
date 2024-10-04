
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.text.DecimalFormat;

public class FahtoCel
{
    public static void main(String[] args)
    {
        try (Scanner myObj = new Scanner(System.in)) {
            System.out.print("Enter Degrees Fahrenheit: "); //ASk
            double fahrenheit = myObj.nextDouble(); //Read
            toCel(fahrenheit); //Call
        }
    }

    static void toCel(double fah)
    {
        double result = 5.0/9.0 * (fah - 32.0); //Process
        DecimalFormat resultFormat = new DecimalFormat("#.##"); //Format

        System.out.println("The equivalent in Celsius is: "+ resultFormat.format(result)); //Result
    }
}
