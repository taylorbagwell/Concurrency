package com.taylorb;

import java.util.Random;

public class Car extends Thread {
	private int carId;

	public Car(int carId) {
		this.carId = carId;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int turnDirection = rand.nextInt((3 - 1) + 1) + 1;
		int approachDirection = rand.nextInt((4 - 1) + 1) + 1;

		switch (turnDirection) {
		case 1:
			GoStraight(approachDirection);
			break;
		case 2:
			TurnRight(approachDirection);
			break;
		case 3:
			TurnLeft(approachDirection);
			break;
		}
	}

	public int getCarId() {
		return this.carId;
	}

	private void TurnRight(int approachDirection) {
		switch (approachDirection) {
		case 1: // North
			IntersectionHandler.lockSEBlock.lock();
			System.out.printf("Car %s is going North & turning Right.\n", this.carId);
			IntersectionHandler.lockSEBlock.unlock();

			break;
		case 2: // East
			IntersectionHandler.lockSWBlock.lock();
			System.out.printf("Car %s is going East & turning Right.\n", this.carId);
			IntersectionHandler.lockSWBlock.unlock();

			break;
		case 3: // South
			IntersectionHandler.lockNWBlock.lock();
			System.out.printf("Car %s is going South & turning Right.\n", this.carId);
			IntersectionHandler.lockNWBlock.unlock();

			break;
		case 4: // West
			IntersectionHandler.lockNEBlock.lock();
			System.out.printf("Car %s is going West & turning Right.\n", this.carId);
			IntersectionHandler.lockNEBlock.unlock();

			break;
		}
	}

	private void TurnLeft(int approachDirection) {
		switch (approachDirection) {
		case 1: // North
			IntersectionHandler.lockSEBlock.lock();
			IntersectionHandler.lockNEBlock.lock();
			IntersectionHandler.lockNWBlock.lock();
			System.out.printf("Car %s is going North & turning Left.\n", this.carId);
			IntersectionHandler.lockNWBlock.unlock();
			IntersectionHandler.lockNEBlock.unlock();
			IntersectionHandler.lockSEBlock.unlock();

			break;
		case 2: // East
			IntersectionHandler.lockSEBlock.lock();
			IntersectionHandler.lockNEBlock.lock();
			IntersectionHandler.lockSWBlock.lock();
			System.out.printf("Car %s is going East & turning Left.\n", this.carId);
			IntersectionHandler.lockNEBlock.unlock();
			IntersectionHandler.lockSEBlock.unlock();
			IntersectionHandler.lockSWBlock.unlock();

			break;
		case 3: // South
			IntersectionHandler.lockSEBlock.lock();
			IntersectionHandler.lockNWBlock.lock();
			IntersectionHandler.lockSWBlock.lock();
			System.out.printf("Car %s is going South & turning Left.\n", this.carId);
			IntersectionHandler.lockSEBlock.unlock();
			IntersectionHandler.lockSWBlock.unlock();
			IntersectionHandler.lockNWBlock.unlock();

			break;
		case 4: // West
			IntersectionHandler.lockNEBlock.lock();
			IntersectionHandler.lockNWBlock.lock();
			IntersectionHandler.lockSWBlock.lock();
			System.out.printf("Car %s is going West & turning Left.\n", this.carId);
			IntersectionHandler.lockSWBlock.unlock();
			IntersectionHandler.lockNWBlock.unlock();
			IntersectionHandler.lockNEBlock.unlock();

			break;
		}
	}

	private void GoStraight(int approachDirection) {
		switch (approachDirection) {
		case 1: // North
			IntersectionHandler.lockSEBlock.lock();
			IntersectionHandler.lockNEBlock.lock();
			System.out.printf("Car %s is going North & going Straight.\n", this.carId);
			IntersectionHandler.lockNEBlock.unlock();
			IntersectionHandler.lockSEBlock.unlock();

			break;
		case 2: // East
			IntersectionHandler.lockSEBlock.lock();
			IntersectionHandler.lockSWBlock.lock();
			System.out.printf("Car %s is going East & going Straight.\n", this.carId);
			IntersectionHandler.lockSWBlock.unlock();
			IntersectionHandler.lockSEBlock.unlock();

			break;
		case 3: // South
			IntersectionHandler.lockNWBlock.lock();
			IntersectionHandler.lockSWBlock.lock();
			System.out.printf("Car %s is going South & going Straight.\n", this.carId);
			IntersectionHandler.lockSWBlock.unlock();
			IntersectionHandler.lockNWBlock.unlock();

			break;
		case 4: // West
			IntersectionHandler.lockNEBlock.lock();
			IntersectionHandler.lockNWBlock.lock();
			System.out.printf("Car %s is going West & going Straight.\n", this.carId);
			IntersectionHandler.lockNWBlock.unlock();
			IntersectionHandler.lockNEBlock.unlock();

			break;
		}
	}
}