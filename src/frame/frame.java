package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import Server.Receiver;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.JTable;

public class frame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("端口号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel.setBounds(154, 51, 71, 46);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 25));
		textField.setBounds(118, 96, 131, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		this.setResizable(true); //设置窗口大小是否可变
		ImageIcon icon = new ImageIcon("src/image/timg.jpg");
				//new ImageIcon(this.getClass().getResource("Resources/timg.jpg"));
		this.setIconImage(icon.getImage());
		//scrollPane.setViewportView(textArea);

		
	    final JTextArea textArea = new JTextArea();
	    textArea.setFont(new Font("Monospaced", Font.PLAIN, 21));

		JScrollPane scrollPane = new JScrollPane(textArea);

		scrollPane.setBounds(248, 142, 607, 427);
		contentPane.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	//scrollPane.setViewportView(textArea);
		
		
	
		
		final JButton btnNewButton = new JButton("连接");
		
		  final long threadid;
		//final Thread current;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						//System.out.println(Thread.currentThread().getId());
					if(textField.getText().trim().equals(""))
					{
						textArea.append("请输入端口号\n");
					}else {
						
					
			 btnNewButton.setEnabled(false);
			 int prot=Integer.parseInt(textField.getText());
			  
			
					  textArea.setText("-----欢迎使用DTU服务端程序 V1.1-----\n");	
					
			

					Receiver receiver;
					try {
						receiver = new Receiver(prot);
						textArea.append("打开端口成功，等待数据...\n");
						receiver.receive(textArea,table_1,textField_5);
					} catch (IOException e1) {
						e1.printStackTrace();
						textArea.append("端口被占用\n");
					}
					
				
				
					
				
			 
					}}
				}).start();
				
				 
			}
		});
		
		btnNewButton.setBounds(16, 22, 103, 29);
		contentPane.add(btnNewButton);
		
	
		JButton btnNewButton_1 = new JButton("断开");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(true);
				
				//textField.setText("");
				//Thread thread1=findThread(18);
				
				
			}
		});
		btnNewButton_1.setBounds(753, 95, 103, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("日志");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File f=new File("F://1.txt");
				//创建文件夹选择器，选择文件路径
				JFileChooser jf = new JFileChooser();  
				jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);  
				jf.showDialog(null,null);  
				File fi = jf.getSelectedFile();  
				String file = fi.getAbsolutePath()+"\\text.txt"; 
           //将textarea里文本以每行一对一的方式写进txt文件中去
				BufferedWriter bw = null;
                try {
                    OutputStream os = new FileOutputStream(file);
                    bw = new BufferedWriter(new OutputStreamWriter(os));
                    for (String value : textArea.getText().split("\n")) {
                        bw.write(value);
                        bw.newLine();//换行
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
//					
				   
			}
		});
		btnNewButton_2.setBounds(753, 50, 103, 46);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("设置");
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton_3.setBounds(16, 49, 103, 46);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("运行");
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton_4.setBounds(16, 95, 103, 46);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("报警上限");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(268, 51, 108, 46);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_1.setBounds(245, 96, 131, 46);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("报警下限");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(401, 51, 100, 46);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_2.setBounds(370, 96, 131, 46);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("测量参数");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel_3.setBounds(528, 51, 95, 46);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_3.setBounds(492, 96, 131, 46);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("测量地址");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel_4.setBounds(650, 51,88, 46);
		contentPane.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_4.setBounds(623, 96, 131, 46);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("常州大学校园温度监测系统");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 32));
		lblNewLabel_5.setBounds(268, 15, 432, 32);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("当前值");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_6.setBounds(83, 144, 87, 62);
		contentPane.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_5.setBounds(15, 221, 233, 62);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("报警记录");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_7.setBounds(82, 283, 166, 78);
		contentPane.add(lblNewLabel_7);
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setFont(new Font("宋体", Font.PLAIN, 22));
//		table_1.setBounds(16, 358, 225, 211);
	//	table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setShowHorizontalLines(true);
//		table_1.setShowVerticalLines(true);
		table_1.setRowHeight(30);
		//table.setModel(tableModel);
		//table.setCellSelectionEnabled(false);

		//tableModel.isCellEditable(row, column)
		//table.setColumnSelectionAllowed(false);
		//tableModel.
//		Vector vRow1 = new Vector();
//		vRow1.add("cell 2 0");
//		vRow1.add("cell 2 1");
//		vData.add(vRow1);
//		tableModel = new DefaultTableModel(vData, vName);
		
		//table_1 = new JTable();
		
	//	contentPane.add(table);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(16, 358, 225, 211);
		scrollPane_1.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//scrollPane_1.setViewportView(table_1);
		contentPane.add(scrollPane_1);
		
		
		
	
		
		
		
		
		
		
		
		
		
		
	
		
	
	}
}
