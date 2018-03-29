package com.example.demo.util;

//import com.alibaba.fastjson.JSON;
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 基础工具包 ClassName: BaseUtils <br/>
 * date: 2017年8月18日 下午3:48:35 <br/>
 * 
 * @author gaoshuang
 * @version
 */
public class BaseUtils {
	
	private static Logger logger = LoggerFactory.getLogger(BaseUtils.class);
	
	// top_sing
	public static String sign(String parameter, String secret) throws Exception {
		// 对参数+密钥做MD5运算
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] digest = md.digest((parameter + secret).getBytes("utf-8"));
		// 对运算结果做BASE64运算并加密
		BASE64Encoder encode = new BASE64Encoder();
		return encode.encode(digest);
	}

	/**
	 * 生成md5
	 */
	public static String md5(String param) {
		String result = "";
		if (param == null || "".equals(param))
			return result;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = byte2hex(md.digest(param.getBytes("utf-8")));
		} catch (Exception e) {
			throw new RuntimeException("md5 error !");
		}
		return result;
	}

	/**
	 * 新的md5签名，首尾放secret。
	 * 
	 * @param secret
	 *            分配给您的APP_SECRET
	 */
	public static String md5Signature(TreeMap<String, String> params, String secret) {
		String result = null;
		StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));
		if (orgin == null)
			return result;
		orgin.append(secret);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));
		} catch (Exception e) {
			throw new RuntimeException("sign error !");
		}
		return result;
	}

	/**
	 * 添加参数的封装方法
	 */
	private static StringBuffer getBeforeSign(TreeMap<String, String> params, StringBuffer orgin) {
		if (params == null)
			return null;
		Map<String, String> treeMap = new TreeMap<String, String>();
		treeMap.putAll(params);
		Iterator<String> iter = treeMap.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			orgin.append(name).append(params.get(name));
		}
		return orgin;
	}

	/**
	 * 二进制转字符串
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	/**
	 * WEB所在目录
	 * 
	 * @param object
	 * @return
	 */
	public static String getPath(Object object) {
		String path = object.getClass().getClassLoader().getResource("").getPath().replace("%20", " ");
		return path.substring(1, path.indexOf("WEB-INF"));
	}

	/**
	 * 字符转码 转utf-8
	 * 
	 * @param value
	 * @return
	 */
	public static String utf8Encode(String value) {
		String result = null;
		if (value != null) {
			try {
				result = new String(value.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 自发码加密串
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String before8Md5M(String src) throws Exception {
		return md5(src + src.length()).substring(8, 16) + "M";
	}

	/**
	 * 生成sha1
	 */
	public static String sha1(String param) {
		String result = "";
		if (param == null || "".equals(param))
			return result;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			result = byte2hex(md.digest(param.getBytes("utf-8"))).toLowerCase();
		} catch (Exception e) {
			throw new RuntimeException("md5 error !");
		}
		return result;
	}

	/**
	 * 字符转码 utf-8 转 IOS
	 * 
	 * @param s
	 * @return
	 */
	public static String utf8ToIso(String s) {
		String ret = "";
		try {
			ret = new String(s.getBytes("UTF-8"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * 格式化金额
	 * 
	 * @param ptn
	 * @param money
	 * @return ",###", 31415926 --> 31,415,926
	 */
	public static String formatMoney(String ptn, double money) {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern(ptn);
		return df.format(money);
	}

	/**
	 * 提出Map值到另一map
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> reqMapToMap(Map<?, ?> map) {
		Map<String, String> tmpRstMap = new HashMap<String, String>();
		Set<?> tmpKeySet = map.keySet();
		for (Iterator<?> tmpIt = tmpKeySet.iterator(); tmpIt.hasNext();) {
			Object tmpKey = tmpIt.next();
			Object tmpVal = map.get(tmpKey);
			if (tmpVal instanceof Object[]) {
				tmpVal = ((Object[]) tmpVal)[0];
			}
			tmpRstMap.put(tmpKey.toString(), tmpVal.toString());
		}

		return tmpRstMap;
	}

	public static Date setHMS(Date date, int h, int m, int s) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, h);
		c.set(Calendar.MINUTE, m);
		c.set(Calendar.SECOND, s);

		return c.getTime();
	}

	/**
	 * 指定长度剪切字符
	 * 
	 * @param source
	 * @param maxLen
	 * @return
	 */
	public static String cutString(String source, int maxLen) {
		if (source != null && maxLen >= 0) {
			source = source.length() > maxLen ? source.substring(0, maxLen) : source;
		}

		return source;
	}

	/**
	 * 指定长度 不够前面补0 100 -->> 00100
	 * 
	 * @param num
	 * @param maxLen
	 * @return
	 */
	public static String patchZero(Long num, int maxLen) {
		num = num == null ? 0L : num;
		StringBuffer sb = new StringBuffer(num.toString());
		int len = maxLen - sb.length();
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				sb.insert(0, "0");
			}
		}

		return sb.toString();
	}

	/**
	 * 判断double类型值是否int
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isInt(double value) {
		return (value % 1d) == 0d;
	}

	/**
	 * 判断double类型值是否int,并返回
	 * 
	 * @return
	 */
	public static String getNumberStr(double d) {
		int i = (int) d;
		if (i == d) {
			return String.valueOf(i);
		} else {
			return String.valueOf(d);
		}
	}

	/**
	 * 判断参数是不是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	 * mapToObject:map 转对象 class. <br/>
	 * 
	 * @author gaoshuang
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
		if (map.isEmpty()) {
			return null;
		}
		//return JSON.parseObject(JSON.toJSONString(map), beanClass);
		return null;
	}

	/**
	 * 
	 * objectToMap:对象转MAP. <br/>
	 * 
	 * @author gaoshuang
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		//return JSON.parseObject(JSON.toJSONString(obj), Map.class);
		return null;
	}


	/**
	 * 
	 * isEmptyObject:集合对象不为null 不为空判断. <br/>
	 * 
	 * @author gaoshuang
	 * @param obj
	 *            集合对象不为null 不为空判断
	 * @return
	 */
	public static boolean isEmptyObject(Object obj) {
		if (obj == null || obj.toString().length() == 0 || 
				obj.toString().equals("null") ||
				obj.toString().equals("{}")||
				obj.toString().equals("[]")) {
			return true;
		}
		return false;
	}

	/**
	 * 转换数字个数；追加小数点；
	 * 使用场景：产品的年化收益；15 ==》 15.00；用于页面展示的格式；
	 */
	public static DecimalFormat dfFixed2Digit = new DecimalFormat("0.00");
	
	/**
	 * 
	 * isEmptyArray:数组是否为空 <br/>  
	 *  
	 * @author gaoshuang  
	 * @param obj
	 * @return
	 */
	public static boolean isEmptyArray(Object[] obj) {
		if (obj == null || obj.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 对以分隔符来作为间隔拼装字符串的.进行分割.转换为数组;
	 * 
	 * @param strs
	 *            以分隔符进行拼接的字符串,如 abc,cdd,fff,ddd
	 * @param separated
	 *            拼装的分隔符;
	 * @return String[]
	 */
	public static String[] splitString(String str, String separated) {
		String[] strs = null;
		if (!isEmptyObject(str)) {
			if (str.endsWith(separated)) {
				str = str.substring(0, str.length() - 1);
			}
			strs = str.split(separated);
		}
		return strs;
	}

	/**
	 * 把一个数组转化成字符串,用单引号括起来"'",多个之间用逗号隔开","如 '1','2'
	 * 
	 * @param objs
	 *            Object[] objs 要转换的数组
	 * @return String[]
	 */
	public static String packString(Object[] objs) {
		String strs = "";
		if (!isEmptyObject(objs)) {
			for (Object obj : objs) {
				strs += "'" + obj + "',";
			}
			if (strs.endsWith(",")) {
				strs = strs.substring(0, strs.length() - 1);
			}
		}
		return strs;
	}


	/**
	 * 把大的数字用中文的单位来替代<br>
	 * 返回的中文单位有:<b>万</b>、<b>亿</b> 不足<b>万</b>的,直接返回原数据;
	 * 
	 * @param num1
	 *            数值
	 * @return 中文单位的表式
	 */
	public static String getChineseNumber(Double num1) {
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		if (num1 >= 100000000) {
			return (format.format(((num1 / 100000000))) + "亿");
		} else if (num1 >= 10000000) {
			return (format.format((num1 / 10000000)) + "千万");
		} else if (num1 >= 1000000) {
			return (format.format((num1 / 1000000)) + "百万");
		} else if (num1 >= 100000) {
			return (format.format((num1 / 100000)) + "十万");
		} else if (num1 >= 10000) {
			return (format.format((num1 / 10000)) + "万");
		} else {
			return (num1.intValue() + "");
		}
	}

	/**
	 * 返回JSON格式的字符串;<br>
	 * 一般与getJSONString()方法搭配使用;先通过getJSONString()获得JSON格式的字符串,把返回的结果传进此方法调用输出;
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param json
	 *            JSON格式的字符串;
	 * @throws IOException
	 */
	public static void printJSON(HttpServletResponse response, String json) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control", "No-Cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/x-json;charset=UTF-8");
		// json = json.replace("'", "\"");
		PrintWriter print = response.getWriter();
		print.print(json);
	}

	/**
	 * 验证密码是否一致
	 * 
	 * @param password
	 *            密码1
	 * @param pwd
	 *            密码2
	 * @return
	 */
	public static boolean pwdIsUnanimous(String userName, String password, String pwd) {
		return pwd.equals(password);
	}
	
	/**
	 * 
	 * toString:通过反射对对象属性toString <br/>  
	 *  
	 * @author gaoshuang  
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {

		if(BaseUtils.isEmptyObject(obj)){
			return null;
		}
		//return ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
		return null;
	}
	 
	 /**
		 * 把字符串中间用*号代替; 字符串长度不足4位的,后面追加4个*号
		 * 
		 * @param str
		 * @return
		 */
		public static String replaceStringToX(String str) {
			String newStr = "";
			
			if (str.length() > 4) {
				String first = str.substring(0, 3);
				String end = str.substring(str.length() - 4);
				newStr = first + "****" + end;
			} else {
				newStr = str + "****";
			}
			return newStr;
		}
	 
	 /**
	  * 姓名首字符替换为*号          *凯强
	  * @param name
	  * @return String
	  * @author hujiaxing
	  * @date 2017年11月9日 下午9:07:04
	  */
	 public static String replaceName(String name){
		 return "*" + name.substring(1, name.length());
	 }
	 /**
	  * 身份证号*号处理                33******021X
	  * @param idNo
	  * @return String
	  * @author hujiaxing
	  * @date 2017年11月9日 下午9:07:12
	  */
	 public static String replaceIdNo(String idNo){
		 if(idNo.length() == 15){
			 return idNo.replaceAll("(\\d{2})\\d{9}(\\w{4})","$1******$2");
		 }
		 return idNo.replaceAll("(\\d{2})\\d{12}(\\w{4})","$1******$2");
	 }
	 
	 /**
	  * 银行卡号   *号处理  **** **** **** 0123
	  * @param bankCard
	  * @return String
	  * @author hujiaxing
	  * @date 2017年11月9日 下午9:07:31
	  */
	 public static String replaceBankCard(String bankCard){
		 return  "**** **** **** "+ bankCard.substring(bankCard.length()-4, bankCard.length());
	 }
	 
	 /**
		 * 对一个数值,直接截取需要的位数;后面的值省略;<br>
		 * 如: 23.3245423; jieQuFa(23.3245423,2); 返回: 23.32; jieQuFa(23.3,2);
		 * 返回23.20;
		 * 
		 * @param number
		 *            数值
		 * @param digit
		 *            保留多少位小数;
		 * @return 截取后的数值;
		 * @throws Exception
		 */
		public static double jieQuFa(double number, int digit) throws Exception {
			if (digit < 0) {
				throw new Exception("The digit must be positive integer!");
			}
			String temp = "1";
			for (int i = 0; i < digit; i++) {
				temp += "0";
			}
			BigDecimal newNum = new BigDecimal(calcNumber(number, temp, "*").doubleValue()).setScale(digit, BigDecimal.ROUND_DOWN);

			return calcNumber(newNum, temp, "/", digit).doubleValue();
		}
		
		/**
		 * 对两个数进行四则运算; <strong>对于除法的运算,保留4位小数;</strong><br>
		 * result = num1+num2;<br>
		 * result = num1-num2;<br>
		 * result = num1*num2;<br>
		 * result = num1/num2;<br>
		 * 
		 * @param num1
		 *            式子中的第一个数;
		 * @param num2
		 *            式子中的第二个数;
		 * @param calcSymbol
		 *            运算符号 "+" "-" "*" "/"
		 * @return BigDecimal
		 * @throws Exception
		 *             计算异常;
		 */
		public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol) throws Exception {
			return calcNumber(num1, num2, calcSymbol, 4);

		}

		/**
		 * 对两个数进行四则运算; 自行选择保留位数;不四舍五入,直接截取; result = num1+num2;<br>
		 * result = num1-num2;<br>
		 * result = num1*num2;<br>
		 * result = num1/num2;<br>
		 * 
		 * @param num1
		 *            式子中的第一个数;
		 * @param num2
		 *            式子中的第二个数;
		 * @param calcSymbol
		 *            运算符号 "+" "-" "*" "/"
		 * @param remainNum
		 *            保留多少位小数; 如果小于0,则为0;
		 * @return BigDecimal
		 * @throws Exception
		 *             计算异常;
		 */
		public static BigDecimal calcNumber(Object num1, Object num2, String calcSymbol, int remainNum) throws Exception {
			remainNum = remainNum < 0 ? 0 : remainNum;
			if (!BaseUtils.isEmptyObject(num1) && !BaseUtils.isEmptyObject(num2)) {
				BigDecimal decimal = new BigDecimal(num1.toString());
				BigDecimal decima2 = new BigDecimal(num2.toString());
				if ("+".equals(calcSymbol)) {
					return decimal.add(decima2);
				} else if ("-".equals(calcSymbol)) {
					return decimal.subtract(decima2);
				} else if ("*".equals(calcSymbol)) {
					return decimal.multiply(decima2);
				} else if ("/".equals(calcSymbol)) {
					if (!num2.equals("0")) {
						return decimal.divide(decima2, remainNum, BigDecimal.ROUND_DOWN);
					} else {
						throw new Exception();
					}
				} else {
					return null;
				}
			}
			return null;
		}
		
		
		/**
		 * 拼接路径
		 * wangxuelin
		 * 2018年1月15日
		 * @param prefix   域名 不能为空
		 * @param leftUrl  剩余的路径 不能为空
		 * @param token token 可以为空
		 * @return 返回拼接好的url地址；如果域名和URL为空；则返回 ''空字符串；
		 */
		public static String connectH5Url(String prefix, String leftUrl, String token){
			if (BaseUtils.isEmptyObject(prefix) || BaseUtils.isEmptyObject(leftUrl)) {
				return "";
			}
			
			StringBuffer buffer = new StringBuffer();
			buffer.append(prefix);
			buffer.append(leftUrl);
			buffer.append("?token=");
			buffer.append(token);
			
			return buffer.toString();
		}
		
		/**
		 * 拼接路径 不含token字段；
		 * wangxuelin
		 * 2018年1月15日
		 * @param prefix   域名 不能为空
		 * @param leftUrl  剩余的路径 不能为空
		 * @return 返回拼接好的url地址；如果域名和URL为空；则返回 ''空字符串；
		 */
		public static String connectH5UrlNoToken(String prefix, String leftUrl){
			if (BaseUtils.isEmptyObject(prefix) || BaseUtils.isEmptyObject(leftUrl)) {
				return "";
			}
			StringBuffer buffer = new StringBuffer();
			buffer.append(prefix);
			buffer.append(leftUrl);
			return buffer.toString();
		}
	 
		/**
		 * 金额转化为大写
		 * @param money
		 * @return
		 */
		public static String moneyToUp(Double money){
			final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";  
		    final String DIGIT = "零壹贰叁肆伍陆柒捌玖";  
		    final double MAX_VALUE = 9999999999999.99D;
			
		    if (money < 0 || money > MAX_VALUE) {  
	            return "参数非法!";  
	        }  
	        long l = Math.round(money * 100);  
	        if (l == 0) {  
	            return "零元整";  
	        }  
	        String strValue = l + "";  
	        // i用来控制数  
	        int i = 0;  
	        // j用来控制单位  
	        int j = UNIT.length() - strValue.length();  
	        String rs = "";  
	        boolean isZero = false;  
	        for (; i < strValue.length(); i++, j++) {  
	            char ch = strValue.charAt(i);  
	            if (ch == '0') {  
	                isZero = true;  
	                if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万'  
	                        || UNIT.charAt(j) == '元') {  
	                    rs = rs + UNIT.charAt(j);  
	                    isZero = false;  
	                }  
	            } else {  
	                if (isZero) {  
	                    rs = rs + "零";  
	                    isZero = false;  
	                }  
	                rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);  
	            }  
	        }  
//	      if (!rs.endsWith("分")) {  
//	          rs = rs + "整";  
//	      }  
	        rs = rs.replaceAll("亿万", "亿");  
	        return rs;  
		    
		}
		
	
	public static Object reflectMapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null){
			return null;
		}

		Object obj = beanClass.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}

			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		return obj;
	}

	public static Map<String, Object> reflectObjectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

	//计算当前时间距离今天晚上23:59:59之间的秒数
	public static long validTime() {
		Calendar curDate = Calendar.getInstance();
		Calendar nextDayDate = new GregorianCalendar(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH),
				curDate.get(Calendar.DATE) + 1, 0, 0, 0);
		return (nextDayDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
	}
	
	// 测试
	public static void main(String[] args) throws Exception {
		
		System.out.println(replaceBankCard("6236681440246437"));
	}
}
