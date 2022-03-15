package com.dstushar;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static final byte MONTHS_IN_YEAR = 12;
    static final byte PERCENT = 100;

    public static void main(String[] args) {
        // Mortgage Calculator
        int principal = (int) readNumber("Principal: ",1000,1000000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ",1,30);
        byte years = (byte) readNumber("Period (Years): ",1,30);

        mortgageCalculator(principal, annualInterest, years);
        paymentSchedule(principal, annualInterest, years);
    }


    private static void paymentSchedule(int principal, float annualInterest, byte years){
        double monthlyInterest = (annualInterest/MONTHS_IN_YEAR)/PERCENT;
        double paymentMonthly;
        byte numberOfTotalPayments = (byte) (years*MONTHS_IN_YEAR);
        double mainBalanceWithInterest = Math.pow((1+monthlyInterest),numberOfTotalPayments);

        System.out.println("PAYMENT SCHEDULE\n----------------");
        for (byte i = 1; i <= numberOfTotalPayments; i++) {
            paymentMonthly = principal*(mainBalanceWithInterest-Math.pow((1+monthlyInterest),i))/(mainBalanceWithInterest-1);
            String paymentFormatted = NumberFormat.getCurrencyInstance().format(paymentMonthly);
            System.out.println(paymentFormatted);
        }
    }

    private static double readNumber(String prompt,int minLimit,int maxLimit) {
        double value;
        while (true) {
            Scanner takeInput = new Scanner(System.in);
            System.out.print(prompt);
            value = takeInput.nextDouble();
            if (value >= minLimit && value <= maxLimit) {
                break;
            } else {
                System.out.println("Enter a value between "+minLimit+" and "+maxLimit);
            }
        }
        return value;
    }

    private static void mortgageCalculator(int principal, float annualInterest, byte years){
        float monthlyInterest = (annualInterest/MONTHS_IN_YEAR)/PERCENT;
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);


        double mortgage = principal * ((monthlyInterest*Math.pow(1+monthlyInterest,numberOfPayments))/(Math.pow(1+monthlyInterest,numberOfPayments)-1));
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("MORTGAGE\n--------\nMonthly Payments: "+mortgageFormatted);
    }

    public static void greetUser(String name){
        System.out.println("Hello "+name);
    }
}
