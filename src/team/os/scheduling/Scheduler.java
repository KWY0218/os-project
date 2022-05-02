package team.os.scheduling;

import java.util.List;

import team.os.model.Core;
import team.os.model.History;
import team.os.model.Process;

public interface Scheduler {
	public History schedule(List<Process> mProcessList, List<Core> mCoreList);
}
