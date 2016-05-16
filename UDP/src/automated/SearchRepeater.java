package automated;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.OutputHandler;
import main.Application;
import payload.KeepAliveAction;
import payload.SearchAction;
import user.User;
import user.UserManager;

public class SearchRepeater implements Runnable {

	public static final long MAX_IDLE_TIME = 60000;
	
	public void run() {
		while (true) {
			if(UserManager.listIsEmpty()){
				try {
					seekUsers();
				} catch (InterruptedException e) {
					OutputHandler.error("Um erro ocorreu", e);
					Application.halt();
				}
			}else{
				try {
					maintainUserList();
				} catch (InterruptedException e) {
					e.printStackTrace();
					Application.halt();
				}
			}
		}

	}

	public void seekUsers() throws InterruptedException{
		SearchAction searchAction = new SearchAction();
		searchAction.nickname = Application.getUser().getNickname();

		while (UserManager.listIsEmpty()) {
			try {
				searchAction.send();
			} catch (JsonProcessingException e) {
				OutputHandler.log("Error sending search action: can't parse json");
			}

			TimeUnit.SECONDS.sleep(2);
		}

	}
	
	public void maintainUserList() throws InterruptedException{
		KeepAliveAction keepAliveAction = new KeepAliveAction();
		
		while (! UserManager.listIsEmpty()) {
			try {
				keepAliveAction.send();
				removeIdleUsers();
			} catch (JsonProcessingException e) {
				OutputHandler.log("Error sending search action: can't parse json");
			}

			TimeUnit.SECONDS.sleep(1);
		}

	}
	
	private void removeIdleUsers(){
		for(User user: UserManager.getArray()){
			
			if(user.getTimestamp() + MAX_IDLE_TIME < System.currentTimeMillis()){
				UserManager.removeByAddress(user.getAddress());
				OutputHandler.out("Removendo " + user.getNickname() + " por inatividade.");
			}
		}
	}

}
