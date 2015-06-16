package utils;

public class Player {
	
	private double money;
	private String name;

	public Player(double money, String name) {
		this.money = money;
		this.name = name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
