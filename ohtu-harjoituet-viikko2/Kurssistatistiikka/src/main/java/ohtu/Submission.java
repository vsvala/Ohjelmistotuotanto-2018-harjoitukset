package ohtu;

import java.util.ArrayList;

public class Submission {

    private int week;
    private String course;
    private int hours;
    private ArrayList<Integer> exercises;

    public void setAll(int week, String course, int hours, ArrayList<Integer> exercises) {
        this.week = week;
        this.course = course;
        this.hours = hours;
        this.exercises = exercises;
    }

    public int getWeek() {
        return week;
    }

    public int gethours() {
        return hours;
    }

    public String getCourse() {
        return course;
    }
    


    public ArrayList<Integer> getExercises() {
        return exercises;
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
