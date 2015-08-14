package oodesign;

public class Singletone{
	private static Singletone _instance = null;
	
	private Singletone(){}
	
	public static Singletone getInstance(){
		if (_instance == null){
			_instance = new Singletone();
		}
		return _instance;
	}
	
	protected void printHello(){
		System.out.println("hellow");
	}
}
