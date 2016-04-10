package mskyblock.generator;

import java.util.Map;

import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

public class SkyblockGenerator extends Generator {
	
	public static final int TYPE_SKYBLOCK = 3;
	
	private ChunkManager level;
	private NukkitRandom random;
	private Map<String, Object> options;

	
	public SkyblockGenerator(Map<String, Object> options) {
		this.options = options;
	}
	
	@Override
	public int getId() {
		return TYPE_SKYBLOCK;
	}

	@Override
	public void init(ChunkManager level, NukkitRandom random) {
		this.level = level;
		this.random = random;
	}

	@Override
	public void generateChunk(int chunkX, int chunkZ) {
		
	}

	@Override
	public void populateChunk(int chunkX, int chunkZ) {
		// TODO 자동 생성된 메소드 스텁
		
	}

	@Override
	public Map<String, Object> getSettings() {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public String getName() {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public Vector3 getSpawn() {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public ChunkManager getChunkManager() {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

}
