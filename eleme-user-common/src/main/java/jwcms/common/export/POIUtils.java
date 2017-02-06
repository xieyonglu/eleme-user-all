/** 
 * =============================================================
 * Copyright 2016 ELE.ME All Rights Reserved
 * 公司名称: 拉扎斯网络科技（上海）有限公司
 * 系统名称: 有菜
 * 子系统名: 物流中心
 * 模块名称: 工具类
 * Class名: ExcelUtils
 * 概要: Excel导出工具类
 * 版本			日期				作者						备注 
 * 1.0.0		2016年5月14日		李刚 (gang.li@ele.me)	初次做成
 * =============================================================
 */
package jwcms.common.export;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;

/**
 * <h1>Excel导出工具类</h1>
 */
public class POIUtils {
	
	private static final Log LOGGER = LogFactory.getLog(POIUtils.class);

	public static final String DEFAULT_INTEGER_FORMAT = "##0";
	
	public static final String DEFAULT_DOUBLE_FORMAT = "###0.00";
	
	private POIUtils() {}
	
	public static HSSFWorkbook createWorkBook() {
		return new HSSFWorkbook();
	}
	
	public static <T> HSSFWorkbook createWorkBook(String sheetName, List<Map<String, Object>> mapList, Exportor<T> exporter) {
		Map<Integer, List<String>> cellsMap = new LinkedHashMap<>();
		cellsMap.put(0, exporter.getHeaders());
		HSSFWorkbook wb = createWorkBook();
		createWorkBookStyle(wb);
		HSSFSheet sheet = createSheet(wb, sheetName);
		createHeads(wb, sheet, 1, cellsMap);
		createDatas(wb, sheet, 0, mapList, exporter);
		return wb;
	}
	

	private static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
		return wb.createSheet(sheetName);
	}

	private static CellStyle createWorkBookStyle(HSSFWorkbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setWrapText(true);
		return cellStyle;
	}

	private static <T> void createDatas(HSSFWorkbook wb, HSSFSheet sheet, 
			Integer startRow, List<Map<String, Object>> mapList, Exportor<T> exporter) {
		
		CellStyle tdCss = wb.createCellStyle();
		tdCss.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		tdCss.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		tdCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		tdCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		getCellStyle(tdCss, (short) 2);
		
		CreationHelper createHelper = wb.getCreationHelper();  
		Map<String, HSSFCellStyle> cellStyles = new LinkedHashMap<>();
		for (Entry<String, NumberFormat> entry : exporter.getNumericalColumns().entrySet()) {
			NumberFormat format = entry.getValue();
			HSSFCellStyle css = wb.createCellStyle();
			css.setDataFormat(createHelper.createDataFormat().getFormat(format.getPattern()));
			cellStyles.put(entry.getKey(), css);
		}
		
		HSSFCellStyle dateCellStyle = wb.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < mapList.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1 + startRow);
			Map<String, Object> map = mapList.get(i);
			for (int j = 0; j < exporter.getKeys().size(); j++) {
				String key = exporter.getKeys().get(j);
				HSSFCell cell = row.createCell(j);
				
				Object value = map.get(key);
				if (value == null) {
					continue;
				}
				
				if (exporter.getGreyColumns().contains(key)) {
					cell.setCellStyle(tdCss);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				} else if (cellStyles.containsKey(key)) {
					cell.setCellStyle(cellStyles.get(key));
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if (value instanceof Long
							|| value instanceof Integer) {
						cell.setCellValue(Long.valueOf(value.toString()));
						continue;
					} else if (value instanceof Float || value instanceof Double || value instanceof BigDecimal) {
						cell.setCellValue(Double.valueOf(value.toString()));
						continue;
					}
				} else if (value instanceof Date) {
					cell.setCellStyle(dateCellStyle);
					cell.setCellValue((Date) value);
					continue;
				}

				cell.setCellValue(value.toString());
			}
		}
		long time2 = System.currentTimeMillis();
		LOGGER.info("==========ExcelUtils -> createDatas: " + (time2 - time1) + "ms");
	}
	
	private static void createHeads(HSSFWorkbook wb, HSSFSheet sheet, Integer rows, Map<Integer, List<String>> cellsMap) {
		// 上级表头
		CellStyle thCssHead = wb.createCellStyle();
		Font headFont = wb.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setFontHeightInPoints((short) 11);
		thCssHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		thCssHead.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		thCssHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		thCssHead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		getCellStyle(thCssHead, (short) 2);
		thCssHead.setFont(headFont);
		for (int i = 0; i < rows; i++) {
			HSSFRow row = sheet.createRow(i);
			List<String> cells = cellsMap.get(i);
			for (int j = 0; j < cells.size(); j++) {
				String cellVal = cells.get(j);
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(thCssHead);
				cell.setCellValue(cellVal);
				sheet.autoSizeColumn(i * j, true);
			}
		}
	}

	private static CellStyle getCellStyle(CellStyle style, short type) {
		style.setBorderBottom(type);// 下边框
		style.setBorderLeft(type);// 左边框
		style.setBorderRight(type);// 右边框
		style.setBorderTop(type);// 上边框
		return style;
	}

	public static void download(String name, HSSFWorkbook wb, HttpServletResponse response) {
		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			wb.write(output);
			byte[] bytes = output.toByteArray();
			String fileName = name + ".xls";
			fileName = new String(fileName.getBytes(), "ISO8859-1");
			
			response.setContentType("application/x-msdownload");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.getOutputStream().write(bytes);

			output.flush();
			output.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
}
