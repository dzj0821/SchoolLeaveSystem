package pers.dzj0821.SchoolLeaveSystem.service;

public interface UserService {
	
	public String register(String username, String password, String name, int telephone, String RSAPrivateKey);
	/**
	 * 
	 * @param username
	 * @param password - the password was hex256 encode and then RSA encrypt 
	 * @return
	 */
	public String login(String username, String password);
	
	public String modify(String username, String password, String name, int telephone);
	
	public String logout();
	
	
}
