package GUI;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Mementor{
	private String content;
	Mementor(String content){
		this.content = content;
	}
	public String restoreContent() {
		return this.content;
	}
}
class CareTaker{
	ArrayList<Mementor> list = new ArrayList<Mementor>();
	private int iterator=0;
	public void push(Mementor obj) {
		list.add(obj);
		iterator++;
	}
	public Mementor popback() {
		if(list.size()!=0) {
			iterator--;
			return list.get(iterator);
		}
		return null;
		
	}
	public Mementor popfront() {
		if(iterator < list.size()-1) {
			iterator++;
			return list.get(iterator);
		}
		return null;
	}
}

public class NotePad extends JApplet{
	
	JFrame frame = new JFrame("NotePad");
	JTextArea area;
	JTextField filename,savefile;
	JButton savebtn, clearbtn, undobtn,redobtn,open;
	JLabel label,filelabel,listlabel,savefilelabel,statuslabel;
	JList<String> list;
	JScrollPane scroll,textscroll;
	CareTaker backup = new CareTaker();
	
	public void init() {
		try {
			SwingUtilities.invokeAndWait(
					new Runnable() {
						public void run() {
							try {
								createGUI();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
			);
		}catch(Exception e) {
			System.out.println("Cannot open the GUI:- "+e);
		}
	}
	
	private void createGUI() throws FileNotFoundException {
		String filelist[] = new String[10];
		Set<String> fileset = new TreeSet<String>();
		Scanner sc = new Scanner(new File("C:\\Users\\HP\\Desktop\\DataStructures\\Structures\\fileList.txt"));
		sc.useDelimiter("[^A-Za-z0-9._]+");
		int i=0;
		while(sc.hasNext()) {
			fileset.add(sc.next());
		}
		for(String s:fileset)
		{
			filelist[i++]=s;
		}
		sc.close();
		//header part
		JPanel Element = new JPanel();
		Element.setLayout(new BorderLayout());
		JPanel headElement = new JPanel();
		headElement.setLayout(new GridLayout(1,3));
		
		undobtn = new JButton("Undo");
		undobtn.setHorizontalAlignment(0);
		headElement.add(undobtn);
		
		label = new JLabel("NotePad");
		label.setFont(new Font("serif",Font.ITALIC,30));
		label.setHorizontalAlignment(0);
		headElement.add(label);
		
		redobtn = new JButton("redo");
		redobtn.setHorizontalAlignment(0);
		headElement.add(redobtn);
		
		undobtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mementor temp = backup.popback();
				try {
					System.out.println(temp.restoreContent());
					area.setText(temp.restoreContent());
				}catch(Exception e) {}
			}
			
		});
		
		redobtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Mementor temp = backup.popfront();
				try {
					System.out.println(temp.restoreContent());
					area.setText(temp.restoreContent());
				}catch(Exception e) {}
			}
			
		});
		
		
		//body part.
		JPanel mainElement = new JPanel();
		mainElement.setLayout(new GridLayout(1,1));
		mainElement.setBorder(new TitledBorder(new EtchedBorder(), "TextArea"));
		area = new JTextArea(250,250);
		mainElement.add(area);
		area.setBackground(Color.white);
		area.setFont(new Font("serif",Font.BOLD,16));
		
		area.setLineWrap(true);
//		area.getAutoscrolls();
		area.setAutoscrolls(true);
		area.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar());
				backup.push(new Mementor(area.getText()));
			}
			
		});
		textscroll = new JScrollPane(mainElement);
//		textscroll.setPreferredSize(new Dimension(25,25));
		//filearea.
		JPanel fileArea = new JPanel();
		fileArea.setLayout(new GridLayout(6,1));
		JPanel file = new JPanel();
		JPanel listfile = new JPanel();
		JPanel savelist = new JPanel();
		statuslabel = new JLabel("Status:- No Updates");
		
		statuslabel.setFont(new Font("serif",Font.ITALIC,18));
		
		filelabel = new JLabel("Open a file");
		filename = new JTextField(20);
		open = new JButton("open file");
		filelabel.setFont(new Font("serif",Font.BOLD,16));
		file.add(filelabel);
		file.add(filename);
		file.add(open);
		
		listfile.setLayout(new GridLayout(2,1));
		list = new JList<String>(filelist);
		list.setFont(new Font("serif",Font.BOLD,16));
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(120,90));
		listlabel = new JLabel("Choose a file:- ");
		listlabel.setFont(new Font("serif",Font.BOLD,16));
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = list.getSelectedIndex();
				if(index!=-1 && filelist[index]!=null) {
					listlabel.setText("The file chosen:- "+filelist[index]);
					filename.setText(filelist[index]);
				}
				else {
					listlabel.setText("Choose a file:- ");
				}
			}
			
		});
		
		listfile.add(scroll);
		listfile.add(listlabel);
		
		savefile = new JTextField(20);
		savefilelabel = new JLabel("Create & Save to file:- ");
		savefilelabel.setFont(new Font("serif",Font.BOLD,16));
		savelist.add(savefilelabel);
		savelist.add(savefile);
		
		fileArea.add(file);
		fileArea.add(listfile);
		fileArea.add(savelist);
		fileArea.add(statuslabel);
		
		
		fileArea.setBorder(new TitledBorder(new EtchedBorder(), "File Area"));
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String root = "C:\\Users\\HP\\Desktop\\DataStructures\\Structures\\";
				try {
					savefile.setText(filename.getText());
					FileReader fr = new FileReader(root+filename.getText());
					BufferedReader br = new BufferedReader(fr);
					String data,content="";
					while((data = br.readLine()) != null)
						content+=data+"\n";
					area.setText(content);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
		//footer part.
		JPanel footerElement = new JPanel();
		footerElement.setLayout(new GridLayout(1,1));
		
		savebtn = new JButton("Save");
	
		savebtn.setFont(new Font("serif",Font.BOLD,18));
		footerElement.add(savebtn);
		
		clearbtn = new JButton("Clear");
		clearbtn.setFont(new Font("serif",Font.BOLD,18));
		footerElement.add(clearbtn);
		
		savebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(area.getText());
				String root = "C:\\Users\\HP\\Desktop\\DataStructures\\Structures\\";
				String filesavename = savefile.getText();
				if(filesavename != null) {
					try {
						FileWriter fs = new FileWriter(root+filesavename);
						fs.append(area.getText());
						fs.close();
						FileWriter fl = new FileWriter(root+"fileList.txt");
						fileset.add(filesavename);
						for(String s: fileset) {
							fl.append(s+"\n");
						}
						fl.close();
						statuslabel.setText("Status :- "+filesavename+" file saved.");
					}
					catch(Exception e) {
						System.out.println(e);
					}
				}
				
			}
			
		});
		
		clearbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				area.setText("");
				filename.setText("");
				savefile.setText("");
				listlabel.setText("Choose a file:- ");
			}
			
		});
		
		
		Element.add(headElement,BorderLayout.PAGE_START);
		Element.add(mainElement,BorderLayout.CENTER);
		Element.add(footerElement,BorderLayout.PAGE_END);
		Element.add(fileArea,BorderLayout.LINE_START);
	
		add(Element,BorderLayout.CENTER);
		
	}
	public void saveToFile(String content) throws IOException {
		System.out.println("called"+content);
		FileWriter fs = new FileWriter("input_file");
		fs.write(content);
		fs.close();
		
	}
	public static void main(String[] args) {
		
		NotePad obj =new NotePad();
		obj.init();
	}

}



