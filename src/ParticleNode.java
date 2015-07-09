import java.util.ArrayList;

public class ParticleNode {
	
	private String data;
	private ParticleNode left;
	private ParticleNode right;
	private int depth;
	
	ParticleNode(String data){
		this.data = data;
		left = null;
		right = null;
	}
	
	ParticleNode(){
		left = null;
		right = null;
	}
	
	public void setLeft(ParticleNode left){
		this.left = left;
	}
	
	public void setRight(ParticleNode right){
		this.right = right;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public String getData(){
		return data;
	}
	
	public ParticleNode getLeft(){
		return left;
	}
	
	public ParticleNode getRight(){
		return right;
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public void setDepth(int level){ // always call with zero
		this.depth = level;
		if(left != null)
			left.setDepth(level+1);
		if(right != null)
			right.setDepth(level+1);
	}
	
	public int deepestLevel(){
		int lDepth = 0; 
		int rDepth = 0;
		if (left != null)
			lDepth = left.deepestLevel();
		if (right != null)
			rDepth = right.deepestLevel();
		if (lDepth > rDepth) // three way comparison between this depth and depth of left and right
			if (this.depth > lDepth)
				return this.depth;
			else 
				return lDepth;
		else
			if (this.depth > rDepth)
				return this.depth;
			else
				return rDepth;
	}
	
	public void printAllParticles(){
		//inorder?? no, postorder
		//but combines errything at the end of the processing
		String endResult = ""; 
		String leftSide = "";
		String rightSide = "";
		if(left != null)
			leftSide = left.printAllParticles("");
		if(right != null)
			rightSide = right.printAllParticles("");
		endResult = data + " " + leftSide + " " + rightSide + "\n";
		System.out.print(endResult);
	}
	
	public String printAllParticles(String id){
		String endResult = ""; 
		String leftSide = "";
		String rightSide = "";
		if(left == null && right == null)
			return this.data;
		if(left != null)
			leftSide = left.printAllParticles("");
		if(right != null)
			rightSide = right.printAllParticles("");
		endResult = data + " " + leftSide + " " + rightSide + "\n";
		System.out.print(endResult);
		return data;
	}
	
	public ArrayList<ArrayList<String>> getDecayModes(){
		ArrayList<String> DecayModesL = left.getDecayModes("");
		ArrayList<String> DecayModesR = right.getDecayModes("");
		ArrayList<String> holder = new ArrayList();
		
		ArrayList<ArrayList<String>> allDecayModes = new ArrayList(); // there are three lists
		for(String mode : DecayModesL){ // first list
			holder.add(mode.concat(left.getData()));
		} allDecayModes.add(holder);
		holder.clear();
		for(String mode : DecayModesR){
			holder.add(mode.concat(right.getData()));
		} allDecayModes.add(holder);
		holder.clear();
		for(String modeL : DecayModesL){
			for(String modeR : DecayModesR)
				holder.add(modeL.concat(modeR));
		}allDecayModes.add(holder);
		return allDecayModes;		
	}
	
	public ArrayList<String> getDecayModes(String diff){
		ArrayList<String> result = new ArrayList();
		result.add(data);
		ArrayList<String> holderL = new ArrayList();
		ArrayList<String> holderR = new ArrayList();
		if(left != null)
			holderL = left.getDecayModes("");
		if(right != null)
			holderR = right.getDecayModes("");
		for (String model: holderL)
			for (String moder : holderR)
				result.add(model + " " + moder);
		return result;
	}
	
	public ParticleNode findParticle(String particleName){
		if (this.data.equals(particleName))
			return this;
		if (left != null)
			return left.findParticle(particleName);
		if (right != null)
			return right.findParticle(particleName);
		else return null;
	}
	
	public String toString(){
		return data;
	}

}
