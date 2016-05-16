package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import network.Client;
import user.UnknownUser;
import user.User;
import user.UserManager;

public class LeaveAction extends AbstractAction {

	public String address;
	
	@Override
	public void receive(String senderIp) {
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			user = new UnknownUser(senderIp);
		}
		
		UserManager.removeByAddress(senderIp);
		
		OutputHandler.out(user.getNickname() + " saiu da sala.");
	}

	@Override
	public void send() throws JsonProcessingException {
		Client.sendMulticastMessage(toJson());
		OutputHandler.out("Você saiu da sala.");
	}

}
