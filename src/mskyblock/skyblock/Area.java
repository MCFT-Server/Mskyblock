package mskyblock.skyblock;

import cn.nukkit.level.Position;

public class Area {
	private Position startPos, endPos;
	
	public Area(Position startPos, Position endPos) throws DifferentLevelException {
		if (startPos.getLevel() != endPos.getLevel()) {
			throw new DifferentLevelException();
		}
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	public double getSize() {
		return Math.abs(endPos.getX() - startPos.getX()) * Math.abs(endPos.getZ() - startPos.getZ());
	}
	
	public boolean isInside(Position pos) {
		if (startPos.getX() > endPos.getX()) {
			if (startPos.getZ() > endPos.getZ()) {
				if (pos.getX() <= startPos.getX() && pos.getX() >= endPos.getX()) {
					if (pos.getZ() <= startPos.getZ() && pos.getZ() >= endPos.getZ()) return true;
					else return false;
				} else {
					return false;
				}
			} else {
				if (pos.getX() <= startPos.getX() && pos.getX() >= endPos.getX()) {
					if (pos.getZ() <= endPos.getZ() && pos.getZ() >= startPos.getZ()) return true;
					else return false;
				} else {
					return false;
				}
			}
		} else {
			if (startPos.getZ() > endPos.getZ()) {
				if (pos.getX() >= startPos.getX() && pos.getX() <= endPos.getX()) {
					if (pos.getZ() <= startPos.getZ() && pos.getZ() >= endPos.getZ()) return true;
					else return false;
				} else {
					return false;
				}
			} else {
				if (pos.getX() >= startPos.getX() && pos.getX() <= endPos.getX()) {
					if (pos.getZ() <= endPos.getZ() && pos.getZ() >= startPos.getZ()) return true;
					else return false;
				} else {
					return false;
				}
			}
		}
	}
	public Position getStartPos() {
		return startPos;
	}
	public Position getEndPos() {
		return endPos;
	}
}

@SuppressWarnings("serial")
class DifferentLevelException extends Exception {
	@Override
	public String toString() {
		return "매개변수로 받은 두 Position 인스턴스의 Level 인스턴스가 다릅니다.";
	}
}