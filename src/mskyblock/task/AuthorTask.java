package mskyblock.task;

import cn.nukkit.Server;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.TextFormat;
import mskyblock.Main;

public class AuthorTask extends PluginTask<Main> {

	public AuthorTask(Main owner) {
		super(owner);
	}

	@Override
	public void onRun(int currentTick) {
		Server.getInstance().broadcastMessage(TextFormat.DARK_AQUA + "이 서버는 MCFT서버(mcft.ourplan.kr) 의 어드민인 마루님이 만든 Mskyblock 플러그인을 사용중입니다."); 
	}
}
