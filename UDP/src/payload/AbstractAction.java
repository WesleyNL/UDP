package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractAction {
	public String action;
	protected String targetIp;
	
	public AbstractAction() {
		action = generateActionName();
	}
	
	public AbstractAction(String targetIp) {
		action = generateActionName();
		this.targetIp = targetIp;
	}
	
	public void setTargetIp(String targetIp){
		this.targetIp = targetIp;
	}
	
	private String generateActionName(){
		String name = getClass().getName().replace("Action", "").replace("payload.", "");
		
		char[] chars = name.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		
		return new String(chars);
	}
	
	public abstract void receive(String senderIp);
	
	public abstract void send() throws JsonProcessingException;
	
	protected String toJson() throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(this);
	}
}
