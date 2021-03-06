package voya.gemfire.core.cache.manager;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnServer;

@OnServer(pool="pool")
public interface FunctionExecution {

	@FunctionId("CreateRegion")
	boolean createRegion(String cacheName);

}

