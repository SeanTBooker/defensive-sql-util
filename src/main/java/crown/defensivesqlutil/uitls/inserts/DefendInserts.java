/**
 * 
 */
package crown.defensivesqlutil.uitls.inserts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crown.defensivesqlutil.uitls.FileUtil;

/**
 * @author dc2805
 *
 */
public class DefendInserts {
	
	protected static final Log LOG = LogFactory.getLog(DefendInserts.class);

	public static final Date CURRDATE_DATE = new Date();
	public static final String TOKEN_FROM_TABLE_NAME = "<" + CURRDATE_DATE + "_FROM_TABLE_NAME>";
	public static final String TOKEN_WHERE_COLUMN_NAME_KEY = "<" + CURRDATE_DATE + "_WHERE_COLUMN_NAME_KEY>";
	public static final String TOKEN_WHERE_COLUMN_NAME_VALUE = "<" + CURRDATE_DATE + "_WHERE_COLUMN_NAME_VALUE>";
	public static final String INSERT_CONTENT = "INSERT INTO";
	public static final String BEGIN_CONTENT = "DECLARE\n"+
    "CNT_V NUMBER(2) := 0;\n"+
	"BEGIN\n\n";
	public static final String INSERT_REPLACE_START_CONTENT = "SELECT COUNT(*)\n" + 
   "INTO CNT_V\n" +
   "FROM "+ TOKEN_FROM_TABLE_NAME + "\n" + 
   "WHERE "+ TOKEN_WHERE_COLUMN_NAME_KEY + " = "+ TOKEN_WHERE_COLUMN_NAME_VALUE + ";\n" +
    "\n"+
	"IF CNT_V = 0 THEN\n" +
	"	INSERT INTO";
	
	public static final String INSERT_REPLACE_END_BEFORE_SET_CONTENT = "\nELSE\n"+
	  "	UPDATE "+ TOKEN_FROM_TABLE_NAME + "\n";
	
	public static final String INSERT_REPLACE_END_AFTER_SET_CONTENT = 
	  "	WHERE "+ TOKEN_WHERE_COLUMN_NAME_KEY + " = "+ TOKEN_WHERE_COLUMN_NAME_VALUE + ";\n"+
	  "END IF;\n\n"+
	  "-- reset the counter\n"+
	  "CNT_V := 0;\n\n";
	
	public static final String END_CONTENT = "COMMIT;\n"+
			"END;\n"+
			"/\n";
	
	private int stat_recordCount = 0;
	
