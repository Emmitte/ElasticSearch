package elasticsearch.util;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;


public class ClientUtil {
	private Client client;
	public ClientUtil() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化客户端
	 * */
	private void init() throws Exception {
		byte[] bs = new byte[] { (byte) 192, (byte) 168, (byte)52, (byte)130 }; 
		Settings settings = null;
		
		settings = Settings.settingsBuilder()
                .put("cluster.name", "poc").build();// cluster.name在elasticsearch.yml中配置
		
		if (settings != null) {
			this.client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByAddress(bs), 9300));
		}else {
			this.client = TransportClient.builder().build()  
	                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByAddress(bs), 9300));
		}
		
	}

	public Client getClient() {
		return client;
	}

}
