package crown.defensivesqlutil.uitls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This is a singleton class used to work with files and directories
 *
 * @author Sean Booker
 * @version 1.0
 */
public class FileUtil {
    private static FileUtil instance = new FileUtil();

    protected static final Log LOG = LogFactory.getLog(FileUtil.class);
	
    /**
	 * Private constructor, the FileUtil object can only be constructed by
	 * itself.
	 */
    private FileUtil() {
    }

    /**
     * Static Method:  getInstance
     * Creates a new FileUtil object if one is not already created.
     * If one has already been created, just return it.
     * @return an instance of FileUtil
     */
    public static FileUtil getInstance() {
        if (instance == null) instance = new FileUtil();
        return instance;
    }

    /**
     * 
     * @param fileName
     * @return the file contents
     * @throws IOException
     */
    public String readFile(String fileName) throws IOException {
        return readFile(new File(fileName));
    }

    /**
     * 
     * @param sysFile
     * @return the file contents
     * @throws IOException
     */
    public String readFile(File sysFile) throws IOException {
        StringBuffer fileContents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(sysFile));
            String line = "";
            while ( (line = input.readLine()) != null) {
                if (line.trim().length() > 0) {
                    fileContents.append(line + "\n");
                }
            }
        }finally {
            try {
                if (input != null) {
                    input.close();
                    input = null;
                }
            }catch (Exception e) {
            }
        }
        return fileContents.toString();
    }

    /**
     * 
     * @param fileName
     * @param fileContents
     * @return the File object
     * @throws IOException
     */
    public File writeFile(String fileName, String fileContents)
            throws IOException {
        FileOutputStream output = null;
        File sysFile = null;
        try {
            sysFile = new File(fileName);
            output = new FileOutputStream(sysFile);
            output.write(fileContents.getBytes());
        }finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                    output = null;
                }
            }catch (Exception e) {
            }
        }
        return sysFile;
    }   
}
