/**
 * 
 */
package crown.defensivesqlutil.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crown.defensivesqlutil.uitls.inserts.DefendInserts;

/**
 * Driver to perform the Defensive SQL tasks
 * 
 * @author Sean Booker
 * @version 1.0
 *
 */
public class Driver {
	protected static final Log log = LogFactory.getLog(Driver.class);
	
	private static String action = "";
	private static String firstParm = "";
	private static String secondParm = "";
	
	private static final String ACTION_DEFEND_INSERTS = "defend-inserts";
	
	private static void pauseForDebug() {
		 log.info("Pausing for Debug, please connect Remote Debugger now and hit any key...");
		 //  open up standard input
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      //  read the username from the command-line; need to use try/catch with the
	      //  readLine() method
	      try {
	         br.readLine();
	      } catch (IOException ioe) {
	         System.out.println("IO error trying pause for Debug!");
	         System.exit(1);
	      }
	}
	
	//This class will have 3 inputs:
	// 1. Action
	//		- defend-inserts (this will defend SQL against inserts)
	// 2. File Input
	// 3. File Output
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Driver Started...");
		
		pauseForDebug();
		log.info("Driving...");
		
		int returnCode = 0;

		if (args.length > 0) {
			log.debug("Getting by args");
			setByArguments(args);
		}
		
		if(ACTION_DEFEND_INSERTS.equalsIgnoreCase(action) &&
				(StringUtils.isNotBlank(firstParm)) && 
				(StringUtils.isNotBlank(secondParm))){
			DefendInserts defendInserts = new DefendInserts();
			try {
				defendInserts.processFile(firstParm, secondParm);
			}catch(IOException ioe) {
				log.error(ioe);
				returnCode = -1;
			}
		} else {
			returnCode = displayUsage();
		}

		System.exit(returnCode);
	}
	
	
	protected static int displayUsage() {
		log.info("-- You must supply an action and parameters as follows --");
		log.info("Usage to generate defensive sql inserts/updates  : drive.bat defend-inserts input-file-name-with-inserts output-file-name-with-defended-inserts");
		return -1;
	}
	
	protected static void setByArguments(String[] args) {
		log.info("Entry - args length = " + args.length);
		for (int i = 0; i < args.length; i++) {
			switch (i) {
			case 0:
				action = args[i];
				log.info("Action = " + action);
				break;
			case 1:
				firstParm = args[i];
				log.info("First Parm = " + firstParm);
				break;
			case 2:
				secondParm = args[i];
				log.info("Second Parm = " + secondParm);
				break;
			default:
				break;
			}
		}
	}

}
