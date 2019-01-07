/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;
import java.util.ArrayList;
/**
 *
 * @author svsv
 */
public class CourseStat{
    private int week;
    private String fullName;
    private String course;
    private int hours;
    private ArrayList<Integer> exercises;

    public void setAll(int week, String fullName, int hours, ArrayList<Integer> exercises) {
        this.week = week;
        this.course = course;
        this.hours = hours;
        this.exercises = exercises;
        this.fullName=fullName;
    }

    public String getFullName() {
        return fullName;
    }



    public ArrayList<Integer> getExercises() {
        return exercises;
    }
    
        public int  countExercises() {
            int max =0;
     
           for (int ex : exercises) {
           max=max+ex;
            
          }
        return max;
    }



    public void printExercises() {
        for (int ex : exercises) {
            System.out.print(ex + ",");

        }
    }

    @Override
    public String toString() {
        return "" + course + ", viikko " + week + " tehtyjätehtäviä yhteensä " + exercises.size() + " aikaa kului " + hours + ", tehdyt tehtävät:";
    }

}

