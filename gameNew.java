import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class gameNew extends JFrame implements MouseListener,ActionListener
{
	
	int[][] nodesToLines = new int[24][2];
	int[][] LinesToNodes = new int[16][3];
	
	//counter variable initialization
	int count= 0, lo=1,redscore=0,bluescore=0,fccou=0,num,move=0;
	JLabel l1,l2,re,bl;
	JTextField t1,t2;
	Color src;
	
	//buttons initialization
	private JButton[] buttons = new JButton[24];
	public gameNew()
	{
		addMouseListener(this);
      		JPanel p = new JPanel();
      		int ctr =0;
      		for(int i=0;i<8;i++)
      		{
      			for(int j=0;j<3;j++)
          		{
          		   buttons[ctr] = new JButton(Integer.toString(ctr));
          		   buttons[ctr].addActionListener(this);
          		   p.add(buttons[ctr]);
          		   LinesToNodes[i][j] = ctr;
          		   nodesToLines[ctr][0] = i; 
          		   ctr ++;
          		}
      		}
      		
      		//following are the vertical lines
      		LinesToNodes[8] = new int[] {0,9,21 };
      		LinesToNodes[9] = new int[] {3,10,18 };
      		LinesToNodes[10] = new int[] {6,11,15 };
      		LinesToNodes[11] = new int[] {1,4,7 };
      		LinesToNodes[12] = new int[] {16,19,22 };
      		LinesToNodes[13] = new int[] {8,12,17 };
      		LinesToNodes[14] = new int[] {5,13,20 };
      		LinesToNodes[15] = new int[] {2,14,23 };
      		
      		for(int i=8;i<16;i++)
      		{
      			for(int j=0;j<3;j++)
          		{
      				nodesToLines[LinesToNodes[i][j]][1] = i; 
          		}
      		}
      		
      		
      		
      		
      		
      		
		
	//creating buttons with labels
      		p.setLayout(null);
	//placing buttons at a desired location
	
      	buttons[0].setBounds(30,15,64,20);	buttons[1].setBounds(664,15,64,20);	buttons[2].setBounds(1220,15,64,20);	buttons[3].setBounds(130,115,64,20);
	buttons[4].setBounds(664,115,64,20);	buttons[5].setBounds(1199,114,64,20);	buttons[6].setBounds(223,212,64,20);	buttons[7].setBounds(664,212,64,20);
	buttons[8].setBounds(1104,212,64,20);	buttons[9].setBounds(30,327,64,20);	buttons[10].setBounds(130,327,64,20);	buttons[11].setBounds(223,327,64,20);
	buttons[12].setBounds(1104,327,64,20);	buttons[13].setBounds(1199,327,64,20);	buttons[14].setBounds(1220,327,64,20);buttons[15].setBounds(223,488,64,20);
	buttons[16].setBounds(664,488,64,20);	buttons[17].setBounds(1104,488,64,20);	buttons[18].setBounds(130,590,64,20);	buttons[19].setBounds(664,590,64,20);
	buttons[20].setBounds(1199,590,64,20);buttons[21].setBounds(30,690,64,20);	buttons[22].setBounds(664,690,64,20);	buttons[23].setBounds(1220,690,64,20);
      	
	
	l1=new JLabel("ELEVEN MEN MORRIS GAME SCORE BOARD");
	l1.setBounds(570,180,900,180);
	p.add(l1);
	
	l2=new JLabel();
	l2.setBounds(560,220,560,220);
	p.add(l2);
	
	re=new JLabel("RED:");
	re.setBounds(370,407,30,30);
	p.add(re);
	bl=new JLabel("BLUE:");
	bl.setBounds(885,407,40,40);
	p.add(bl);
	
	t1=new JTextField(2);
	t1.setBounds(400,400,60,60);
	p.add(t1);
	t2=new JTextField(2);
	t2.setBounds(930,400,60,60);
	p.add(t2);


 		getContentPane().add(p);
		//setLayout(null);
		
      		setDefaultCloseOperation(3);
      		setSize(1400,1400);
      		setVisible(true);

	}

	//used this code to know the location where to keep the buttons
	String s,pa;
	int x,y;
	public void init()
	{
		s=" ";
		x=0;
		y=0;
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent me)
	{
		x=me.getX();
		y=me.getY();
		System.out.println(x+","+y);
	}//upto here it is used to know the location where to keep the buttons
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public boolean checkDadi(int nodeId, Color c)
	{
		for(int i=0;i<2;i++)
  		{
			int lineNo = nodesToLines[nodeId][i];
			int count3 = 0;
  			for(int j=0;j<3;j++)
      		{
  				int nodeNo  = LinesToNodes[lineNo][j];
  				if (buttons[nodeNo].getBackground() == c )
  				{
  				  count3++;
  				}
      		}
  			if(count3 == 3) return true;
  			
  		}
		return false;
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		s=ae.getActionCommand();
		
		if((count==0)&&(lo<=22)){
			
			((JButton)ae.getSource()).setBackground(Color.red);
			if(checkDadi(Integer.parseInt(s), Color.red))
			{
				// display dadi on screen somewhere
				t1.setText("dadi at "+ s );
				// remove opponents coin
				count = 2;  // reds turn to remove a blue;
				
				lo++;
			}
			else
			{
			count=1;lo++;
			}
		}//if close
		else if((count==1)&&(lo<=22)){
			((JButton)ae.getSource()).setBackground(Color.blue);
			if(checkDadi(Integer.parseInt(s), Color.blue))
			{
				// display dadi on screen somewhere
				t2.setText("dadi at "+ s );
				// remove opponents coin
				count = 3;  // blues turn to remove a red;
				
				lo++;
			}
			else
			{
			count=0;lo++;
			}
		}//else if close
		
		else if(count==2)  // reds turn to remove a blue;
		{
			if(buttons[Integer.parseInt(s)].getBackground() == Color.blue)
			{
				if (!checkDadi(Integer.parseInt(s), Color.blue))
				{
					buttons[Integer.parseInt(s)].setBackground(Color.gray);
					if(lo <= 22)
					{
						count = 1;
					}
					else
					{
						count = 5;   //blues turn to move
					}
				}
				else
				{
				   //give message dadi of opponent cannot be disturbed
					return;
				}
			}
		}
		else if(count==3)  // blues turn to remove a red;
		{
			if(buttons[Integer.parseInt(s)].getBackground() == Color.red)
			{
				if (!checkDadi(Integer.parseInt(s), Color.red))
				{
					buttons[Integer.parseInt(s)].setBackground(Color.gray);
					if(lo <= 22)
					{
						count = 0;
					}
					else
					{
						count = 4;   // reds turn to move
					}
				}
				else
				{
				   //give message dadi of opponent cannot be disturbed
					return;
				}
			}
		}
		else if(count==4 || (lo==22 && move==0))  // reds turn to move
		{
		  
			System.out.println("fccou:"+fccou);
			if(fccou==0)
			{
				num=Integer.parseInt(s);
					System.out.println("btn no:"+num);
				src=buttons[num].getBackground();
					System.out.println("src col:"+src);
				fccou=1;
			}
			if(fccou==1)
			{
				buttons[Integer.parseInt(s)].setBackground(src);
				fccou=0;
			}
		}
		else if(count==5 && lo==22)  // blues turn to move
		{
		   
		}
		
		
	}//action performed close ///////////////
	


	public void paint(Graphics  g)
	{

		super.paint(g);
	
		g.drawRect(50,50,(this.getWidth()-100),this.getHeight()-100);
		g.drawRect(150,150,(this.getWidth()-300),this.getHeight()-300);
		g.drawRect(250,250,(this.getWidth()-500),this.getHeight()-500);
		
		int x=(50+this.getWidth()-100)/2;
		int y=(250+this.getWidth()-500)/2;
		int z=250+this.getHeight()-500;
		int s=250+this.getWidth()-500;
		int s1=(this.getWidth()-500)+200+250;
		int w=(50+this.getHeight()-100)/2;
		
		 int r=(this.getWidth()-500)/2+250;
		int d=(this.getHeight()-500)+250;
		g.drawLine(r,50,r,250);
		g.drawLine(r,d,r,d+200);
		g.drawLine(50,w,250,w);
		g.drawLine(s,w,s1,w);
	}
   	public static void main(String args[])
	{
       		new gameNew();
       	}
}



