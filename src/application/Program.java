package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Department;
import model.entities.HourContract;
import model.entities.Worker;
import model.exceptions.HourContractException;
import model.exceptions.WorkerException;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.print("Enter department's name: ");
			String departmentName = sc.nextLine();
			System.out.println();
			System.out.println("Enter worker data:");
			System.out.print("Name: ");
			String workerName = sc.nextLine();
			System.out.print("Level (BEGINNER, INTERMEDIATE, ADVANCED or EXPERT): ");
			String workerLevel = sc.nextLine();
			System.out.print("Base salary: ");
			double baseSalary = sc.nextDouble();
			Worker worker = new Worker(workerName, model.entities.enums.WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
			worker.WorkerExceptions();
			
			System.out.println();
			System.out.print("How many contracts to this worker: ");
			int N = sc.nextInt();
			while (N < 0) {
				System.out.print("Error! Enter a valid number of contracts: ");
				N = sc.nextInt();
			}
			
			for (int i=1; i<=N; i++) {
				System.out.println();
				System.out.println("Enter contract (#" + i + ") data: ");
				System.out.print("Date (DD/MM/YYYY): ");
				Date contractDate = sdf.parse(sc.next());
				System.out.print("Value per hour: ");
				double valuePerHour = sc.nextDouble();
				System.out.print("Duration (hours): ");
				int hours = sc.nextInt();
				HourContract contract = new HourContract(contractDate, valuePerHour, hours);
				contract.HourContractExceptions();
				worker.addContract(contract);
			}
			System.out.println();
			System.out.print("Enter month and year to calculate income (MM/YYYY): ");
			String monthAndYear = sc.next();
			int month = Integer.parseInt(monthAndYear.substring(0, 2));
			int year = Integer.parseInt(monthAndYear.substring(3));
			System.out.println();
			System.out.println("Department: " + worker.getDepartment().getName());
			System.out.println("Name: " + worker.getName());
			System.out.println("Level: " + workerLevel);
			System.out.println("Income for: " + monthAndYear + ": $" + String.format("%.2f", worker.income(year, month)));
		}
		catch (IllegalArgumentException error) {
			System.out.println("Error! Enter an existing worker level.");
		}
		catch (InputMismatchException error) {
			System.out.println("Error! Enter a number or a integer number.");
		}
		catch (ParseException error) {
			System.out.println("Error! Enter an existing date.");
		}
		catch (StringIndexOutOfBoundsException error) {
			System.out.println("Error! Enter an existing date.");
		}
		catch (HourContractException error) {
			System.out.println(error.getMessage());
		}
		catch (WorkerException error) {
			System.out.println(error.getMessage());
		}
		
		sc.close();
	}
}
