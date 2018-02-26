import java.util.ArrayList;
import java.util.Scanner;

public class dynamicProgramKnapsack {

    private int numProblems, jobs, table[][], w[], v[];
    private int totalSecondsUsed; //keeps track of the seconds used

    /**
     * Constructor method that fills in the dynamic programming table
     * with the appropriate values corresponding to the pseudocode
     * @param seconds an array filled with seconds values
     * @param cost an array filled with cost values
     * @param totalTime max time capacity
     */
    public dynamicProgramKnapsack(int[] seconds, int[] cost, int totalTime){
        table = new int [seconds.length][totalTime + 1]; //dp Table

        //filling the rows with 0's
        for(int i = 0; i < seconds.length; i++) {
            table[i][0] = 0;
        }

        //filling the columns with 0's
        for(int j = 0; j < totalTime; j++) {
            table[0][j] = 0;
        }

        //filling the dpTable
        for(int r = 1; r < table.length; r++){
            for(int c = 1; c < table[0].length; c++){
                int max;
                if(c - seconds[r] >= 0){
                    int noItem = table[r-1][c];
                    int item = cost[r] + table[r-1][c-seconds[r]];
                    if(noItem < item){
                        max = item;
                    }
                    else{
                        max = noItem;
                    }
                    table[r][c] = max;
                }
                else{
                    table[r][c] = table[r-1][c];
                }
            }
        }

    }//end constructor

    /**
     * Method that finds the seconds that were used the get the solution
     * @param total maximum amount of time that can be used
     * @param seconds array filled with second values
     * @param cost array filled with cost values
     */
    public void solutionFinder(int total, int[] seconds, int[] cost){
        totalSecondsUsed = 0; //keeps the total amount of seconds used to get the solution
        int row = (table.length)-1;
        int col = (table[0].length)-1;

        int item = table[row][col];

        while(item != 0 && col >= 0 && row >= 0){
            if(item > table[row-1][col]){
                totalSecondsUsed += seconds[row];
                col = col - seconds[row];
                row = row - 1;
            }
            else{
                row = row - 1;
            }
            item = table[row][col];
        }

    }//end of solutionFinder method

    /**
     * Method to display the correct solution to the corresponding problems
     * to the console
     * @param i the problem number
     * @param total seconds used to for the solution
     * @return a string representation of the solution
     */
    public String toString(int i, int total) {
        double money = table[table.length - 1][table[0].length - 1];
        money = money/100;
        String str = "Problem " + (i + 1) + ": " + totalSecondsUsed + " seconds scheduled for $" +  String.format("%.2f", money);

        return str;
    }//end of toString method

}//end of class
