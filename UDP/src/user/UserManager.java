package user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.OutputHandler;

public class UserManager {
	private static List<User> users = new ArrayList<User>();
	
	public static List<User> getList(){
		return users;
	}
	
	public static boolean insert(User user) {
		if(findByAddress(user.getAddress()) != null){
			return false;
		}

		user.setTimestamp(System.currentTimeMillis());
		users.add(user);
		
		OutputHandler.log("Added user " + user.getAddress() + " " + user.getNickname() + " to list.");
		
		return true;
	}

	public static User findByAddress(String address) {
		Iterator<User> i = users.iterator();
		
		while(i.hasNext()){
			User user = i.next();
			
			if(user.getAddress().equals(address) || user.getNickname().equals(address)){
				return user;
			}
		}
		
		return null;
	}
	
	public static boolean removeByAddress(String address) {
		User user = findByAddress(address);
		
		if(user != null){
			users.remove(user);
			
			OutputHandler.log("Removed user " + user.getAddress() + " " + user.getNickname() + " from list.");
			
			return true;
		}
		
		return false;
	}
	
	public static User getUserAtIndex(int index){
		try {
			return users.get(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public static boolean listIsEmpty(){
		return users.isEmpty();
	}
	
	public static int getNumberOfUsers(){
		return users.toArray().length;
	}
	
	public static User[] getArray(){
		return users.toArray(new User[users.size()]);
	}
	
	public static boolean updateTimestamp(String address){
		User user = findByAddress(address);
		
		if(user == null){
			return false;
		}
		
		user.setTimestamp(System.currentTimeMillis());
		return true;
	} 
}
