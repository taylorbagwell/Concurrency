package com.taylorb;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IntersectionHandler {
	private ArrayList<Car> cars;
	public static Lock lockNEBlock;
	public static Lock lockNWBlock;
	public static Lock lockSEBlock;
	public static Lock lockSWBlock;

	public IntersectionHandler() {
		cars = new ArrayList<Car>();
		lockNEBlock = new ReentrantLock();
		lockNWBlock = new ReentrantLock();
		lockSEBlock = new ReentrantLock();
		lockSWBlock = new ReentrantLock();
	}

	public void GenerateTraffic() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Number of Cars: ");
		int numOfCars = scanner.nextInt();
		scanner.close();

		for (int i = 1; i <= numOfCars; i++) {
			cars.add(new Car(i));
		}
	}

	public void StartTraffic() {
		for (Iterator<Car> iter = cars.iterator(); iter.hasNext();) {
			Car currentCar = iter.next();
			currentCar.start();
		}
	}
}