package mskyblock.skyblock;

import cn.nukkit.level.Position;

public class Area {
	private Position startPos, endPos;
	
	public Area(Position startPos, Position endPos) {
		if (startPos.getLevel() != endPos.getLevel()) {
			throw new IllegalArgumentException("different level");
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
		bigX = Math.max(startPos.x, endPos.x);
		smallX = Math.min(startPos.x, endPos.x);
		return pos.x < bigX && pos.x > smallX;
	}
	public boolean isZinside(Position pos) {
		double bigZ, smallZ;
		bigZ = Math.max(startPos.z, endPos.z);
		smallZ = Math.min(startPos.z, endPos.z);
		return pos.z < bigZ && pos.z > smallZ;
	}
	
	public Position getStartPos() {
		return startPos;
	}
	public Position getEndPos() {
		return endPos;
	}
}