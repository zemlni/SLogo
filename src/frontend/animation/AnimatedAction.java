package frontend.animation;

import java.lang.reflect.Method;

import frontend.app.FrontEndController;

public class AnimatedAction {
	
	private FrontEndController frontEndController;
	private String methodName;
	private Object[] args;
	
	public AnimatedAction(FrontEndController frontEndController,
			String methodName, Object[] args) {
		this.frontEndController = frontEndController;
		this.methodName = methodName;
		this.args = args;
	}
	
	public void execute() {
		System.out.println("action execute called: "+methodName);
		try {
			Class<?>[] paramTypes = new Class<?>[4];
			for (int i = 0; i < 4; i++) {
				paramTypes[i] = Double.TYPE;
			}
			Method method = frontEndController.getClass().getMethod(methodName, paramTypes);
			method.invoke(frontEndController, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
