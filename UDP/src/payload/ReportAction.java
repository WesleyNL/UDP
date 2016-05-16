package payload;

import java.security.InvalidParameterException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import network.Client;
import user.UnknownUser;
import user.User;
import user.UserManager;

public class ReportAction extends AbstractAction {
	public String message;
	
	@Override
	public void receive(String senderIp){
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			user = new UnknownUser(senderIp);
		}

		OutputHandler.out(user.getNickname() + " reportou um erro: " + message);	
	}

	@Override
	public void send() throws JsonProcessingException {
		if(targetIp == null){
			throw new InvalidParameterException("targetIp must be set");
		}
		
		Client.sendMessageToIp(targetIp, toJson());
		
		OutputHandler.out(Application.getUser().getNickname() + " reportou um erro: " + message);
	}
	
}
