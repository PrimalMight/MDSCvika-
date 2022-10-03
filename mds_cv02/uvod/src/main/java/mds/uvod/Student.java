package mds.uvod;

import java.util.ArrayList;

public class Student {
    public String surname, name;
    public int id, year;

    public Student(String surname, String name, int id, int year){
        this.surname = surname;
        this.name = name;
        this.id = id;
        this.year = year;
    }

    public String toString(){
        return "Jmeno: " + this.name + " Prijmeni: " + this.surname + " ID: " + this.id + " Rok: " + this.year;
    }





}
