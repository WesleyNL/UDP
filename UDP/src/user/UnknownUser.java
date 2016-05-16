package user;

public class UnknownUser extends User{

	public UnknownUser(String address) {
		super(address, "[Desconhecido]"+address);
	}
}
