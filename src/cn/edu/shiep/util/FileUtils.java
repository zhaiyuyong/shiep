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
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
		try {
			if (is != null)
				is.close();
			is = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	} 
	public static void closeOutputStream(OutputStream os) {
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
		try {
			if (os != null)
				os.close();
			os = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}	
	public static void closeFileReader(FileReader fr) {
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
		try {
			if (fr != null)
				fr.close();
			fr = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeBufferedReader(BufferedReader br) {
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
		try {
			if (br != null)
				br.close();
			br = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeReader(FileReader fr, BufferedReader br) {
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
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
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
		try {
			if (fw != null)
				fw.close();
			fw = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeBufferedWriter(BufferedWriter bw) {
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
		try {
			if (bw != null)
				bw.close();
			bw = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closePrintWriter(PrintWriter pw) {
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
		try {
			if (pw != null)
				pw.close();
			pw = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}

	public static void closeWriter(FileWriter fw, BufferedWriter bw) {
		// �˳�ǰ,һ��Ҫ���ļ������д���ر�
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
		// �˳�ǰ,���ļ���Ϊ��
		try {
			if (inFile != null)
  			inFile = null;
		} catch (Exception exx) {
			log.error(exx);
		}
	}




}
