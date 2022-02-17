package com.example.cis436_proj1;

public class Player {
    int score;
    public Player(int score){
        this.score = score;
    }

    public  void resetScore(){
        this.score = 0;
    }
    public void setScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
