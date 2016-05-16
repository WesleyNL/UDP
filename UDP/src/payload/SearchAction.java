package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import network.Client;
import user.User;
import user.UserManager;

public class SearchAction extends AbstractAction {
	public String nickname;

	@Override
	public void receive(String senderIp) {
		if(senderIp.equals(Application.getUser().getAddress())){
			return;
		}
		
		User user = new User(senderIp, nickname);
		if (UserManager.insert(user)) {
			OutputHandler.out(user.getNickname() + " entrou na sala");
		}
		
		KeepAliveAction keepAliveAction = new KeepAliveAction();
		keepAliveAction.setTargetIp(senderIp);
		
		try {
			keepAliveAction.send();
		} catch (JsonProcessingException e) {
			OutputHandler.error("Um erro ocorreu", e);
		}

	}

	@Override
	public void send() throws JsonProcessingException {
		Client.sendBroadcastMessage(toJson());
	}

}
