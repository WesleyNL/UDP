package user;

public class User {
	private String nickname;
	private String address;
	private long timestamp;
	
	public User(){
		
	}
	
	public User(String address, String nickname) {
		this.address = address;
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
