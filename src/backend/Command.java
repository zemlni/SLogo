package backend;

public abstract class Command implements CommandInterface {
	
	private int numArgs;
	@Override
	public abstract double execute();
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumArgs() {
		return numArgs;
	}
	protected void setNumArgs(int numArgs){
		this.numArgs = numArgs;
	}
}
