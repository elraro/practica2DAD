package dad.urjc.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

@Entity
public class Lista implements com.hazelcast.nio.serialization.DataSerializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String user;
	private String name;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Item> items;

	public Lista() { }
	
	public Lista(String user, String name, List<Item> items) {
		this.user = user;
		this.name = name;
		this.items = items;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		// lectura de datos
		long id = in.readLong();
		String user = in.readUTF();
		String name = in.readUTF();
		int numItems = in.readInt();
		List<Item> items = new ArrayList<>();
		for (int i = 0; i < numItems; i++) {
			items.add(in.readObject());
		}

		this.setId(id);
		this.setUser(user);
		this.setName(name);
		this.setItems(items);
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		long id = this.getId();
		String user = this.getUser();
		String name = this.getName();
		List<Item> items = this.getItems();
		int numItems = items.size();
		// serializacion
		out.writeLong(id);
		out.writeUTF(user);
		out.writeUTF(name);
		out.writeInt(numItems); // wtf?
		for (Item item : items) {
			out.writeObject(item);
		}
	}

}
