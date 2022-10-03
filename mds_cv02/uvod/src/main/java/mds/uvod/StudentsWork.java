package mds.uvod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;

@RestController
@RequestMapping("studentswork")
public class StudentsWork{

    public ArrayList<Student> students = new ArrayList<Student>();

    {
        students.add(new Student("Carboch","David",221654,2000));
        students.add(new Student("Černohous","Matěj",229997,2000));
        students.add(new Student("Mička","Petr",230132,2000));
        students.add(new Student("Hamran","David",230253,2001));
        students.add(new Student("Babeľa","Miroslav",230530,2000));
        students.add(new Student("Balušík","Peter",230531,2001));
        students.add(new Student("Bílek","František",230534,2001));
        students.add(new Student("Bukovský","Jan",230535,2000));
        students.add(new Student("Buzovský","Viktor",230536,2001));
        students.add(new Student("Ďuráč","Jakub",230546,2001));
        students.add(new Student("Gradoš","Matej",230551,2001));
        students.add(new Student("Horčička","Patrik",230558,2000));
        students.add(new Student("Jílek","Jiří",230573,2001));
        students.add(new Student("Klíma","Petr",230586,2001));
        students.add(new Student("Kounický","Filip",230597,2000));
        students.add(new Student("Král","Lukáš",230601,2001));
        students.add(new Student("Kubant","Michal",230608,2001));
        students.add(new Student("Dudar","Oleksandra",230847,2002));
        students.add(new Student("Fišarová","Anežka",230890,2001));
        students.add(new Student("Valíček","Matěj",230903,2001));
        students.add(new Student("Wittner","Alex",230914,2000));
        students.add(new Student("Zdražil","Jakub",230917,2000));
        students.add(new Student("Sadecká","Valentýna",231275,2000));
        students.add(new Student("Brandejs","Jakub",233264,2000));
        students.add(new Student("Kohout","David",195823,1996));
        students.add(new Student("Masaryk","Tomáš",123456,1850));
        students.add(new Student("Číka","Petr",10,1982));
    }

    @GetMapping("student")
    public String student(@RequestParam String name, String id ){
        return "Student: " + name +" ID: " + id;
    }

    @GetMapping("students")
    public String studentsMethod(@RequestParam(defaultValue = "-1") int vutid){
        if(vutid == -1){
            String html = "";
            for (Student stud: students) {
                html += stud.toString() + "<br>";
            }

            return html;
        }
        else{
            Student hledanyStudent = null;
            for (Student stud: students) {
                if(stud.id == vutid){
                    hledanyStudent = stud;
                }
            }
            if(hledanyStudent != null){
                return hledanyStudent.toString();
            }else{
                return "Nelze najit studenta";
            }
        }
    }

}
