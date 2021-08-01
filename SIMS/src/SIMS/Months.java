package SIMS;

public enum Months{
	January("01"), February("02"), March("03"), April("04"), May("05"), June("06"), 
	July("07"), August("08"), September("09"), October("10"), November("11"), December("12");
	
//	January("01"), FEBRUARY("02"), MARCH("03"), APRIL("04"), MAY("05"), JUNE("06"), 
//	JULY("07"), AUGUST("08"), SEPTEMBER("09"), OCTOBER("10"), NOVEMBER("11"), DECEMBER("12");

	private String numericalRepresentation;
	
	Months(String numericalRepresentation){
		this.numericalRepresentation = numericalRepresentation;
	}
	
	public String getNumericalRepresentation() {
		return numericalRepresentation;
	}
}
