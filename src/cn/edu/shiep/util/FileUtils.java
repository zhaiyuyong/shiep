package cn.edu.shiep.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

/**
 * @author huangxq
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FileUtils {
	private static Logger log = Logger.getLogger(FileUtils.class.getName());

	public FileUtils() {
	}
	public static void closeInputStream(InputStream is) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (is != null)
				is.close();
			is = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	} 
	public static void closeOutputStream(OutputStream os) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (os != null)
				os.close();
			os = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}	
	public static void closeFileReader(FileReader fr) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (fr != null)
				fr.close();
			fr = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeBufferedReader(BufferedReader br) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (br != null)
				br.close();
			br = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeReader(FileReader fr, BufferedReader br) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (fr != null)
				fr.close();
			fr = null;
		} catch (Exception exx) {
			log.error(exx);
		}
		try {
			if (br != null)
				br.close();
			br = null;
		} catch (Exception exx) {
			log.error(exx);
		}		
	}

	public static void closeFileWriter(FileWriter fw) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (fw != null)
				fw.close();
			fw = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeBufferedWriter(BufferedWriter bw) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (bw != null)
				bw.close();
			bw = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closePrintWriter(PrintWriter pw) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (pw != null)
				pw.close();
			pw = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeWriter(FileWriter fw, BufferedWriter bw) {
		// 退出前,一定要将文件处理读写器关闭
		try {
			if (fw != null)
				fw.close();
			fw = null;
		} catch (Exception exx) {
			log.error(exx);
		}
		try {
			if (bw != null)
				bw.close();
			bw = null;
		} catch (Exception exx) {
			log.error(exx);
		}		
	}

	public static void closeFile(File inFile) {
		// 退出前,将文件置为空
		try {
			if (inFile != null)
  			inFile = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}




}
