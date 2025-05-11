package com.examly.springapp.exception;

public class LoanApplicationNotFoundException extends Exception {

    public LoanApplicationNotFoundException(String message){
        super(message);
    }




}
// public class Email{
//     public static boolean checkAge(int age){
//         if(age<18){
//             throw new IllegalArgumentException("Age should be greater than 30");
//         }
//         return true;
//     }
// }
// public class Main{
//     public static void main(String[] args){
//         System.out.println("Hello World");
//         try{
//             Email.checkAge(15);
//         }
//     }
// }