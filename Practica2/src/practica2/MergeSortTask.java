/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author h.dominguezd
 */
public class MergeSortTask extends RecursiveTask<Competitor[]>{

    private Competitor[] competitors;
    private int low;
    private int high;

    public MergeSortTask(Competitor[] c, int l, int h) {
        competitors = c;
        low = l;
        high = h;
    }
    @Override
    protected Competitor[] compute() {
        Competitor[] comp;
        if(high - low == 1){
            comp = new Competitor[2];
            if(competitors[high].getScore() < competitors[low].getScore()){
                comp[0] =competitors[low];
                comp[1] = competitors[high];
            } else {
                comp[0] =competitors[high];
                comp[1] = competitors[low];
            }
        } else if(high == low){
            comp = new Competitor[1];
            comp[0] = competitors[low];
        } else {
            int mid = low + (high - low) / 2;
            MergeSortTask left = new MergeSortTask(competitors, low, mid);
            MergeSortTask right = new MergeSortTask(competitors, mid +1, high);
            
            left.fork();
            right.fork();
            
            Competitor[] compLeft = left.join();
            Competitor[] compRight = right.join();
            comp = merge(compLeft, compRight);
        }
        return comp;
    }
    
    Competitor[] merge(Competitor[] left, Competitor[] right){
        Competitor[] competitor = new Competitor[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < left.length && j < right.length){
            if(left[i].getScore() >= right[j].getScore()){
                competitor[k] = left[i];
                i++;
            } else {
                competitor[k] = right[j];
                j++;
            }
            k++;
        }

        while(i < left.length){
            competitor[k] = left[i];
            i++;
            k++;
        }

        while(j < right.length){
            competitor[k] = right[j];
            j++;
            k++;
        }
    return competitor;
    }
    
}
