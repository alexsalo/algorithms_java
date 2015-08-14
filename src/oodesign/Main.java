package oodesign;

import oodesign.Factory.FactoryType;

public class Main {
	public static void main(String[] args) {
		Singletone.getInstance().printHello();
		
		Factory factory;
		factory = Factory.createFactory(FactoryType.CarFactory);
		factory.printName();
		factory = Factory.createFactory(FactoryType.ToyFactory);
		factory.printName();
	}
}
