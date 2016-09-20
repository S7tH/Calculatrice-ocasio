import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class Calculette extends JFrame
{
	private JPanel pan = new JPanel();
	private JPanel ecran = new JPanel();
	private JLabel affichage = new JLabel("0");
	private double chiffre1 = 0;
	private String operator = "";
	private boolean clic = false;
	private boolean clicOperateur = false;
	
	
	
	
	public Calculette()
	{
		
		//config de ma JFrame
		this.setTitle("OCASIO");
		this.setSize(250, 265);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initPan();
		this.setVisible(true);
		
		
	}
	
	public void initPan()
	{
		//on configure le Layout de pan le conteneur de nos élements.
		pan.setLayout(new BorderLayout());		
		
		//numerateurs
		JPanel num = new JPanel();
		String[] numerateurs = {"1","2","3","4","5","6","7","8","9","0",".","="};
		JButton[] number = new JButton[numerateurs.length];
		
		for(int i = 0; i < numerateurs.length; i++)
		{
			number[i] = new JButton(numerateurs[i]);
			number[i].setPreferredSize(new Dimension(52, 45));
			
			switch(i)
			{
			
			case 11 :
				
				number[i].addActionListener(new EgalListener());
				num.add(number[i]);
				
			break;
			
			default :
				
				number[i].addActionListener(new ChiffreListener());
				num.add(number[i]);
				
			break;
			
			}
	
		}
		
		//config du panneau num
		num.setPreferredSize(new Dimension(200, 250));
		num.setBackground(Color.BLACK);
		pan.add(num, BorderLayout.CENTER);
		
		
		//operateurs
		JPanel ope = new JPanel();
		String[] operateurs = {"C","+","-","*","/"};
		JButton[] oper = new JButton[operateurs.length];
		
		
		for(int i = 0; i < operateurs.length; i++)
		{
			oper[i] = new JButton(operateurs[i]);
			oper[i].setPreferredSize(new Dimension(42, 35));
			
			switch(i)
			{
			
			case 0 :
				oper[i].setForeground(Color.red);
				oper[i].addActionListener(new ResetListener());
				ope.add(oper[i]);
				
			break;
			
			default :
				
				oper[i].addActionListener(new OperateursListener());
				ope.add(oper[i]);
				
			break;
			
			}
			

			
		}
		
		ope.setPreferredSize(new Dimension(50, 200));
		ope.setBackground(Color.BLUE);
		pan.add(ope, BorderLayout.EAST);
	
		//ecran
		ecran.setBackground(Color.GREEN);
		
		//affichage de l'ecran
		ecran.setBorder(BorderFactory.createLineBorder(Color.black));
		ecran.add(affichage);
		
		pan.add(ecran, BorderLayout.NORTH);
		

	
		this.setContentPane(pan);
	}
	
	public void calcul()
	{
	
	
		
		switch(operator)
		{
		case "+":
			
			
			chiffre1  += Double.valueOf(affichage.getText()).doubleValue();
			
			affichage.setText(String.valueOf(chiffre1));
			System.out.println("ch1 vaut" + chiffre1);
		
			break;
			
		case "-":
			chiffre1 -= Double.valueOf(affichage.getText()).doubleValue();
			
			affichage.setText(String.valueOf(chiffre1));
			break;
		
		case "*":
			chiffre1 *= Double.valueOf(affichage.getText()).doubleValue();
			
			affichage.setText(String.valueOf(chiffre1));
			break;
		
		case "/":
			try
			{
				chiffre1 /= Double.valueOf(affichage.getText()).doubleValue();
			
				affichage.setText(String.valueOf(chiffre1));
			} 
			catch(ArithmeticException e) 
			{
			     affichage.setText("0");
			}
			break;
			
		}


	}
	
	
	//Listener utilisé pour les chiffres
	  //Permet de stocker les chiffres et de les afficher
	  class ChiffreListener implements ActionListener 
	  {
	    public void actionPerformed(ActionEvent e)
	    {
	      //On affiche le chiffre additionnel dans le label
	      String str = ((JButton)e.getSource()).getText();
	   
	     
	      if(clic)
	      {
	    	  clic = false;
	      }
	      else
	      {
	      
	        if(!affichage.getText().equals("0"))
	          str = affichage.getText() + str;
	      }
	      
	      affichage.setText(str);

	    }
	  }
	  
	  class EgalListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		    {
			 calcul();
			 clic = true;
			 clicOperateur = false;
			
		    }
	  }
	  
	  class ResetListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		    {
			  clicOperateur = false;
			  chiffre1 = 0;
			  operator ="";
			  affichage.setText("0");
			  clic = false;
		    }
	  }
	  
	  class OperateursListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		    {
			  if(clicOperateur)
			  {
			        calcul();
			     
			  }
			  else
		      {
		        chiffre1 = Double.valueOf(affichage.getText()).doubleValue();
		        System.out.println(chiffre1);
		        clicOperateur = true;
		      }
			  
			  operator = ((JButton)e.getSource()).getText();
			
			  
			  clic = true;
 
			  
		    }
	  }
	  
	
	public static void main(String[] args) 
	{
		Calculette on = new Calculette();
	}
}


	
