package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import network.Client;
import user.UnknownUser;
import user.User;
import user.UserManager;

public class SayAction extends AbstractAction {
	public String target;
	public String content;
	
	@Override
	public void receive(String senderIp) {
		System.out.println(senderIp);
		User user = UserManager.findByAddress(senderIp);
		
		if(user == null){
			user = new UnknownUser(senderIp);
		}
		
		if(target == null){
			OutputHandler.out(user.getNickname() + " diz à todos: " + content);
		}else{
			User targetUser;
			
			if(target.equals(Application.getUser().getAddress())){
				targetUser = Application.getUser();
			}else{
				targetUser = UserManager.findByAddress(target);
			}
			
			if(targetUser == null){
				targetUser = new UnknownUser(target);
			}
			
			OutputHandler.out(user.getNickname() + " diz à "+ targetUser.getNickname() +": " + content);
		}
	}
	
	@Override
	public void send() throws JsonProcessingException{
		Client.sendMulticastMessage(toJson());
		
		if(target == null){
			OutputHandler.out(Application.getUser().getNickname() + " diz à todos: " + content);
		}else{
			User targetUser;
			
			if(target.equals(Application.getUser().getAddress())){
				targetUser = Application.getUser();
			}else{
				targetUser = UserManager.findByAddress(target);
			}
			
			if(targetUser == null){
				targetUser = new UnknownUser(target);
			}
			
			OutputHandler.out(Application.getUser().getNickname() + " diz à "+ targetUser.getNickname() +": " + content);
		}
	}

}
