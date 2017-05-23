package users;

import java.io.Serializable;

public class Service implements Serializable{
	private static final long serialVersionUID = 9L;
	String serviceName;
	int blocks;
	
	public Service(String serviceName, int blocks){
		this.serviceName = serviceName;
		this.blocks = blocks;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}
}
