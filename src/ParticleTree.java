import java.io.*;
import java.util.ArrayList;

public class ParticleTree {
	private ParticleNode root;
	
	public ParticleTree(File dirt){
		try {
			FileInputStream fis = new FileInputStream(dirt);
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader stdin = new BufferedReader(inStream);
			//String[] placer = new String[3];
			ParticleNode cursor = new ParticleNode();
		
				while(stdin.ready()){
					String [] placer = stdin.readLine().split("\\s+");
					if (this.isEmpty()){ // root 
						root = new ParticleNode(placer[0]);
						root.setLeft(new ParticleNode(placer[1]));
						root.setRight(new ParticleNode(placer[2]));
					} else {
						cursor = findParticle(placer[0]);
						cursor.setLeft(new ParticleNode(placer[1]));
						cursor.setRight(new ParticleNode(placer[2]));
					} 
				} root.setDepth(0); 
				if(this.isEmpty())
					System.out.print("empty");
		} catch (FileNotFoundException f){System.out.print("File not found");}
		catch (IOException i){System.out.print("IO Exception");}
	}
	
	public int depth(String particleName){// returns the depth of the node
		if(root.findParticle(particleName) != null)
			return root.findParticle(particleName).getDepth();
		else return -1;
	}
	
	
	public void printAllParticles(){
		// inorder
		root.printAllParticles();
	}
	
	public int depth(){ // returns depth of tree
		return root.deepestLevel();
		
	}
	
	public boolean isEmpty(){
		return (this.root == null);
	}
	
	public ArrayList<ArrayList<String>> getDecayModes(String particleName){
		return root.findParticle(particleName).getDecayModes();
		
	}
	
	public ParticleNode findParticle(String particleName)throws IllegalArgumentException{
		if (root.findParticle(particleName) == null)
			throw new IllegalArgumentException();
		else return root.findParticle(particleName);
		
	}

}
