import controller.AtmManager;

public class Main {
	public static void main(String[] args) {
		AtmManager am = AtmManager.getInstance();

		am.run();
	}
}
