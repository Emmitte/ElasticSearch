package protobuf.test;

import java.util.List;

import protobuf.service.KafkaMessageEntity;
import protobuf.service.PersonEntity;
import protobuf.service.PersonEntity.Person;

import com.google.protobuf.InvalidProtocolBufferException;

public class test1 {
	
	public static void sendTest() throws Exception {
		// 创建builder对象  
		PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();  
		builder.setEmail("ggggggg@email.com");  
		builder.setId(1);  
		builder.setName("TestName");  
		builder.addFriends("tom");
		builder.addFriends("jack");
		builder.addFriends("john");
		// 通过builder转化为Person对象  
		Person person = builder.build();  
		// 将person对象转化为字节流  
		byte[] buf = person.toByteArray();  
		  
		try {  
		// 通过字节流转化 为Person对象  
		Person person2 = PersonEntity.Person.parseFrom(buf);  
		System.out.println(person2.getName() + ", " + person2.getEmail()); 
		List friendList = person2.getFriendsList();
		System.out.println("friends:" + friendList);
		} catch (InvalidProtocolBufferException e) {  
		e.printStackTrace();
		}  
		  
		System.out.println(buf);  
		
		KafkaMessageEntity.Person.Builder builder2 = KafkaMessageEntity.Person.newBuilder();  
		builder2.setSource("1");
		builder2.setSourceType("2");
		builder2.setTrailSeq("3");
		builder2.setTrailRba("4");
		builder2.setOperate("5");
		builder2.setOperateTime("6");
		builder2.setDbName("7");
		builder2.setTableName("8");
		KafkaMessageEntity.Person.KeyColumnEntity.Builder KeyColumnBuilder1 = KafkaMessageEntity.Person.KeyColumnEntity.newBuilder();
		KeyColumnBuilder1.setColumnName("column1");
		KeyColumnBuilder1.setColumnValue("value1");
		builder2.addKeyColumn(KeyColumnBuilder1);
		KafkaMessageEntity.Person.KeyColumnEntity.Builder KeyColumnBuilder2 = KafkaMessageEntity.Person.KeyColumnEntity.newBuilder();
		KeyColumnBuilder2.setColumnName("column2");
		KeyColumnBuilder2.setColumnValue("value2");
		builder2.addKeyColumn(KeyColumnBuilder2);
		KafkaMessageEntity.Person kafkaPerson = builder2.build();
		buf = kafkaPerson.toByteArray();
		
		KafkaMessageEntity.Person kafkaPerson2 = KafkaMessageEntity.Person.parseFrom(buf);
		System.out.println(kafkaPerson2.getKeyColumnCount());
		System.out.println(kafkaPerson2.getKeyColumnList());
		System.out.println(kafkaPerson2.getColumnList());
	}

	public static void main(String[] args) throws Exception {
		sendTest();
	}

}
