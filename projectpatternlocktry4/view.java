/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectpatternlocktry4;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Wajeeha Parker
 */
public class view extends JPanel implements Runnable{
    Thread th;
    Graphics2D g;
    int DOTS=9;
    boolean trues[]= new boolean[DOTS];
    Rectangle rect[] = new Rectangle[DOTS];
    int pattern[]= new int[DOTS];
    List<Line2D.Double> lines = new ArrayList<>();
    Color ink= Color.green;
    Color dot=Color.green;   
    int startx,starty,endx,endy,enddx,enddy;
    int end,start,index=1,stroke=2,time=5,rdots=(int)Math.sqrt(DOTS);
    int incw=20,oncw;
    boolean drawing=false;
    String patt="";
    Timer timer;
//    JLabel output;
    static int fnumber;
    ArrayList t=null, obj0=null, obj1=null, obj2=null, obj3=null, obj4=null, obj5=null, obj6=null, obj7=null, obj8=null;
    TGtoRE tg1=null, tg2=null;
    String str1="", str2="";
    static boolean match=true;
    static ArrayList<ArrayList<table>> tt= null;
    ArrayList<ArrayList<table>> oldtt;
    String oldstr;
        
    
    public view(){

    try{
        oncw=incw+40;
        th = new Thread(this);
        setBackground(Color.WHITE);
        setOpaque(false);
        for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels())
        {
            if(info.getName().equals("Nimbus"))
            {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        ActionListener al = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(time==0)
                {
                    resetScreen();
                    timer.stop();
                }
                --time;
            }
        };
        timer= new Timer(100, al);
        //Listener starts
        
            MouseListener ml = new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent me)
                {
                    resetScreen();
                    index=1;
                    startx=me.getX();
                    starty=me.getY();
                    for(int i=0;i<rect.length;++i)
                    {
                        if(rect[i].contains(me.getPoint()))
                        {
                            startx=(int)rect[i].getCenterX();
                            starty=(int)rect[i].getCenterY();
                            endx=startx;
                            endy=starty;
                            trues[i]=true;
                            pattern[i]=index;
                            start=i;
                            drawing=true;
                            break;              
                        }
                    }
                }
                @Override
                public void mouseReleased(MouseEvent me)
                {
                    drawing=false;
                    printPattern();
//                    transtable();
                    time=5;
                    timer.start();
                }
            };
            MouseMotionListener mll = new MouseAdapter()
            {
               @Override
                public void mouseDragged(MouseEvent me)
                {
                    if(drawing==true)
                    {
                        endx=me.getX();
                        endy=me.getY();
                        for(int i=0;i<rect.length;++i)
                        {
                            if(trues[i]!=true)
                            {
                                if(rect[i].contains(me.getPoint()))
                                {
                                    index+=1;
                                    enddx=(int)rect[i].getCenterX();
                                    enddy=(int)rect[i].getCenterY();
                                    lines.add(new Line2D.Double(startx, starty, enddx, enddy));
                                    startx=enddx;
                                    starty=enddy;
                                    end=i;
                                    if((start==0&&end==2)||(start==2&&end==0)){
                                        if(trues[1]==false){
                                        trues[1]=true;pattern[1]=index;index+=1;}}
                                    if((start==3&&end==5)||(start==5&&end==3)){
                                        if(trues[4]==false){
                                        trues[4]=true;pattern[4]=index;index+=1;}}
                                    if((start==6&&end==8)||(start==8&&end==6)){
                                        if(trues[7]==false){
                                        trues[7]=true;pattern[7]=index;index+=1;}}
                                    if((start==0&&end==6)||(start==6&&end==0)){
                                        if(trues[3]==false){
                                        trues[3]=true;pattern[3]=index;index+=1;}}
                                    if((start==1&&end==7)||(start==7&&end==1)){
                                        if(trues[4]==false){
                                        trues[4]=true;pattern[4]=index;index+=1;}}
                                    if((start==2&&end==8)||(start==8&&end==2)){
                                        if(trues[5]==false){
                                        trues[5]=true;pattern[5]=index;index+=1;}}
                                    if((start==0&&end==8)||(start==8&&end==0)){
                                        if(trues[4]==false){
                                        trues[4]=true;pattern[4]=index;index+=1;}}
                                    if((start==2&&end==6)||(start==6&&end==2)){
                                        if(trues[4]==false){
                                        trues[4]=true;pattern[4]=index;index+=1;}}
                                    start=i;
                                    trues[i]=true;
                                    pattern[i]=index;
                                    break;
                                }     
                            }
                        }
                    }
                }
            };
            addMouseListener(ml);
            addMouseMotionListener(mll);
             //Listener ends
            th.start();
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    @Override
    public void paint(Graphics g2)
    {
        g=(Graphics2D)g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g);
        int ind=0;
        g.setColor(ink);
        g.setStroke(new BasicStroke(incw,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        if(drawing==true)
            g.drawLine(startx, starty, endx, endy);
        for(int i=0;i<lines.size();++i)
            g.draw(lines.get(i));
        g.setColor(dot);
        g.setStroke(new BasicStroke(stroke));
        for(int i=(getWidth()/rdots)/2;i<getWidth();i+=getWidth()/rdots)
        {
            for(int j=(getHeight()/rdots)/2;j<getHeight();j+=getHeight()/rdots)
            {
                g.fillOval(i-incw/2, j-incw/2, incw, incw);
                if(trues[ind]==true)
                    g.drawOval(i-(oncw)/2, j-(oncw)/2, oncw, oncw);
                rect[ind]=new Rectangle();
                rect[ind].setLocation(i-(oncw)/2,  j-(oncw)/2);
                rect[ind].setSize(oncw+stroke/2, oncw+stroke/2);
                ind+=1;
            }
        }
    }
    public void resetScreen()
    {
        if(timer.isRunning())
            timer.stop();
        clearPattern();
        lines.clear();
        makeFalse();
    }
    public void makeFalse()
    {
         for(int i=0;i<trues.length;++i)
             trues[i]=false;
    }
    

    public void printPattern(){
        String s="";
        for(int i=0;i<pattern.length;++i)
            s+=","+pattern[i];
        patt=s.substring(1);
//        System.out.println("Pattern = "+patt);
        transtable();
//        output.setText("Pattern = "+patt);
                    
    }
//    public void setOutputComponent(JLabel l)
//    {
//        output=l;
//    }

    public void clearPattern(){
        for(int i=0;i<pattern.length;++i)
            pattern[i]=0;
    }
    
    public void transtable()
    {
        t=new ArrayList();
        obj0=new ArrayList();
        obj1=new ArrayList();
        obj2=new ArrayList();
        obj3=new ArrayList();
        obj4=new ArrayList();
        obj5=new ArrayList();
        obj6=new ArrayList();
        obj7=new ArrayList();
        obj8=new ArrayList();
        table tab;
        String str;
        int count=0, node1=1, node2=2, state, is = 0, fs = 0;
        for(int i=0; i<pattern.length; i++)
            if(pattern[i]!=0)
                count++;
            while(count!=0)
            {
                for (int i=0; i<pattern.length; i++)
                {
                    if(pattern[i]==node1)
                    {
                        state=i+1;
                        if(node1==1)
                            is=i;
                        str=Integer.toString(i);
                        for(int j=0; j<pattern.length; j++)
                            if(pattern[j]==node2)
                            {
                                fs=j;
                                str+=Integer.toString(j);
                                tab=new table(j,str);
                                switch(state)
                                {
                                    case 1 : obj0.add(tab);
                                             break;
                                    case 2 : obj1.add(tab);
                                             break;
                                    case 3 : obj2.add(tab);
                                             break;
                                    case 4 : obj3.add(tab);
                                             break;
                                    case 5 : obj4.add(tab);
                                             break;
                                    case 6 : obj5.add(tab);
                                             break;
                                    case 7 : obj6.add(tab);
                                             break;
                                    case 8 : obj7.add(tab);
                                             break;
                                    case 9 : obj8.add(tab);
                                             break;
                                    default: System.out.println("error");
                                }
                                break;
                            }
                        break;
                    }
                }
                count--;
                node1++;
                node2++;
            }
        t.add(obj0);
        t.add(obj1);
        t.add(obj2);
        t.add(obj3);
        t.add(obj4);
        t.add(obj5);
        t.add(obj6);
        t.add(obj7);
        t.add(obj8);
        //current password
        if(fnumber==3)
        {
            tg2=new TGtoRE(t.size(), t, new int[]{is}, new int[]{fs});
            if(!tg2.validate(str1))
            {
                match=false;
                new view1().setVisible(true);
            }
            else
                new view4().setVisible(true);
        }
        //new password
        else if(fnumber==4)
        {   
            oldtt=this.tt;
            oldstr=str1;
            this.tt=t;
            tg1=new TGtoRE(this.tt.size(), this.tt, new int[]{is}, new int[]{fs});
            str1=tg1.convert();
            
            if(TGtoRE.onedigitpass==true)
            {
//                this.setVisible(false);
                this.tt=null;
                new view1().setVisible(true);
                TGtoRE.onedigitpass=false;
            }
            else
            {
                str2="";
                for(int i=0; i<str1.length(); i++)
                    if(str1.charAt(i)=='(' || str1.charAt(i)==')')
                        continue;
                    else str2+=str1.charAt(i);
                str1=str2;
                new view5().setVisible(true);
            }
        }
        //verify password
        else if(fnumber==5)
        {
            tg2=new TGtoRE(t.size(), t, new int[]{is}, new int[]{fs});
            if(!tg2.validate(str1))
            {
                match=false;
//                this.tt=null;
                this.tt=oldtt;
                str1=oldstr;
                new view1().setVisible(true);
            }
            else
            {
                match=true;
                new view1().setVisible(true);
            }
        }
        //login password
        else if(fnumber==6)
        {
            tg2=new TGtoRE(t.size(), t, new int[]{is}, new int[]{fs});
            if(!tg2.validate(str1))
            {
                match=false;
                new view1().setVisible(true);
            }
            else
                new view8().setVisible(true);
        }
//        }
    }
    
    @Override
    public void run() {
        try{
            while(true)
            {
               th.sleep(15);
               repaint();
            }
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
       }
    }
    
    
}