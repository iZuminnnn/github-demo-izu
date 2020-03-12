/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import business.StudentManagement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import entity.Student;

/**
 *
 * @author cauch
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void menu() {
        System.out.println(" 1.	Create.");
        System.out.println(" 2.	Find and Sort.");
        System.out.println(" 3.	Update/Delete.");
        System.out.println(" 4.	Report.");
        System.out.println(" 5.	Save File.");
        System.out.println(" 6.	Exit.");
        System.out.print(" Enter your choice:  ");
    }

    public static String validatorInput(String msg) throws IOException {
        String s1 = "";
        do {
            System.out.println(msg);
            s1 = in.readLine();
            if (s1.length() == 0) {
                System.out.println("Input not isEmpty !!!");
            } else {
                break;
            }
        } while (true);
        return s1;
    }

    public static String validatorInputCourse() throws IOException {
        //loop until user input correct
        while (true) {
            String result = validatorInput("Enter Course Name:(java/ .net/ c/c++)");
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")) {
                result = "Java";
                return result;
            }
            if (result.equalsIgnoreCase(".net")) {
                result = ".Net";
                return result;
            }
            if (result.equalsIgnoreCase("c/c++")) {
                result = "C/C++";
                return result;
            }
            System.out.println("There are only three courses: Java, .Net, C/C++");

        }
    }

    public static Student inputStudent() throws IOException {

        String id = validatorInput("Enter id: ");
        String studentName = validatorInput("Enter Name: ");
        String sem = validatorInput("Enter sem: ");
        String courseName = validatorInputCourse();
        Student st = new Student(id, studentName, sem, courseName);

        return st;
    }

    public static Student inputUpdateStudent(String id) throws IOException {
        String studentName = validatorInput("Enter Name: ");
        String sem = validatorInput("Enter sem: ");
        String courseName = validatorInput("Enter Course Name: ");
        Student st2 = new Student(id, studentName, sem, courseName);
        return st2;
    }

    public static void main(String[] args) {
        try {
            // TODO code application logic here

            StudentManagement sm = new StudentManagement();
//            sm.add(new Student("1", "Nguyen Van An", "Spring", "Java"));            
//            sm.add(new Student("1", "Nguyen Van An", "Summer", "Java"));
//            sm.add(new Student("1", "Nguyen Van An", "Summer", "Java"));        
//            sm.add(new Student("1", "Nguyen Van An", "Winter", ".Net"));
//            sm.add(new Student("4", "Nguyen Van B", "Winter", ".Net"));
//            sm.add(new Student("8", "Nguyen Van B", "Summer", ".Net"));
//            sm.add(new Student("5", "Nguyen Van B", "Spring", "Java"));
//            sm.add(new Student("6", "Nguyen Van C", "Summer", "Java"));
            sm.loadFile();
            while (true) {
                //Show menu option
                menu();
                String choice = in.readLine();
                switch (choice) {
                    case "1":
                        int count = 0;
                        for (int i = 0; i < 100; i++) {
                            count++;
                            if (count <= 2) {

                                try {
                                    // add student
                                    Student st = inputStudent();
                                    //input student detail
                                    sm.add(st);
                                } catch (Exception ex) {
                                    System.out.println("Error: " + ex.getMessage());
                                }
                            } else if (count > 2) {
                                System.out.println("Do you want to continue (Y/N)?");
                                String yn = in.readLine();
                                if (yn.equalsIgnoreCase("y")) {
                                    try {
                                        // add student
                                        Student st = inputStudent();

                                        //input student detail
                                        sm.add(st);
                                    } catch (Exception ex) {
                                        System.out.println("Error: " + ex.getMessage());
                                    }
                                } else if (yn.equalsIgnoreCase("n")) {
                                    break;
                                }
                                System.err.println("Please input y/Y or n/N.");

                            }

                        }

                        break;

                    case "2":
                        System.out.println("Enter Student Name: ");
                        String st1 = in.readLine();
                        for (int i = 0; i < sm.find(st1).size(); i++) {
                            System.out.println(sm.find(st1).get(i));
                        }

                        break;
                    case "3":
                        System.out.println("Enter id: ");
                        String id = in.readLine();
                        System.out.println("Do you want to update (U) or delete (D) student.");
                        String choice2 = in.readLine().toLowerCase();
                        switch (choice2) {
                            case "u":
                                Student st = new Student(id);

                                if (sm.update(st)) {
                                    sm.update(inputUpdateStudent(id));
                                    System.out.println("Update Successful !!!");
                                } else {
                                    System.out.println("Cannot find ID in List !!!");

                                }
                                break;
                            case "d":

                                if (sm.delete(id)) {
                                    sm.delete(id);
                                    System.out.println("Delete Successful !!!");
                                } else {
                                    System.out.println("Cannot find id in List !!!");

                                }

                                break;
                            default:
                                System.out.println("Must U or D");

                                break;
                        }
                        break;
                    case "4":
                        for (int i = 0; i < sm.report().size(); i++) {
                            System.out.println(sm.report().get(i).toString());
                        }
                        break;
                    case "5":
                        sm.outputFile();

                        break;
                    case "6":

                        return;
                }

            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