	public String seekAndReplace(final String lineToReplace) {
		if((StringUtils.isNotBlank(lineToReplace) && 
			lineToReplace.toUpperCase().startsWith(INSERT_CONTENT))) {
			final String seekLineToReplace = lineToReplace.toUpperCase();
			
			LOG.debug("lineToReplace: " + lineToReplace);
			
			StringBuffer replaceBuffer = new StringBuffer();
		
			replaceBuffer.append(INSERT_REPLACE_START_CONTENT);
			
			int idxOfInsert = seekLineToReplace.indexOf(INSERT_CONTENT) + INSERT_CONTENT.length();
			//Add the full Insert line
			replaceBuffer.append(lineToReplace.substring(idxOfInsert));
			replaceBuffer.append(";");
			
			//Wrap with Else..
			replaceBuffer.append(INSERT_REPLACE_END_BEFORE_SET_CONTENT);
			
			int idxColumnLeftParentheses = seekLineToReplace.indexOf("(");
			String extractedTableName = lineToReplace.substring(idxOfInsert, idxColumnLeftParentheses).trim();
			
			int idxColumnRightParentheses = seekLineToReplace.indexOf(")");
			String extractedColumnNames = lineToReplace.substring(idxColumnLeftParentheses+1, idxColumnRightParentheses);
			String[] extractedColumnNameArray = extractedColumnNames.split(",");
			String whereColumnNameKEY = extractedColumnNameArray[0];//Use the first column
			
			int idxOfValues = seekLineToReplace.indexOf("VALUES") + "VALUES".length();
			String valueSectionLineToReplace = lineToReplace.substring(idxOfValues);
			int idxValuesLeftParentheses = valueSectionLineToReplace.indexOf("(");
			
			int idxValuesRightParentheses = valueSectionLineToReplace.lastIndexOf(")");
			String extractedValueNames = valueSectionLineToReplace.substring(idxValuesLeftParentheses+1, idxValuesRightParentheses);
			String[] extractedValueNameArray = extractedValueNames.split(",");
			
			List<String> correctedDataValueNameList = new ArrayList<String>();
			String oracleFunctionStartInData = "";
			String oracleSingleQuoteStartInData = "";
			for(String currValue : extractedValueNameArray) {
				if(StringUtils.isNotBlank(currValue)) { 
					if(((StringUtils.isBlank(oracleFunctionStartInData) &&
							currValue.contains("(")) ||
							(StringUtils.isNotBlank(oracleFunctionStartInData))) &&
									(!currValue.contains(")"))){//Start of Oracle function
						oracleFunctionStartInData += currValue;
						oracleFunctionStartInData += ","; //Restore comma
					} else if(StringUtils.isNotBlank(oracleFunctionStartInData) &&
							currValue.contains(")")) {//End of Oracle function
						//Let's now add the data whole, end of function...
						oracleFunctionStartInData += currValue;
						correctedDataValueNameList.add(oracleFunctionStartInData);
						oracleFunctionStartInData = ""; //reset
					} else if(StringUtils.isBlank(oracleFunctionStartInData)) {
						if((StringUtils.isBlank(oracleSingleQuoteStartInData) &&
								(containsOddAmountOfSingleQuotes(currValue))) || 
								(StringUtils.isNotBlank(oracleSingleQuoteStartInData) &&
										(!currValue.contains("'")))){
							//Start of together
							oracleSingleQuoteStartInData += currValue;
							oracleSingleQuoteStartInData += ","; //Restore comma
						} else if(StringUtils.isNotBlank(oracleSingleQuoteStartInData) &&
								(containsOddAmountOfSingleQuotes(currValue))) {
							//End of together
							oracleSingleQuoteStartInData += currValue;
							correctedDataValueNameList.add(oracleSingleQuoteStartInData);
							oracleSingleQuoteStartInData = ""; //reset
						} else {
							//This data is good, just add it
							correctedDataValueNameList.add(currValue);
						}
					}
				}
			}
			
			String whereColumnNameVALUE = correctedDataValueNameList.get(0);
			
			//Generate SET SQL...
			int currIdx = 0;
			replaceBuffer.append("		SET ");
			for(String currExtractedColumnName : extractedColumnNameArray) {
				replaceBuffer.append(currExtractedColumnName);
				replaceBuffer.append(" = ");
				replaceBuffer.append(correctedDataValueNameList.get(currIdx));
				if(currIdx < (extractedColumnNameArray.length - 1)) {
					replaceBuffer.append(", ");
				}
				replaceBuffer.append(" \n");
				currIdx++;
			}
			
			replaceBuffer.append(INSERT_REPLACE_END_AFTER_SET_CONTENT);
			
			String replacementLine = replaceBuffer.toString(); 
			
			replacementLine = replacementLine.replaceAll(TOKEN_FROM_TABLE_NAME, extractedTableName);
			replacementLine = replacementLine.replaceAll(TOKEN_WHERE_COLUMN_NAME_KEY, whereColumnNameKEY);
			replacementLine = replacementLine.replaceAll(TOKEN_WHERE_COLUMN_NAME_VALUE, whereColumnNameVALUE);
			
			stat_recordCount++;
			LOG.info("Records processed: " + stat_recordCount);
			
			return replacementLine;
		}
		return null;
	}
	
	public void processFile(String inputFileName, String outputFileName) throws IOException {
		StopWatch onMyMark = new StopWatch();
		
		onMyMark.start();
		String fileContent = FileUtil.getInstance().readFile(inputFileName);
		
		String[] linesInFile = fileContent.split(";\n");
		
		StringBuffer lineOut = new StringBuffer();
		for(String lineIn : linesInFile) {
			String replacementLine = seekAndReplace(lineIn);
			if(StringUtils.isNotBlank(replacementLine)){
				lineOut.append(replacementLine);
			} else {
				//Just add the line to the output file
				lineOut.append(lineIn);
				lineOut.append(";\n");
			}
			//lineOut.append("\n");
		}
		
		if(StringUtils.isNotBlank(lineOut.toString())) {
			FileUtil.getInstance().writeFile(outputFileName, lineOut.toString());
		}	
		onMyMark.stop();
		
		LOG.info("Total Processing Time: " +  onMyMark.getTime() + " ms");
	}
	
	private int testStringContainsEvenAmountOfSingleQuotes(String currValue) {
		if((StringUtils.isBlank(currValue)) ||
			(!currValue.contains("'"))){
			return -1;
		} else {
			return (StringUtils.countMatches(currValue, "'") % 2);
		}
	}
	
	private boolean containsOddAmountOfSingleQuotes(String currValue) {
		return (testStringContainsEvenAmountOfSingleQuotes(currValue) > 0);
	}
}
