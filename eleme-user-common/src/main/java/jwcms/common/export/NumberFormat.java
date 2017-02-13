//package jwcms.common.export;
//
///**
// * <h1></h1>
// */
//public class NumberFormat {
//	private NumberFormatType formatType;
//	private String pattern;
//	public NumberFormat(NumberFormatType formatType) {
//		this.formatType = formatType;
//		if (formatType.equals(NumberFormatType.DECIMAL)) {
//			this.pattern = POIUtils.DEFAULT_DOUBLE_FORMAT;
//		} else {
//			this.pattern = POIUtils.DEFAULT_INTEGER_FORMAT;
//		} 
//	}
//	public NumberFormat(NumberFormatType formatType, String pattern) {
//		this.formatType = formatType;
//		this.pattern = pattern;
//	}
//	public NumberFormatType getFormatType() {
//		return formatType;
//	}
//	public void setFormatType(NumberFormatType formatType) {
//		this.formatType = formatType;
//	}
//	public String getPattern() {
//		return pattern;
//	}
//	public void setPattern(String pattern) {
//		this.pattern = pattern;
//	}
//}
