package jwcms.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FileUtils {

	private static final Log LOGGER = LogFactory.getLog(FileUtils.class);

	public static boolean append(String fileName, String strContent) {
		BufferedWriter bWriter = null;
		try {
			File sysFile = new File(fileName);
			if (!sysFile.getParentFile().exists()) {
				sysFile.getParentFile().mkdirs();
			}
			if (!sysFile.exists()) {
				sysFile.createNewFile(); // 创建文件
			}
			FileWriter wf = new FileWriter(sysFile, true);
			bWriter = new BufferedWriter(wf);
			bWriter.write(new String(strContent.getBytes(), "UTF-8"));
			wf.close();
			return true;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		} finally {
			if (bWriter != null) {
				try {
					bWriter.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
	}

	public static void writeNewFile(String fileName, String content) throws Exception {
		BufferedWriter bWriter = null;
		try {
			File sysFile = new File(fileName);
			if (!sysFile.getParentFile().exists()) {
				sysFile.getParentFile().mkdirs();
			}
			if (!sysFile.exists()) {
				sysFile.createNewFile(); // 创建文件
			}
			FileWriter fw = new FileWriter(sysFile);
			bWriter = new BufferedWriter(fw);
			bWriter.write(new String(content.getBytes(), "UTF-8"));
			fw.close();
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		} finally {
			if (bWriter != null) {
				try {
					bWriter.close();
				} catch (IOException e) {
					LOGGER.error(e);
					throw e;
				}
			}
		}
	}

	public static File mkdirs(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	}

	/**
	 * 将文件转换为字节数组
	 * 
	 * @param f
	 * @return
	 */
	public static byte[] toBytes(File f) {
		if (f == null) {
			return null;
		}
		try (FileInputStream stream = new FileInputStream(f)) {
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1) {
				out.write(b, 0, n);
			}
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}

	public static File toFile(byte[] b, String outputFile) {
		File ret = new File(outputFile);
		BufferedOutputStream stream = null;
		try (FileOutputStream fstream = new FileOutputStream(ret)) {
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
		return ret;
	}

	public static List<String> readFileByLines(String filePath) throws IOException {
		File file = new File(filePath);
		return readFileByLines(file);
	}

	public static List<String> readFileByLines(File file) throws IOException {
		List<String> list = new LinkedList<>();
		if (!file.exists() || file.isDirectory()) {
			LOGGER.error("文件不存在path=[" + file.getAbsolutePath() + "]或是个目录");
			return list;
		}
		BufferedReader reader = null;
		try (FileReader fr = new FileReader(file)) {
			reader = new BufferedReader(fr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			LOGGER.error(e);
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LOGGER.error(e);
					throw e;
				}
			}
		}
		return list;
	}

	public static String readContent(String filePath) throws IOException {
		return appendContent(filePath, "");
	}

	public static String appendContent(String filePath, String interval) throws IOException {
		File file = new File(filePath);
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try (FileReader fr = new FileReader(file)) {
			reader = new BufferedReader(fr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				sb.append(tempString).append(interval);
			}
			reader.close();
		} catch (IOException e) {
			LOGGER.error(e);
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
		return sb.toString();
	}

}
