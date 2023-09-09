import java.awt.Font;

import javax.swing.*;
public class About extends JFrame{
    About()
    {
        setBounds(100, 100, 800, 600);
        setTitle("About Notepad Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon=new ImageIcon(getClass().getResource("notepad-icon-7.png"));
        setIconImage(icon.getImage());
        setLayout(null);
        JLabel iconLabel=new JLabel(new ImageIcon(getClass().getResource("notepad-icon-8.png")));
        iconLabel.setBounds(100,50,80,80);
        add(iconLabel);
        JLabel textLabel=new JLabel("<html>Welcome to NotePad.A simple Text Editor project.<br>Created by Arnab Debnath.<br>All rights reserved@2023</html>");
        textLabel.setBounds(100,45,400,300);
        textLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        add(textLabel);
    }


public static void main(String args[])
{
    new About().setVisible(true);
}

}