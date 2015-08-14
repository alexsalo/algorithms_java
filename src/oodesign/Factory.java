package oodesign;

public class Factory{
	public enum FactoryType{
		ToyFactory, CarFactory
	}	
	
	public static Factory createFactory(FactoryType ftype){
		if (ftype == FactoryType.ToyFactory){
			return new ToyFactory();
		}else if (ftype == FactoryType.CarFactory){
			return new CarFactory();
		}
		return null;		
	}
	
	public void printName(){
		System.out.println("Factory");
	}
}

class ToyFactory extends Factory{	
	public void printName(){
		System.out.println("ToyFactory");
	}
}

class CarFactory extends Factory{	
	public void printName(){
		System.out.println("CarFactory");
	}
}

