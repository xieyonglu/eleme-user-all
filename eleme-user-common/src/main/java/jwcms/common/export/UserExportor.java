package jwcms.common.export;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

class A {
	private String a;
	private String b;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
}

@Service
public class UserExportor extends Exportor<A> {

	@Override
	public String getFileName() {
		return "0点库存";
	}

	@Override
	public String getSheetName() {
		return "0点库存";
	}

	@Override
	public List<String> getKeys() {
		String[] keys = new String[] {
				"a",
				"b",
		};
		return Arrays.asList(keys);
	}

	@Override
	public List<String> getHeaders() {
		String[] headers = new String[] {
				"字段-A",
				"字段-B",
		};
		return Arrays.asList(headers);
	}

	@Override
	public Map<String, NumberFormat> getNumericalColumns() {
		Map<String, NumberFormat> columns = new LinkedHashMap<>();
		columns.put("a", new NumberFormat(NumberFormatType.WHOLE));
		columns.put("b", new NumberFormat(NumberFormatType.WHOLE));
		return columns;
	}
	
	@Override
	public Map<String, Object> convert(A item) {
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("a", item.getA());
		data.put("b", item.getB());
		
		return data;
	}
}
