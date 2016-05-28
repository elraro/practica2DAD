package dad.urjc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.spring.cache.HazelcastCache;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@RestController
public class CacheController {
	
	private static final Log LOG = LogFactory.getLog(CacheController.class);
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value="/cache", method=RequestMethod.GET)
	public Object getCacheContent() {
		LOG.info("GET /cache");
		HazelcastCacheManager cacheMgr = (HazelcastCacheManager) cacheManager;
		HazelcastCache cache = (HazelcastCache) cacheMgr.getCache("listas");
		return cache.getNativeCache();
	}

}
