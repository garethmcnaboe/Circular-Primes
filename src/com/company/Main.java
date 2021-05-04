package com.company;

import java.util.Scanner;

public class Main {

    public static boolean[] sieve;

    public static void populateSieve() {
        for(int i = 2; i< 10_000_001; i++){
            sieve[i] = true;
        }

        for (int i = 2; i < 3164; i++) {
            if (sieve[i] == false) {

            } else {
                sieve[i] = checkPrime(i);
                //System.out.println(i);
                if (sieve[i] == true) {
                    for (int j = i+i; j < 10_000_001; j = j + i) {
                        sieve[j] = false;
                    }
                }
            }
        }
        System.out.println("Sieve Populated");
    }

    public static boolean checkPrime(int number){
        boolean prime = true;
        int limit = (number/2)+1;

        for(int i = 2; i<limit; i++){
            if(number%i ==0 ){
                prime = false;
            }
        }
        return prime;
    }

    public static boolean checkSieve(int number){
        System.out.println(number);
        System.out.println("CheckSieve called");
        System.out.println(sieve[number]);
        boolean prime = sieve[number];
        return prime;
    }

    public static boolean checkCircularPrime(int number){
        boolean circularPrime = true;
        double numDigits = 0;
        int numberCheck1 = number;
        int numberCheck2 = number;

        //checks the number of digits in the number.
        while(numberCheck1 > 0){
            numberCheck1 = numberCheck1 / 10;
            numDigits++;
        }

        while(circularPrime){
            double lastDigit = numberCheck2 % 10;
            int remainingDigits = numberCheck2 / 10;
            numberCheck2 = (int) (remainingDigits + (Math.pow(10, numDigits-1)*lastDigit));
            circularPrime = checkSieve(numberCheck2);

            if(numberCheck2 == number){
                break;
            }
        }
        return circularPrime;
    }

    public static void main(String[] args) {
        boolean isPrime = false;
        boolean isCircularPrime = false;
        int counter = 0;
        sieve = new boolean[10_000_001];
        populateSieve();

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        for(int i = 2; i<input+1; i++) {
            isPrime = checkSieve(i);

            if(isPrime) {
               isCircularPrime = checkCircularPrime(i);
                if(isCircularPrime){
                    counter++;
                    System.out.println(i);
                }
            }
        }
        System.out.println(counter);
    }
}

