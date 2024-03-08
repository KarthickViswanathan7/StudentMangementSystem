package com.kce.student;

import com.kce.student.bean.Grade;
import com.kce.student.bean.Student;
import com.kce.student.dao.*;
import com.kce.student.util.DuplicateKeyException;
import com.kce.student.util.studentNotFoundException;

import java.sql.SQLException;
import java.util.*;

public class Main{
    private static Scanner scanner;
    

	public static void main(String[] args) throws ClassNotFoundException, DuplicateKeyException, SQLException, studentNotFoundException {
        scanner = new Scanner(System.in);
        StudentDAO obj= new StudentDAO();
        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Search a Student");
            System.out.println("6. Display Students(Grade):");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

           
                int studentId;
                int age;
                String name;
                Grade grade;
				if(choice==1) {
					try {
					System.out.print("Enter name: ");
                    name =(scanner.next());
                    System.out.print("Enter student ID: ");
                    studentId = Integer.parseInt(scanner.next());
                    System.out.print("Enter age: ");
                    age = Integer.parseInt(scanner.next());
                    System.out.println("Choose Grade:\n1.O\n2.A\n3.B\n4.C");
                    int choce=Integer.parseInt(scanner.next());
                    grade=obj.chooseGrade(choce);
                    Student student=new Student(name, studentId, age,grade);
                    	obj.addStudent(student);
                    	obj.addGrade(student);
                   } 
					catch(DuplicateKeyException dke) {
						System.out.println(dke.getMessage());
					}
					catch (SQLException e) {
                        System.out.println("!!! Cannot Store values");
                   }catch(NumberFormatException e) {
                	   System.out.println("!!!!!!!!!!!!\n Enter valid input format");
                   }
                }
                else if(choice==2) {
                	try {
                    System.out.print("Enter student ID: ");
                    studentId = Integer.parseInt(scanner.next());
                    System.out.print("Enter new name: ");
                    name = scanner.next();
                    System.out.print("Enter new age: ");
                    age = Integer.parseInt(scanner.next());
                    System.out.println("Choose Grade:\n1.O\n2.A\n3.B\n4.C");
                    int choce=Integer.parseInt(scanner.next());
                    grade=obj.chooseGrade(choce);
                    Student student=new Student(name, studentId, age,grade);
                    
                        obj.updateStudent(student);
                        obj.updateGrade(student);
                    } 
                       catch(NumberFormatException e) {
             	        System.out.println("!!!!!!!!!!!!\n Enter valid input format");
                }
                }
                else if(choice==3) {
                    System.out.print("Enter student ID: ");
                    studentId = Integer.parseInt(scanner.next());
                    try {
                        obj.deleteStudent(studentId);
                        obj.deleteGrade(studentId);
                    } catch (SQLException e) {
                        System.out.println("Cannot delete Student\n ID not found");
                    }
                }
                else if(choice==4) {
                    try {
                        obj.displayAllStudents();
                    } catch (SQLException e) {
                        System.out.println("Cannot  display values ");
                    }
                }
                else if(choice==5) {
                        System.out.print("Enter student ID: ");
                        studentId = Integer.parseInt(scanner.next());
                        try {
                            obj.searchStudent(studentId);
                        } catch (SQLException e) {
                            System.out.println("Student Not Found\n");
                        }
                }
                else if(choice==6) {
                	System.out.println("Enter the grade :");
                	String grad=scanner.next();
                	grad.toUpperCase();
                	obj.displayByGrade(grad);
                }
                else if(choice==7) {
                	System.out.println("Exiting the program...");
                    break;
                }
                else {
                    System.out.println("Invalid choice. Please try again.");
            }
            }
        }
    }
