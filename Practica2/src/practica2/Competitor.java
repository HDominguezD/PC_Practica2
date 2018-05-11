/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

/**
 *
 * @author h.dominguezd
 */
public class Competitor {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Competitor(int s, String n){
        score = s;
        name = n;
    }
    
    @Override
    public String toString(){
        return this.name + " : " + this.score;
    }
}
