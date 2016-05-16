package io;

import java.text.SimpleDateFormat;
import java.util.Date;

import listener.Dispatcher;
import listener.MessageEvent;
import main.Application;

public class OutputHandler {
	public static final int VERBOSE = 3;
	public static final int PRINT_TRACE = 2;
	public static final int ONLY_ERRORS = 1;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static void out(String output){
		Dispatcher.getInstance().dispatchEvent(new MessageEvent(getTimestamp() + " " + output));
	}
	
	public static void log(String log){
		if(Application.getLogLevel() < VERBOSE){
			return;
		}
		Dispatcher.getInstance().dispatchEvent(new MessageEvent("LOG "+ getTimestamp() +": " + log));
	}
	
	public static void error(String log){
		if(Application.getLogLevel() < ONLY_ERRORS){
			return;
		}
		Dispatcher.getInstance().dispatchEvent(new MessageEvent("LOG "+ getTimestamp()  +": " + log));
	}
	
	public static void error(String log, Exception e){
		if(Application.getLogLevel() < ONLY_ERRORS){
			return;
		}
		
		if(Application.getLogLevel() >= PRINT_TRACE){
			e.printStackTrace();
		}
		
		Dispatcher.getInstance().dispatchEvent(new MessageEvent("LOG "+ getTimestamp()  +": " + log));
	}
	
	private static String getTimestamp(){
		return dateFormat.format(new Date());
	}
	
}
