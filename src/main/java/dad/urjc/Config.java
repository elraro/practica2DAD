package dad.urjc;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
public class Config {
	@Bean(destroyMethod = "shutdown")
	public HazelcastInstance hazelcast() {
		return Hazelcast.newHazelcastInstance();
	}

	@Bean
	public CacheManager cacheManager() {
		return new HazelcastCacheManager(hazelcast());
	}
}