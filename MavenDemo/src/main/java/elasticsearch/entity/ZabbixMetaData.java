package elasticsearch.entity;
/**
 * zabbix元数据实体类
 * 
 * @author wangyd
 */
public class ZabbixMetaData {
	String ID;
	//ip
	String IP;
	//当天的value
	String VALUE_DAY;
	//组名
	String GROUPNAME;
	//机器类型
	String TYPE_MACHINE;
	//日期
	String DATE;
	//类型
	String TYPE;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getVALUE_DAY() {
		return VALUE_DAY;
	}
	public void setVALUE_DAY(String vALUE_DAY) {
		VALUE_DAY = vALUE_DAY;
	}
	public String getGROUPNAME() {
		return GROUPNAME;
	}
	public void setGROUPNAME(String gROUPNAME) {
		GROUPNAME = gROUPNAME;
	}
	public String getTYPE_MACHINE() {
		return TYPE_MACHINE;
	}
	public void setTYPE_MACHINE(String tYPE_MACHINE) {
		TYPE_MACHINE = tYPE_MACHINE;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	@Override
	public String toString() {
		return "ZabbixMetaData [ID=" + ID + ", IP=" + IP + ", VALUE_DAY="
				+ VALUE_DAY + ", GROUPNAME=" + GROUPNAME + ", TYPE_MACHINE="
				+ TYPE_MACHINE + ", DATE=" + DATE + ", TYPE=" + TYPE + "]";
	}

}
