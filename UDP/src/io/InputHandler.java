package io;

import action.ActionBuilder;
import payload.AbstractAction;
import user.User;
import user.UserManager;

public class InputHandler {

	public boolean readInput(String input) {
		if (input.equals("exit")) {
			OutputHandler.log("Application terminated");

			return false;
		}

		OutputHandler.log("User input: " + input);
		
		String[] parts = input.split("/");

		User user = UserManager.findByAddress(parts[0]);

		//Verifica se o usu·rio n„o existe, mas depois permite que tente enviar para o usu·rio?
		if(user == null){
			OutputHandler.log("Usu√°rio n√£o encontrado. A mensagem n√£o ser√° enviada");
		}
		
		try {
			System.out.println(parts[1]);
			System.out.println(user.getAddress());
			OutputHandler.log("Sending message: '" + parts[1] + "' to " + user.getAddress());
			input = "{\"action\":\"whisper\",\"content\":\"" + parts[1] + "\",\"target\":\"RANDOM\"}!";
			input.replace("MESSAGE", parts[1]);
			input.replace("RANDOM", user.getAddress());
			
			AbstractAction action = ActionBuilder.buildFromJson(input);

			if (action.getClass().getName().equals("payload.WhisperAction")) {
				action.setTargetIp(user.getAddress());
				action.send();
			}
			
			action.send();

		} catch (Exception e) {
			OutputHandler.error("Um erro ocorreu", e);
			e.printStackTrace();
		}

		return true;
	}

}
