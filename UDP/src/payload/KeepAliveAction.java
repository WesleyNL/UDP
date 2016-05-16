package payload;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import network.Client;
import user.User;
import user.UserManager;

public class KeepAliveAction extends AbstractAction {

	public String nickname;
	public List<User> users;

	@Override
	public void receive(String senderIp){
		User sender = new User(senderIp, nickname);

		if (UserManager.insert(sender)) {
			OutputHandler.out(sender.getNickname() + " entrou na sala");
		}
		
		for(User user : users){
			UserManager.insert(user);
		}

	}
	
	@Override
	public void send() throws JsonProcessingException {
		nickname = Application.getUser().getNickname();
		users = UserManager.getList();
		
		if(targetIp != null){
			Client.sendMessageToIp(targetIp, toJson());
		}else{
			Client.sendMulticastMessage(toJson());
		}
		
	}
}
