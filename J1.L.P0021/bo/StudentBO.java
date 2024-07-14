/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import constant.Constant;
import entity.Student;
import utils.Validation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Nhat Anh
 */
public class StudentBO {

    private List<Student> list;

    public StudentBO() {
        this.list = new ArrayList<>();
        list.add(new Student("HE171754", "A", "Summer", "java"));
        list.add(new Student("HE171754", "A", "Fall", ".net"));
        list.add(new Student("HE171755", "B", "Summer", "java"));
        list.add(new Student("HE171755", "B", "Fall", "java"));
        list.add(new Student("HE171754", "a", "Spring", "java"));
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public StudentBO(List<Student> list) {
        this.list = list;
    }

    /**
     * Use to get new student from input
     * 
     * @return
     */
    public boolean add() {
        Student student = new Student();
        student.input(list);
        return list.add(student);
    }

    /**
     * Use to find student list find by name
     * 
     * @param text
     * @return
     */
    public List<Student> searchName(String text) {
        List<Student> listFind = new ArrayList<>();
        list.forEach(a -> {
            if (a.getStudentName().toLowerCase().contains(text.toLowerCase())) {
                listFind.add(a);
            }
        });
        list.sort((o1, o2) -> o1.getStudentName().compareTo(o2.getStudentName()));
        return listFind;
    }

    /**
     * 
     * @param id
     * @return
     */
    public boolean remove(String id) {
        int count =0;
        for(int i = list.size()-1; i>=0;i--){
            if(list.get(i).getId().equalsIgnoreCase(id)){
                list.remove(i);
                count++;
            }
        }
        return count > 0;
    }

    /**
     * 
     * @param id
     * @return a Integer list have value is index of ID
     */
    private List<Integer> searchId(String id) {
        List<Integer> number = new ArrayList<>();
        int index = 0;
        for (Student obj : list) {
            if (obj.getId().equalsIgnoreCase(id)) {
                number.add(index);
            }
            index++;
        }
        return number;
    }

    public boolean update(String id, String name){
        if(list.isEmpty()){
            return false;
        }
        list.forEach(ls -> {
            if(ls.getId().equalsIgnoreCase(id)){
            ls.setStudentName(name);}
        });
        return true;
    }

    /**
     * 
     * @param id
     * @return
     */
    public boolean update(int choice,int index, String infor) {
        Student student = list.get(index);
        switch (choice) {
            case 1:
                do {
                    if (!student.exist(list, student.getId(), infor, student.getCourseName())) {
                        list.get(index).setSemester(infor);
                        break;
                    }
                    System.out.println(Constant.messageStudentExist);
                    infor = Validation.getString(
                            "Enter new your semester: ",
                            "Your semester just have character and digit",
                            Constant.CONDITION_SEMESTER);
                } while (true);
                break;
            case 2:
                do {
                    if (!student.exist(list, student.getId(), student.getSemester(), infor)) {
                        list.get(index).setCourseName(infor);
                        break;
                    }
                    System.out.println(Constant.messageStudentExist);
                    infor = Validation.getString(
                            "Enter new your course name: ",
                            "Must follow fomat: JAVA, .NET, C/C++",
                            Constant.CONDITION_COURSE_NAME);
                } while (true);
                break;
        }
        return true;
    }

    /**
     * 
     * @param list
     */
    public void report() {
        Set<String> uniqueId = new HashSet<>();
        list.forEach(a -> {
                uniqueId.add(a.getId()+ a.getCourseName());
            }
        );
        Map<String, Integer> countMap = new HashMap<>();
        for (Student student : list) {
            String id = student.getId();
            String course = student.getCourseName();
            countMap.put(id+course, countMap.getOrDefault(id+course, 0) + 1);
        }
        uniqueId.forEach(a -> {
            for (Student student : list) {
                if (a.equalsIgnoreCase(student.getId()+student.getCourseName())) {
                    System.out.printf("%10s|%10s|%10s",
                            student.getStudentName(),
                            student.getCourseName(),
                            countMap.get(student.getId()+student.getCourseName()));
                    System.out.println();
                    break;
                }
            }
        });
    }
    /**
     * Use to take position follow list display
     * 
     * @return a index of position in list you choose
     */
    public int indexChange(String id){
        if(list.isEmpty()){
            return -1;
        }
        List<Integer> listIndex = searchId(id);
        if(listIndex.isEmpty()){
            return -1;
        }
        int index = 1;
            for (Integer integer : listIndex) {
                System.out.print(index + ". ");
                list.get(integer).display();
                index++;
            }
        return Validation.getInt(
                "Enter your position",
                "Out of range",
                "Invalid number",
                1, listIndex.size())-1;
    }

    /**
     * 
     * @param list
     */
    public void display(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        list.forEach(a -> {
            a.display();
        });
    }

    /**
     * Use to display information of student
     * 
     */
    public void display() {
        if (list.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        list.forEach(a -> {
            a.display();
        });
    }
}
