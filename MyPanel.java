import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MyPanel extends JPanel implements ActionListener  {
    private JTextField url;
    private JLabel img;
    private JLabel exmaple;
    private JLabel sample;
    //private JToggleButton grab;
    private JLabel stat;
    private Button grab;
    private ImageIcon icn; 
    

    public MyPanel() {
        icn = new ImageIcon("html.png");
        url = new JTextField (5);
        img = new JLabel (icn);
        exmaple = new JLabel ("eg: xyz.com");
        sample = new JLabel ("ENTER WEB ADDRESS");
        //grabk = new JToggleButton ("G R A B");
        grab = new Button ("G R A B");
        stat = new JLabel (" ");

        setBorder( BorderFactory.createTitledBorder( "<html/> Graber" ) );
        setPreferredSize (new Dimension (270, 330));
        setLayout (null);

        grab.addActionListener(this);
        add (url);
        add (img);
        add (exmaple);
        add (sample);
        add (grab);
        add (stat);

        url.setBounds (50, 205, 175, 30);
        img.setBounds (70, 40, 125, 90);
        exmaple.setBounds (100, 170, 80, 25);
        sample.setBounds (70, 145, 135, 25);
        grab.setBounds (90, 245, 100, 25);
        stat.setBounds (90, 285, 150, 25);
    }

    public static void main (String[] args)
     {
        JFrame frame = new JFrame ("HTML GRABER");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);

        }

    public void actionPerformed(ActionEvent e)
    {
        String web = url.getText();
        if (web.isEmpty()) {
            stat.setText("invalid address");
        }
        else {
            try{
            URL host = new URL("http://"+web+"/");
            URLConnection con = host.openConnection();
            InputStream is =con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
                while ((line = br.readLine()) != null) 
                {//System.out.println(line);
                    try { 
                         File f1 = new File(web+".html");
                                 if(!f1.exists()) 
                                 {f1.createNewFile();} 
                                     FileWriter fileWritter = new FileWriter(f1.getName(),true);
                                     BufferedWriter bw = new BufferedWriter(fileWritter);
                                     bw.write("<!--html graberd by <html/> greaber made by chinmay-->");
                                     bw.write(line);
                                     bw.close();
                                     stat.setText("index file created...");
                         } 
                         catch(IOException eex)
                         {stat.setText("404");}
                }
            }
            catch(IOException exe)
            {stat.setText("unk error");}
            //catch(MalformedURLException ex)
            //{stat.setText("unk error");}
        }
    }   


    public void windowClosing(WindowEvent we)
    {
     System.exit(0);
    }
}
