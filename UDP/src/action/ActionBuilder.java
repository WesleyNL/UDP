package action;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import payload.*;

public class ActionBuilder {

	public static AbstractAction buildFromJson(String json) throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		FakeAction fakeAction = mapper.readValue(json, FakeAction.class);

		if(fakeAction.action.equals("search")){
			return mapper.readValue(json, SearchAction.class);
		}
		
		if(fakeAction.action.equals("say")){
			return mapper.readValue(json, SayAction.class);
		}
		
		if(fakeAction.action.equals("whisper")){
			return mapper.readValue(json, WhisperAction.class);
		}
		
		if(fakeAction.action.equals("report")){
			return mapper.readValue(json, ReportAction.class);
		}

		if(fakeAction.action.equals("keepAlive")){
			return mapper.readValue(json, KeepAliveAction.class);
		}
		
		if(fakeAction.action.equals("leave")){
			return mapper.readValue(json, LeaveAction.class);
		}
		
		return null;
	}
}
