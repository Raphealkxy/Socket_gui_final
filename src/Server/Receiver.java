package Server;
import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Receiver {

	private final  ServerSocket serverSocket;

	public Receiver(int port) throws IOException {

		serverSocket = new ServerSocket(port);
		
	
	}
   public  void   stop() throws IOException
   {
	   serverSocket.close();
   }
	public void receive(TimeSeriesCollection dataset,TimeSeries timeSeries,JTable jTable,JTextField jTextField) throws IOException {
         
        //TimeSeries timeSeries = new TimeSeries("不良笑花", Minute.class);  
		Vector vData = new Vector();
		Vector vName = new Vector();
		Vector vRow;
		DefaultTableModel tableModel;
		vName.add("column1");
		vName.add("column2");
		Socket socket = serverSocket.accept();
		double value;
		//SimpleDateFormat sdf;
		Calendar calendar;		  
		Date date;
		Boolean flag=true;
	  

		try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
			while (true) {
				byte[] bytes = new byte[30]; // 假设发送的字节数不超过 1024 个
				int size = dis.read(bytes); // size 是读取到的字节数

				if (size > 0) {
					Date now = new Date();
					
				

					SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss");
				//	String getDate =sFormat.format(now) + "收到数据...\n";
				//	area.append(getDate);

					try {
						String str = new String(bytes, "GBK");
						String str2 = new String(bytes, "GB18030");

			
						value=Double.valueOf(str.trim());
						timeSeries.add(new Millisecond(),value);
						
						if(flag)
						{
					       dataset.addSeries(timeSeries);
					       flag=false;
						}
					       try {  
				                Thread.currentThread().sleep(100);  
				            } catch (InterruptedException e) {  
				                e.printStackTrace();  
				            }  
					    vRow = new Vector();
					    vRow.add(sFormat.format(now));
						vRow.add(str.trim());
						vData.add(vRow);
						jTextField.setText(str.trim());
						tableModel=new DefaultTableModel(vData, vName);
						jTable.setModel(tableModel);
						
					} catch (Exception e) {
						e.printStackTrace();
					
					//	area.append("解析失败...");
					}
					
				//	area.append("-----------------\n");
				}


			}
		}

	}

	/**
	 * 将 byte 数组转化为十六进制字符串
	 *
	 * @param bytes
	 *            byte[] 数组
	 * @param begin
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @return byte 数组的十六进制字符串表示
	 */
	private String bytesToHex(byte[] bytes, int begin, int end) {
		StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
		for (int i = begin; i < end; i++) {
			hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16)); // 转化高四位
			hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16)); // 转化低四位
			hexBuilder.append(' '); // 加一个空格将每个字节分隔开
		}
		return hexBuilder.toString().toUpperCase();
	}

}
