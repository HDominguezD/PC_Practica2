/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author h.dominguezd
 */
public class Practica2 {

    /**
     * @param args the command line arguments
     */
    private static int nCompetitors;
    private static Competitor[] competitors;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        if(args.length == 1){
            File input = new File(args[0]);
            if(!input.exists()){
                System.out.println("El archivo introducido no ha sido encontrado");
                return;
            }
            competitors = readFile(input);
            Competitor[] orderedCompetitors = mergeSort();
            generateOutput(orderedCompetitors);
        }
        
        else System.out.println("Introduce el archivo como parámetro");
    }
    
    private static Competitor[] readFile(File fileName) throws FileNotFoundException, IOException{
        FileReader reader = new FileReader(fileName);
        BufferedReader b = new BufferedReader(reader);
        nCompetitors = Integer.parseInt(b.readLine());
        Competitor[] comp = new Competitor[nCompetitors];
        String line = null;
        for(int i = 0; i < nCompetitors; i++){
            line = b.readLine();
            String[] nameAndScore = line.split(": ");
            Competitor competitor = new Competitor(Integer.parseInt(nameAndScore[1]), nameAndScore[0]);
            comp[i] = competitor;
        }
        
        return comp;
    }
    
    private static Competitor[] mergeSort(){
        ForkJoinPool pool = new ForkJoinPool();
        Competitor[] comp = pool.invoke(new MergeSortTask(competitors, 0, competitors.length -1));
        pool.shutdown();
        return comp;
    }
    
    private static void generateOutput(Competitor[] comp) throws FileNotFoundException{
        try(PrintWriter pw = new PrintWriter("./output.txt")){
            pw.println(nCompetitors);
            for (int i = 0; i < nCompetitors; i++){
                pw.println(comp[i].getName()+ ": "+comp[i].getScore());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
