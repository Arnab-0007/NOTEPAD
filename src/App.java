import java.awt.Font;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends JFrame implements ActionListener{

    //Creating Menubar
    JMenuBar menubar=new JMenuBar();
    JMenu file=new JMenu("File");
    JMenu edit=new JMenu("Edit");
    JMenu help=new JMenu("help");

    //Creating Menu Items of File Menu
    JMenuItem newFile=new JMenuItem("New");
    JMenuItem openFile=new JMenuItem("Open");
    JMenuItem saveFile=new JMenuItem("Save");
    JMenuItem print=new JMenuItem("Print");
    JMenuItem exit=new JMenuItem("Exit");

    //Creating Menu Items of Edit Menu
    JMenuItem cut=new JMenuItem("Cut");
    JMenuItem copy=new JMenuItem("Copy");
    JMenuItem paste=new JMenuItem("Paste");
    JMenuItem selectall=new JMenuItem("Select All");

    //Creating Menu Items of Help Menu
    JMenuItem about=new JMenuItem("About");

    //creating Text Area for notepad
    JTextArea textArea=new JTextArea();

    App()
    {
        setTitle("Notepad Application");
        setBounds(200,200,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon(getClass().getResource("notepad-icon-7.png"));
        setIconImage(icon.getImage());

        //Adding Menubar to Notepad Application
        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        //Adding Menu Items to the respective Menus
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        help.add(about);
        //Adding Scroll Limitation to TextArea.
        JScrollPane scrollPane=new JScrollPane(textArea);
        add(scrollPane);
        //Adding HorizontalScrollBar to text Editor.
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Adding VerticalScrollBar to text Editor.
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        //Set Default Font And Text Size to Editor.
        textArea.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20 ));

        //text Wrapping for the window.
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //Adding Action Listener to every menu item to perform the task.
        //FILE
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        //Edit
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        //help
        about.addActionListener(this);


//Assigning ShortCut Keys.
        //File
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,KeyEvent.ALT_DOWN_MASK));
        //Edit
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        //Edit
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,KeyEvent.CTRL_DOWN_MASK));
    }
    @Override
    public void actionPerformed(ActionEvent e) // catching the action event input in 'e'.
    {
        if(e.getActionCommand().equalsIgnoreCase("New")){
            textArea.setText(null);//resetting a new text editor place and creating a blank space.
        }else if(e.getActionCommand().equalsIgnoreCase("Open")){

            JFileChooser fileChooser =new JFileChooser();//choosing System window for opening location .
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action=fileChooser.showOpenDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION)
            {
                return;
            }else{
                try{
                    BufferedReader reader=new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(reader,null);
                }catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }

        }else if(e.getActionCommand().equalsIgnoreCase("Save")){

            JFileChooser fileChooser =new JFileChooser();//choosing System window for saving location and save as type.
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action= fileChooser.showSaveDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION)
            {
                return;
            }else{
                String fileName=fileChooser.getSelectedFile().getAbsolutePath().toString();
                if(fileName.contains("txt"))
                    fileName=fileName+".txt";
                try{
                BufferedWriter writer=new BufferedWriter(new FileWriter(fileName));
                textArea.write(writer);
                }catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }

        }else if(e.getActionCommand().equalsIgnoreCase("Print")){
            try{
            textArea.print();
            }catch(PrinterException ex){
                Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,ex);
            }
        }else if(e.getActionCommand().equalsIgnoreCase("Exit")){
                System.exit(0);
        }else if(e.getActionCommand().equalsIgnoreCase("Cut")){
                textArea.cut();
        }else if(e.getActionCommand().equalsIgnoreCase("Copy")){
                textArea.copy();
        }else if(e.getActionCommand().equalsIgnoreCase("Paste")){
                textArea.paste();
        }else if(e.getActionCommand().equalsIgnoreCase("Select All")){
            textArea.selectAll();
        }else if(e.getActionCommand().equalsIgnoreCase("About")){
            new About().setVisible(true);;
        }
    }


    public static void main(String[] args) throws Exception {

    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//changing system window instead of old default window.
    new App().setVisible(true);
    }
}