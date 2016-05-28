package dad.urjc.model;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

@Entity
public class Item implements com.hazelcast.nio.serialization.DataSerializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int quantity;

	public Item() {
	}

	public Item(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public void readData(ObjectDataInput in) throws IOException {
		// lectura de datos
		long id = in.readLong();
		String name = in.readUTF();
		int quantity = in.readInt();

		this.setId(id);
		this.setName(name);
		this.setQuantity(quantity);
	}

	@Override
	public void writeData(ObjectDataOutput out) throws IOException {
		// datos que vamos a serilizar
		long id = this.getId();
		String name = this.getName();
		int quantity = this.getQuantity();

		// serializacion
		out.writeLong(id);
		out.writeUTF(name);
		out.writeInt(quantity);
	}
}
