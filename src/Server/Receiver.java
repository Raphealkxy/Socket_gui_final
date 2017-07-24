package Server;
import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Receiver {

	private final  ServerSocket serverSocket;

	public Receiver(int port) throws IOException {

		serverSocket = new ServerSocket(port);
		
	
	}
   public  void   stop() throws IOException
   {
	   serverSocket.close();
   }
	public void receive(JTextArea area,JTable jTable,JTextField jTextField) throws IOException {

		
		Vector vData = new Vector();
		Vector vName = new Vector();
		Vector vRow;
		DefaultTableModel tableModel;
		vName.add("column1");
		vName.add("column2");
		Socket socket = serverSocket.accept();

		try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
			while (true) {
				byte[] bytes = new byte[30]; // 假设发送的字节数不超过 1024 个
				int size = dis.read(bytes); // size 是读取到的字节数

				if (size > 0) {
					Date now = new Date();
					//SimpleDateFormat sFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

					SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss");
					String getDate =sFormat.format(now) + "收到数据...\n";
					area.append(getDate);

					try {
						String str = new String(bytes, "GBK");
						String str2 = new String(bytes, "GB18030");

					    area.append("使用GBK解析    :" + str.trim()+"\n");
					
					    area.append("使用GB18030解析:" + str2.trim()+"\n");
					    vRow = new Vector();
					    vRow.add(sFormat.format(now));
						vRow.add(str.trim());
						vData.add(vRow);
						jTextField.setText(str.trim());
						tableModel=new DefaultTableModel(vData, vName);
						jTable.setModel(tableModel);
						
					} catch (Exception e) {
						e.printStackTrace();
					
						area.append("解析失败...");
					}
					
					area.append("-----------------\n");
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
