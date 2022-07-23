package dbops;

public class DataSingleton {
	
	private static final DataSingleton instance = new DataSingleton();
	private String email;
	
	private DataSingleton() {
		
	}

	public static DataSingleton getInstance() {
		return instance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
