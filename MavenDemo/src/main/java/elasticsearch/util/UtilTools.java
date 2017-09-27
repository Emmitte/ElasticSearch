package elasticsearch.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用工具方法的集合
 * @author wangxd
 */
public class UtilTools {
	
    /**
     * 
     * 检查是否为null
     *@param	str		被检查对象
     *@return	boolean null?true:false
     */
    public static boolean isNull(Object obj){
    	if(obj == null)return true;
    	return false;
    }
    /**
     * 
     * 检查字符串是否为空
     *@param	str		被检查字符串
     *@return	boolean ""?true:false
     */
    public static boolean isEmpty(String str){
    	if("".equals(str.trim()))return true;
    	return false;
    }
    /**
     * 
     * 检查字符串是否为空或null
     *@param	str		被检查字符串
     *@return
     */
    public static boolean isNullOrEmpty(String str){
    	if(isNull(str) || isEmpty(str))return true;
    	return false;
    }
    /**
     * 功能说明：string字符串转换
     * @param str
     * @param code
     */
    public static String StringEncode(String str,String code){
    	if(isNullOrEmpty(str))return null;
    	if(isNullOrEmpty(code))return null;
    	try {
			return new String(str.getBytes(),code);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
	
	/**
	 *  将日期格式为年月日
	 * @param date
	 * @return
	 */
	public static String formatDateToYMD(Date date){
		if(date == null)return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 *  将日期格式为年月日时分秒
	 * @param date
	 * @return
	 */
	public static String formatDateToYMDHMS(Date date){
		if(date == null)return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 将时间转换为时间戳
	 * @param String time
	 * @return long
	 * @throws ParseException
	 */   
    public static long dateToTimestamp(String time) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();
        return ts;
    }
    
    /**
	 * 将时间戳转换为时间
	 * @param long timestamp
	 * @return String
	 * @throws Exception
	 */ 
    public static String timestampToDate(long timestamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp);
        res = simpleDateFormat.format(date);
        return res;
    }
	
	/**
	 *  随机生成字符，含大写、小写、数字
	 * @return
	 */
    public static String getRandomChar() {   
        int index = (int) Math.round(Math.random() * 2);  
        String randChar = "";   
        switch (index) {   
        case 0://大写字符   
            randChar = String.valueOf((char)Math.round(Math.random() * 25 + 65));   
            break;   
        case 1://小写字符   
            randChar = String.valueOf((char)Math.round(Math.random() * 25 + 97));   
            break;   
        default://数字   
            randChar = String.valueOf(Math.round(Math.random() * 9));   
            break;   
        }   
        return randChar;   
    }   
    
	
	public static void  main(String[] args){
		String fileSepator = System.getProperties().getProperty("file.separator");
		UtilTools.class.getResourceAsStream(fileSepator);
		System.out.println(System.getProperty("bhtec.root"));
		File[] drive = File.listRoots();
		for (int i = 0; i < drive.length; i++) {
			System.out.println("\t" + drive[i]);
		} 
	}
}
