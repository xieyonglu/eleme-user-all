//package jwcms.common.export;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CreationHelper;
//import org.apache.poi.ss.usermodel.Font;
//
//import jwcms.common.util.StringUtils;
//
///**
// * <h1>Excel导出工具类</h1>
// * 
// */
//public class ExcelUtils {
//
//	private static final Log LOGGER = LogFactory.getLog(ExcelUtils.class);
//
//	private static final List<String> DEFAULT_GREY_COLUMNS = new LinkedList<>();
//
//	private ExcelUtils() {
//	}
//
//	public static HSSFWorkbook createWorkBook() {
//		return new HSSFWorkbook();
//	}
//
//	public static HSSFWorkbook createWorkBook(String sheetName,
//			Integer headRows, Map<Integer, List<String>> cellsMap,
//			List<String> keys, Integer startRowIndex,
//			List<Map<String, String>> mapList) {
//		HSSFWorkbook wb = createWorkBook();
//		createWorkBookStyle(wb);
//		HSSFSheet sheet = createSheet(wb, sheetName);
//		createHeads(wb, sheet, headRows, cellsMap);
//		createDatas(wb, sheet, keys, startRowIndex, mapList);
//		return wb;
//	}
//
//	public static HSSFWorkbook createWorkBook(String sheetName,
//			List<String> headers, List<String> keys,
//			List<Map<String, String>> mapList, List<String> greyColumns) {
//		Map<Integer, List<String>> cellsMap = new LinkedHashMap<>();
//		cellsMap.put(0, headers);
//		HSSFWorkbook wb = createWorkBook();
//		createWorkBookStyle(wb);
//		HSSFSheet sheet = createSheet(wb, sheetName);
//		createHeads(wb, sheet, 1, cellsMap);
//		createDatas(wb, sheet, keys, 0, mapList, greyColumns);
//		return wb;
//	}
//	
//	public static HSSFWorkbook createWorkBook(String sheetName,
//			List<String> headers, List<String> keys,
//			List<Map<String, String>> mapList) {
//		return createWorkBook(sheetName, headers, keys, mapList,
//				DEFAULT_GREY_COLUMNS);
//	}
////	
////	public static void createDatas(HSSFWorkbook wb, HSSFSheet sheet, List<String> keys, Integer startRow,
////			List<Map<String, String>> mapList, List<String> greyColumns) {
////		CellStyle tdCss = wb.createCellStyle();
////		tdCss.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
////		tdCss.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
////		tdCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
////		tdCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
////		getCellStyle(tdCss, (short) 2);
////
////		long time1 = System.currentTimeMillis();
////		for (int i = 0; i < mapList.size(); i++) {
////			HSSFRow row = sheet.createRow(i + 1 + startRow);
////			Map<String, String> map = mapList.get(i);
////			for (int j = 0; j < keys.size(); j++) {
////				String key = keys.get(j);
////				HSSFCell cell = row.createCell(j);
////
////				if (greyColumns.contains(key)) {
////					cell.setCellStyle(tdCss);
////					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
////				}
////
////				Object value = map.get(key);
////				String val = value == null ? "" : value.toString();
////				if (StringUtil.isEmpty(val)) {
////					continue;
////				}
////				cell.setCellValue(val);
////			}
////		}
////		long time2 = System.currentTimeMillis();
////		LOGGER.info("==========ExcelUtils -> createDatas: " + (time2 - time1) + "ms");
////	}
//	
//	public static void createDatas(HSSFWorkbook wb, HSSFSheet sheet,List<String> keys, Integer startRow,
//			List<Map<String, String>> orderData, 
//			List<String> greyColumns) {
//		CellStyle tdCss = wb.createCellStyle();
//		CreationHelper createHelper = wb.getCreationHelper(); 
//		tdCss.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		tdCss.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//		tdCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		tdCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		getCellStyle(tdCss, (short) 2);
//
//		long time1 = System.currentTimeMillis();
//		for (int i = 0; i < orderData.size(); i++) {
//			HSSFRow row = sheet.createRow(i + 1 + startRow);
//			Map<String, String> map = orderData.get(i);
//			for (int j = 0; j < keys.size(); j++) {
//				String key = keys.get(j);
//				HSSFCell cell = row.createCell(j);
//				
//				if (greyColumns.contains(key)) {
//					cell.setCellStyle(tdCss);
//					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//				}
//				
//				Object value = map.get(key);
//				String val = value == null ? "" : value.toString();
//				if (StringUtils.isEmpty(val))
//					continue;
//
//				List<String> numberList = Arrays.asList(new String[]{"planInboundQuantity","actualInboundQuantity"});
//				List<String> dateList = Arrays.asList(new String[]{"createAt","planInboundTime", "actualInboundTime"});
//				
//				if (dateList.contains(key) && !val.isEmpty()) {
//					CellStyle cellStyle = wb.createCellStyle();
//					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//					cell.setCellStyle(cellStyle);
//					Date date = new Date();
//					try  
//					{  
//					    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//					    date = sdf.parse(val);  
//					}  
//					catch (ParseException e)  
//					{  
//					    System.out.println(e.getMessage());  
//					} 
//					cell.setCellValue(date);
//				} else if (numberList.contains(key) && !val.isEmpty()) {
//					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//					cell.setCellValue(Double.valueOf(map.get(key)));
//				} else {
//					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//					cell.setCellValue(val.toString());
//				}
//			}
//		}
//
//		long time2 = System.currentTimeMillis();
//		LOGGER.info("==========ExcelUtils -> createDatas: " + (time2 - time1)
//				+ "ms");
//	}
//
//	public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
//		return wb.createSheet(sheetName);
//	}
//
//	public static CellStyle createWorkBookStyle(HSSFWorkbook wb) {
//		CellStyle cellStyle = wb.createCellStyle();
//		cellStyle.setWrapText(true);
//		return cellStyle;
//	}
//
//	public static void createDatas(HSSFWorkbook wb, HSSFSheet sheet,
//			List<String> keys, Integer startRow,
//			List<Map<String, String>> mapList) {
//		createDatas(wb, sheet, keys, startRow, mapList, DEFAULT_GREY_COLUMNS);
//	}
//
//	public static void createHeads(HSSFWorkbook wb, HSSFSheet sheet,
//			Integer rows, Map<Integer, List<String>> cellsMap) {
//		// 上级表头
//		CellStyle thCssHead = wb.createCellStyle();
//		Font headFont = wb.createFont();
//		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		headFont.setFontHeightInPoints((short) 11);
//		thCssHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		thCssHead.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//		thCssHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		thCssHead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		getCellStyle(thCssHead, (short) 2);
//		thCssHead.setFont(headFont);
//		for (int i = 0; i < rows; i++) {
//			HSSFRow row = sheet.createRow(i);
//			List<String> cells = cellsMap.get(i);
//			for (int j = 0; j < cells.size(); j++) {
//				String cellVal = cells.get(j);
//				HSSFCell cell = row.createCell(j);
//				cell.setCellStyle(thCssHead);
//				cell.setCellValue(cellVal);
//				sheet.autoSizeColumn(i * j, true);
//			}
//		}
//	}
//
//	public static CellStyle getCellStyle(CellStyle style, short type) {
//		style.setBorderBottom(type);// 下边框
//		style.setBorderLeft(type);// 左边框
//		style.setBorderRight(type);// 右边框
//		style.setBorderTop(type);// 上边框
//		return style;
//	}
//
//	public static List<Map<String, String>> readExcel(InputStream ins,
//			int columnRowIndex, int dataStartRowIndex, int cellCount) {
//		List<Map<String, String>> mapList = new LinkedList<>();
//		try {
//			POIFSFileSystem fs = new POIFSFileSystem(ins);
//			HSSFWorkbook wb = new HSSFWorkbook(fs);
//			HSSFSheet sheet = wb.getSheetAt(0);
//			HSSFRow columnRow = sheet.getRow(columnRowIndex);
//			int cellNum = columnRow.getPhysicalNumberOfCells();
//			Map<Integer, String> columnMap = new LinkedHashMap<>();
//			for (int i = 0; i < cellCount; i++) {
//				HSSFCell cell = columnRow.getCell(i);
//				String value = getStringCellValue(cell);
//				columnMap.put(i, value);
//			}
//			LOGGER.info("列总数为： " + cellCount + ", 实际列总为：" + cellNum);
//			int rowNum = sheet.getLastRowNum();
//			LOGGER.info("总行数：" + (rowNum + 1));
//			HSSFRow dataRow;
//			for (int i = dataStartRowIndex; i <= rowNum; i++) {
//				Map<String, String> map = new LinkedHashMap<>();
//				dataRow = sheet.getRow(i);
//				Set<Integer> keys = columnMap.keySet();
//				for (Integer index : keys) {
//					HSSFCell cell = dataRow.getCell(index);
//					String value = getStringCellValue(cell);
//					map.put(columnMap.get(index), value);
//				}
//				mapList.add(map);
//			}
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//		return mapList;
//	}
//
//	private static String getStringCellValue(HSSFCell cell) {
//		if (cell == null) {
//			return "";
//		}
//		String strCell;
//		switch (cell.getCellType()) {
//		case HSSFCell.CELL_TYPE_STRING:
//			strCell = cell.getStringCellValue();
//			break;
//		case HSSFCell.CELL_TYPE_NUMERIC:
//			strCell = String.valueOf((long) cell.getNumericCellValue());
//			break;
//		case HSSFCell.CELL_TYPE_BOOLEAN:
//			strCell = String.valueOf(cell.getBooleanCellValue());
//			break;
//		default:
//			strCell = "";
//			break;
//		}
//		if (StringUtils.isEmpty(strCell)) {
//			return "";
//		}
//		return strCell;
//	}
//
//	public static void download(String name, HSSFWorkbook wb,
//			HttpServletResponse response) {
//		try {
//
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//			wb.write(output);
//			byte[] bytes = output.toByteArray();
//			String fileName = name + ".xls";
//			fileName = new String(fileName.getBytes(), "ISO8859-1");
//
//			response.setContentType("application/x-msdownload");
//			response.setContentLength(bytes.length);
//			response.setHeader("Content-Disposition", "attachment;filename="
//					+ fileName);
//			response.getOutputStream().write(bytes);
//
//			output.flush();
//			output.close();
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//	}
//
//}
