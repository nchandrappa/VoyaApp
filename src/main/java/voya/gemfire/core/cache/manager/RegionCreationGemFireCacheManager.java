package voya.gemfire.core.cache.manager;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.data.gemfire.GemfireSystemException;
import org.springframework.data.gemfire.support.GemfireCacheManager;

/**
 * The RegionCreationCacheManager class is a GemfireCacheManager extension and implementation of the Spring Cache management
 * abstraction for caching the results of Service method calls.
 *
 * @author jb
 * @see org.springframework.cache.Cache
 * @see org.springframework.data.gemfire.support.GemfireCacheManager
 * @see com.voya.core.functions.VersionAwareRegionGetFunction
 */
public class RegionCreationGemFireCacheManager extends GemfireCacheManager {

//	protected final Logger log = Logger.getLogger(getClass().getName());
	protected final Logger log = LoggerFactory.getLogger(getClass().getName());

	@Resource
	RegionCreator regionCreator;

    public void setRegionCreator(RegionCreator regionCreator) {
	      this.regionCreator = regionCreator;
	}

	@PostConstruct
	public void init() {
	  regionCreator.init();
	  log.info(String.format("%1$s initialized!", getClass().getSimpleName()));
	}

	@Override
	public Cache getCache(String cacheName) throws GemfireSystemException{
		Cache cache = super.getCache(cacheName);

		if(cache == null) {
			cache = regionCreator.createRegion(cacheName);
			addCache(cache);
		}

		return cache;
	}
}
