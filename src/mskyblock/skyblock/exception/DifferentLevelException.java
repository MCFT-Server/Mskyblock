package mskyblock.skyblock.exception;

@SuppressWarnings("serial")
public class DifferentLevelException extends Exception {
	@Override
	public String toString() {
		return "매개변수로 받은 두 Position 인스턴스의 Level 인스턴스가 다릅니다.";
	}
}
