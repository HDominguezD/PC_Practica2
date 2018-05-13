/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateContestants {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter("contestants2.txt")) {
            int nContestants = 10;
            pw.println(nContestants);
            for (int i = 0; i < nContestants; i++) {
                pw.println("Contestant "+(i+1)+": "+new Random().nextInt(nContestants*10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}