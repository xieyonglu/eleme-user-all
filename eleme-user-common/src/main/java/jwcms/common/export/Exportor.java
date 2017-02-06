package jwcms.common.export;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <h1>抽象导出类</h1>
 */
public abstract class Exportor<T> {
	
	private static final Log LOGGER = LogFactory.getLog(Exportor.class); 
	/**
	 * Excel文件名称
	 * @return
	 */
	public abstract String getFileName() ;
	
	/**
	 * sheet名称
	 * @return
	 */
	public abstract String getSheetName() ;
	
	/**
	 * 数据键值集合
	 * @return
	 */
	public abstract List<String> getKeys() ;
	
	/**
	 * 表头名称集合
	 * @return
	 */
	public abstract List<String> getHeaders() ;
	
	/**
	 * 数据转换成Map<String, String>
	 * @param item 原始数据对象
	 * @return
	 */
	public abstract Map<String, Object> convert(T item);
	
	/**
	 * 灰色列下标
	 * @return
	 */
	public List<String> getGreyColumns() {
		return new LinkedList<>();
	}
	
	/**
	 * 数值字段
	 * Map
	 * KEY: 字段名
	 * VALUE: 格式化
	 * @return
	 */
	public Map<String, NumberFormat> getNumericalColumns() {
		return new LinkedHashMap<>();
	}
	
	/**
	 * 导出Excel
	 * @param list 原始数据集合
	 * @param response http响应对象
	 */
	public void doExport(List<T> list, HttpServletResponse response) {
		long time1 = System.currentTimeMillis();
		HSSFWorkbook wb = POIUtils.createWorkBook(getSheetName(), getDatas(list), this);
		long time2 = System.currentTimeMillis();
		LOGGER.info("==========createHSSFWorkbook -> ExcelUtils.doExport: " + (time2 - time1) + "ms");
		ExcelUtils.download(getFileName(), wb, response);

	}

	/**
	 * 将原始数据对象集合转换成List<Map<Stringn, String>>
	 * @param list 原始数据对象
	 * @return
	 */
	public List<Map<String, Object>> getDatas(List<T> list) {
		List<Map<String, Object>> datas = new LinkedList<>();
		for (T item : list) {
			datas.add(convert(item));
		}
		return datas;
	}
	
	/**
	 * 创建一个空的Excel对象
	 * @return
	 */
	public HSSFWorkbook createWorkBook() {
		return ExcelUtils.createWorkBook();
	}
	
//	/**
//	 * 拼接Excel Sheet
//	 * @param book Excel对象
//	 * @param sheetName Sheet名称
//	 * @param list 原始数据对象集合
//	 */
//	public void appendSheet(HSSFWorkbook book, String sheetName, List<T> list) {
//		HSSFSheet sheet = ExcelUtils.createSheet(book, sheetName);
//		Map<Integer, List<String>> cellsMap = new LinkedHashMap<>();
//		List<String> headerList = getHeaders();
//		cellsMap.put(0, headerList);
//		
//		long time1 = System.currentTimeMillis();
//		ExcelUtils.createHeads(book, sheet, 1, cellsMap);
//		long time2 = System.currentTimeMillis();
//		LOGGER.info("==========createHSSFWorkbook -> ExcelUtils.createHeads: " + (time2 - time1) + "ms");
//
//		long time3 = System.currentTimeMillis();
//		List<Map<String, Object>> orderData = getDatas(list);
//		long time4 = System.currentTimeMillis();
//		LOGGER.info("==========createHSSFWorkbook -> getDatas: " + (time4 - time3) + "ms");
//		
//		long time5 = System.currentTimeMillis();
//		POIUtils.createDatas(book, sheet, getKeys(), 0, orderData, getGreyColumns(), getNumericalColumns());
//		long time6 = System.currentTimeMillis();
//		LOGGER.info("==========createHSSFWorkbook -> createDatas: " + (time6 - time5) + "ms");
//	}
	
//	/**
//	 * <pre>
//	 * 若对象为NULL转换为空字符串
//	 * 若对象不为NULL将对象转换成字符串
//	 * </pre>
//	 * @param obj 对象
//	 * @return
//	 */
//	protected String date2str(Date date) {
//		if (date == null) {
//			return "";
//		}
//		try {
//			return DateUtils.format(date, DateConstants.FORMAT01);
//		} catch (Exception e) {
//			LOGGER.error(e);
//			return "";
//		}
//	}
	
}

