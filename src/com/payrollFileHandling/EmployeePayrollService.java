package com.payrollFileHandling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

	enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}
	
	List<EmployeePayrollData> employeePayrollList;
	
	public EmployeePayrollService(){
		this.employeePayrollList = new ArrayList<>();
	}
	
	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}
	
	private void readEmployeePayrollData(Scanner consoleInputReader) {
		System.out.print("Enter employee id: ");
		int id = consoleInputReader.nextInt();
		
		System.out.print("Enter employee name: ");
		String name = consoleInputReader.next();
		
		System.out.print("Enter employee salary: ");
		double salary = consoleInputReader.nextDouble();
		System.out.println();
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));  
	}
	
	private void writeEmployeePayrollData() {
		System.out.println("\nWriting employee payroll service data:\n" + employeePayrollList);
	}
	
	public static boolean deleteFiles(File file) throws IOException {
	   File[] allContents = file.listFiles();
	   
	   if(allContents != null) {
		   for(File file1 : allContents) {
			   deleteFiles(file1);
		   }
	   }
	   return file.delete();
	}
	
	public static void main(String[] args) {
		ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		
		EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
		
		Scanner consoleInputReader = new Scanner(System.in);
		
		employeePayrollService.readEmployeePayrollData(consoleInputReader);
		employeePayrollService.writeEmployeePayrollData();
	}
}
