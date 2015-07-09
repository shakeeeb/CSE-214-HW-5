import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ParticleDriver  extends JFrame{
	
	JButton buildATree = new JButton("Build a Tree from a File");
	JButton findAParticle = new JButton("Find a PArticle in your Tree");
	JButton printAllParticles = new JButton("Print your Particle Tree");
	JButton findDepth = new JButton("Find the Depth of your Tree");
	JButton decayPermutations = new JButton("Display all Decay Modes");
	
	ParticleTree Ygdrassil;
	
	
	public ParticleDriver(){
		this.setSize(200,600);
		this.setLayout(new GridLayout(5,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ParticleTree particleTree = new ParticleTree(bleh);
		this.add(buildATree); 
		this.add(findAParticle); 
		this.add(printAllParticles);
		this.add(findDepth);
		this.add(decayPermutations);
		
		// activate will be my action listener method
		buildATree.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(1);}});
		findAParticle.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(2);}});
		printAllParticles.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(3);}});
		findDepth.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(4);}});
		decayPermutations.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(5);}});
		
		setVisible(true);
	}
	
	public void activate(int option){
		switch(option){
		case 1: //build a tree have to pass in a file somehow
			File file = new File("src/testing.txt"/*JOptionPane.showInputDialog("Please give the Absolute Path for the File you'd Like to open: ")*/);
			Ygdrassil = new ParticleTree(file);
			JOptionPane.showMessageDialog(null, "a Particle Tree has been created from the file at the given path");
			break;
		case 2: // find a particle and print it
			String particleName = JOptionPane.showInputDialog("What is the name of the particle you are searching for? ");
			ParticleNode party = Ygdrassil.findParticle(particleName);
			if(party.getLeft() != null && party.getRight() != null)
				JOptionPane.showMessageDialog(null, "The " + party.toString() + "Particle has been found. it's constituents are " + party.getLeft().toString() + " and " + party.getRight().toString());
			else if (party == null) // error message
				JOptionPane.showMessageDialog(null, "That Particle does not exist you fool");
			else // found, but no children
				JOptionPane.showMessageDialog(null, "The particle" + party.toString() + "has been found. It has no constituents");
			break;
		case 3: // printAllParticles
			Ygdrassil.printAllParticles();
			JOptionPane.showMessageDialog(null,"The particles have been printed to the console");
			break;
		case 4: //find depth
			JOptionPane.showMessageDialog(null,"The depth of the deepest Node in this Tree is " + Ygdrassil.depth());
			break;
		case 5: //decay permutations
			String printout = "";
			particleName = JOptionPane.showInputDialog("What is the name of the particle you are searching for? ");
			ArrayList<ArrayList<String>> massive = Ygdrassil.getDecayModes(particleName);
			for (ArrayList<String> list : massive){
				for (String mode : list){
					printout += mode + "\n";
				} printout += "\n" ;
			}
			JOptionPane.showMessageDialog(null,"These are the Decay Modes:\n" + printout);
			break;
		}
		}
		
		
	public static void main (String[] args){
		ParticleDriver driver = new ParticleDriver();
	}
	
	

}
