package mskyblock.skyblock;

import cn.nukkit.level.Position;
import mskyblock.skyblock.exception.DifferentLevelException;

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
		if (isXinside(pos) && isZinside(pos)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isXinside(Position pos) {
		double bigX, smallX;
		bigX = (startPos.x > endPos.x) ? startPos.x : endPos.x;
		smallX = (bigX == startPos.x) ? endPos.x : startPos.x;
		if (pos.x < bigX && pos.x > smallX) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isZinside(Position pos) {
		double bigZ, smallZ;
		bigZ = (startPos.z > endPos.z) ? startPos.z : endPos.z;
		smallZ = (bigZ == startPos.z) ? endPos.z : startPos.z;
		if (pos.z < bigZ && pos.z > smallZ) {
			return true;
		} else {
			return false;
		}
	}
	
	public Position getStartPos() {
		return startPos;
	}
	public Position getEndPos() {
		return endPos;
	}
}