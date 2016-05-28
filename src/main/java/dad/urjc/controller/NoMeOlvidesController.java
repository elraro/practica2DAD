package dad.urjc.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dad.urjc.model.Lista;
import dad.urjc.repository.NoMeOlvidesRepository;

@RestController
public class NoMeOlvidesController {

	@Autowired
	private NoMeOlvidesRepository repository;
	
	private static final Log LOG = LogFactory.getLog(NoMeOlvidesController.class);

	@RequestMapping(value = "/listas", method = RequestMethod.GET)
	public ResponseEntity<List<Lista>> getAllLista() {
		LOG.info("GET /listas");
		return new ResponseEntity<List<Lista>>(repository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/listas/{user}", method = RequestMethod.GET)
	public ResponseEntity<List<Lista>> getListaByUser(@PathVariable(value = "user") String user) {
		List<Lista> lista = repository.findByUser(user);
		LOG.info("GET /listas/" + user);
		if (lista == null) {
			return new ResponseEntity<List<Lista>>(lista, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Lista>>(lista, HttpStatus.OK);
	}

	@RequestMapping(value = "/listas/{user}/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Lista>> getListaByUserAndName(@PathVariable(value = "user") String user, @PathVariable(value = "name") String name) {
		List<Lista> lista = repository.findByUserAndName(user, name);
		LOG.info("GET /listas/" + user + "/" + name);
		if (lista == null) {
			return new ResponseEntity<List<Lista>>(lista, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Lista>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listas", method = RequestMethod.POST)
	public ResponseEntity<Lista> addLista(@RequestBody Lista lista) {
		repository.save(lista);
		LOG.info("POST /listas");
		return new ResponseEntity<Lista>(lista, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/listas/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> modifyLista(@PathVariable(value = "id") long id, @RequestBody Lista lista) {
		Lista listaDb = repository.findOne(id);
		LOG.info("PUT /listas");
		if (listaDb == null) {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
		repository.save(lista);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listas/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteLista(@PathVariable(value = "id") long id) {
		Lista listaDb = repository.findOne(id);
		LOG.info("DELETE /listas/" + id);
		if (listaDb == null) {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
		repository.delete(listaDb);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}
}

