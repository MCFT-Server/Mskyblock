package mskyblock.generator;

import java.util.Map;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockSapling;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.level.generator.biome.Biome;
import cn.nukkit.level.generator.object.tree.ObjectTree;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;
import mskyblock.DataBase;

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
		BaseFullChunk chunk = level.getChunk(chunkX, chunkZ);
		if (chunkX % 20 == 0 && chunkZ % 20 == 0) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					chunk.setBlock(x, 0, z, Block.BEDROCK);
					for (int y = 1; y <= 3; y++) {
						chunk.setBlock(x, y, z, Block.STONE);
					}
					chunk.setBlock(x, 4, z, Block.DIRT);
					chunk.setBlock(x, 5, z, Block.GRASS);
				}
			}
			ObjectTree.growTree(level, chunkX*16 + 8, 6, chunkZ*16 + 8, random, BlockSapling.OAK);
			if (DataBase.getInstance().config.getBoolean("create-sponge", false)) {
				chunk.setBlock(3, 6, 3, Block.SPONGE);
				chunk.setBlock(3, 7, 3, Block.STONE);
			}
		}
	}

	@Override
	public void populateChunk(int chunkX, int chunkZ) {
        for (int x = 0; x < 16; x++) {
        	for (int z = 0; z < 16; z++) {
        		level.getChunk(chunkX, chunkZ).setBiomeColor(x, z, 133, 188, 86);
        	}
        }
	}

	@Override
	public Map<String, Object> getSettings() {
		return options;
	}

	@Override
	public String getName() {
		return "skyblock";
	}

	@Override
	public Vector3 getSpawn() {
		return new Vector3(0, 7, 1);
	}

	@Override
	public ChunkManager getChunkManager() {
		return level;
	}

}
