/*
Name: Isiah Behner
Assignment: Programming Assignment 4: Dynamic Programming
Course/Semester: CS 371 - Fall 2017
Instructor: Wolff
Sources consulted: Wolff & Classmates
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mainJobThing {

    public static void main(String[] args) throws FileNotFoundException {

        File jobFile = new File("jobs.in");
        Scanner scan = new Scanner(jobFile);

        int numProbs = scan.nextInt(); //number of problems
        scan.nextLine();

        for(int i = 0; i < numProbs; i++) {
            int jobs = scan.nextInt(); //getting jobs

            int [] seconds = new int [jobs+1];
            int [] cost = new int [jobs+1];
            seconds[0] = 0;
            cost[0] = 0;

            for(int j = 1; j < jobs + 1; j++) {
                seconds[j] = scan.nextInt(); //filling the seconds array
                cost[j] = (int)(Math.round(scan.nextDouble() * 100)); //filling the cost array
                scan.nextLine();
            }

            int totalTime = scan.nextInt();

            if(scan.hasNext())
                scan.nextLine();

            dynamicProgramKnapsack job = new dynamicProgramKnapsack(seconds, cost, totalTime);
            job.solutionFinder(totalTime, seconds, cost);
            System.out.println(job.toString(i, totalTime));
        }
        scan.close();
    }//end of main

}//end of class
