package GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JTextField;

import java.awt.event.*;
import java.awt.Dimension;



public class JPanelDemo {


    static int Num;
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        JFrame map=new JFrame("计算矩阵");    //创建一个JFrame对象

        map.setBounds(300, 100, 400, 600);    //设置窗口大小和位置

        map.setResizable(false);			//窗体固定大小

        JPanel user=new JPanel();    //创建一个JPanel对象
        JPanel south=new JPanel();    //创建一个JPanel对象
        JPanel left=new JPanel();    //创建一个JPanel对象
        JPanel right=new JPanel();    //创建一个JPanel对象
        JPanel network=new JPanel();    //创建一个JPanel对象
        JLabel label=new JLabel("行列式阶数:");    //创建一个标签
        JButton okbutton=new JButton("确定");    //创建一个按钮
        JTextField text=new JTextField("",2);  //创建一个输入框
        JLabel Asum=new JLabel("");
        JButton count=new JButton("计算");

        map.add(user,BorderLayout.NORTH);
        map.add(network,BorderLayout.CENTER);
        map.add(south,BorderLayout.SOUTH);
        map.add(right,BorderLayout.WEST);
        map.add(left,BorderLayout.EAST);

        user.setPreferredSize(new Dimension(0,50));
        right.setPreferredSize(new Dimension(50,0));
        left.setPreferredSize(new Dimension(50,0));
        south.setPreferredSize(new Dimension(0,250));
        south.add(Asum);
        south.add(count);
        network.setBackground(Color.white);    //设置背景色
        user.add(label);    //将标签添加到面板
        user.add(text);
        user.add(okbutton);

        okbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                network.removeAll();
                String str;
                str=text.getText();
                Num=Integer.parseInt(str);
                network.setLayout(new GridLayout(Num,Num));
                JTextField[][] A=new JTextField[Num][Num];
                for(int i=0;i<Num;i++)
                {
                    for(int j=0;j<Num;j++)
                    {
                        A[i][j]=new JTextField(2);
                        network.add(A[i][j]);
                    }
                }
                count.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent b)
                    {
                        Asum.setText(Double.toString(Array(A,Num)));
                    }
                });
                map.setVisible(true);
            }

        });


        map.setVisible(true);    //设置窗口可见
    }
    public static double Array(JTextField A[][],int num)
    {
        double[][] a=new double[num][num];
        for(int i=0;i<Math.pow(num,2);i++)
        {
            double d;
            if(A[i/num][i%num].getText().contains("/"))
                a[i/num][i%num]= ArithUtil.eval(A[i/num][i%num].getText());
            else
            {
                d=Double.parseDouble(A[i/num][i%num].getText());
                a[i/num][i%num]=d;
            }
        }
        int t=1;
        while(num>2)
        {
            double p;
            double temp;
            int k;
            for(int i=0;i<num-1;i++)   ///此循环用于把矩阵降阶,直到阶数为二
            {
                if(a[num-1][num-1]==0)		///判断a[n-1][n-1]元素是否为零
                {
                    for(k=0;k<num-1;k++)   ///如果a[n-1][n-1]等于0,那么就用最底下元素不为零的那一列的替换现在的最后一列，而且t乘-1
                    {
                        if(a[num-1][k]!=0)
                        {
                            for(int j=0;j<num;j++)
                            {
                                temp=a[j][k];
                                a[j][k]=a[j][num-1];
                                a[j][num-1]=temp;
                            }
                            t*=-1;
                            break;		///替换完跳出循环
                        }
                    } ///
                }
                p=-1*ArithUtil.div(a[num-1][i],a[num-1][num-1]);
                for(int j=0;j<num;j++)
                {
                    a[j][i]=ArithUtil.add(a[j][i],ArithUtil.mul(a[j][num-1],p));
                }
            }
            t*=a[num-1][num-1];
            num--;
        }
        return a[0][0]*a[1][1]-a[0][1]*a[1][0]==0?a[0][0]*a[1][1]-a[0][1]*a[1][0]:t*(a[0][0]*a[1][1]-a[0][1]*a[1][0]);
    }
}


