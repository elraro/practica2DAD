package dad.urjc.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import dad.urjc.model.Lista;

@CacheConfig(cacheNames="listas")
public interface NoMeOlvidesRepository extends JpaRepository<Lista, Long> {
	
	@CacheEvict(allEntries=true)
	Lista save(Lista lista);
	@Cacheable
	List<Lista> findByUser(String user);
	@Cacheable
	List<Lista> findByUserAndName(String user, String name);
	@Cacheable
	List<Lista> findAll();
}
