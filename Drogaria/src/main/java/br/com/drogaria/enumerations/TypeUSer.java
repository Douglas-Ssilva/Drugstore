package br.com.drogaria.enumerations;

public enum TypeUSer {

	ADMINISTRATOR, MANAGER, EMPLOYEE;

	@Override
	public String toString() {
		switch (this) {
		case ADMINISTRATOR:
			return "Administrator";
		case MANAGER:
			return "Maganer";
		case EMPLOYEE:
			return "Employee";
		default:
			return null;
		}
	}

}
