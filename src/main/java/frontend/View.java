package frontend;

import javax.swing.*;

public class View extends JFrame {
    public View(){
        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        add(b);//adding button in JFrame
        setSize(400,500);//400 width and 500 height
        setLayout(null);//using no layout managers
        setVisible(true);//making the frame visible
    }
}
