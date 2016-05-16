package payload;

import java.security.InvalidParameterException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import network.Client;
import user.UnknownUser;
import user.User;
import user.UserManager;

public class WhisperAction extends AbstractAction{

	public String content;
	
	@Override
	public void receive(String senderIp) {
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			user = new UnknownUser(senderIp);
		}
		
		OutputHandler.out(user.getNickname() + " sussura para " + Application.getUser().getNickname() + ": " + content);
	}
	
	@Override
	public void send() throws JsonProcessingException {
		if(targetIp == null){
			throw new InvalidParameterException("targetIp must be set");
		}
		
		Client.sendMessageToIp(targetIp, toJson());
		
		User targetUser;
		
		if(targetIp == Application.getUser().getAddress()){
			targetUser = Application.getUser();
		}else{
			targetUser = UserManager.findByAddress(targetIp);
		}
		
		if(targetUser == null){
			targetUser = new UnknownUser(targetIp);
		}
		
		OutputHandler.out(Application.getUser().getNickname() + " sussura para " + targetUser.getNickname() + ": " + content);
	}
}
