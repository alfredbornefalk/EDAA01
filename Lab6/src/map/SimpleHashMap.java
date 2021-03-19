package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}
		
		@Override
		public K getKey() {
			return key;
		}
		
		@Override
		public V getValue() {
			return value;
		}
		
		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
		
		@Override
		public String toString() {
			return key + "=" + value;
		}
	}
	
	private int capacity = 16;
	private int size = 0;
	private final double LOAD_FACTOR = .75;
	private Entry<K, V>[] table;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
		and the defauly load factor (0.75). */
	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[capacity];
	}
	
	/**	Constructs an empty hashmap with the specified initial capacity
		and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[capacity];
	}
	
	@Override
	/** Om nyckeln inte finns returneras null */
	public V get(Object arg0) {
		Entry<K, V> node = find(index((K) arg0), (K) arg0);
		
		if (node != null) {
			return node.getValue();
		}
		
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	/** Om det fanns ett gammalt värde skall detta returneras; annars
		returneras null */
	public V put(K arg0, V arg1) {
		Entry<K, V> node = find(index(arg0), arg0);
		
		if (node == null) {
			node = table[index(arg0)];
			
			if (node == null) {
				table[index(arg0)] = new Entry<K, V>(arg0, arg1);
			} else {
				while (node.next != null) {
					node = node.next;
				}
				
				node.next = new Entry<K, V>(arg0, arg1);
			}
		} else {
			V oldValue = node.getValue();
			node.setValue(arg1);
			return oldValue;
		}
		
		size++;
		
		if ((double) size / (double) capacity >= LOAD_FACTOR) {
			rehash();
		}
		
		return null;
	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		Entry<K, V> node = find(index, key);
		
		while (node != null) {
			if (node.getKey().equals((K) arg0)) {
				table[index] = node.next;
				size--;
				return node.getValue();
			} else if (node.next != null && node.next.key.equals(key)) {
				V value = node.next.getValue();
				node.next = node.next.next;
				size--;
				return value;
			}
			
			node = node.next;
		}
		
		return null;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	public String show() {
		StringBuilder sb = new StringBuilder();
		Entry<K, V> node = null;
		
		for (int i = 0; i < table.length; i++) {
			sb.append(i + "\t{");
			node = table[i];
			
			if (node != null) {
				while (node.next != null) {
					sb.append(node.toString() + ", ");
					node = node.next;
				}
				
				sb.append(node.toString());
			}
			
			sb.append("}\n");
		}
		
		return sb.toString();
	}
	
	/* Returnerar det index som skall användas för nyckeln key. */
	private int index(K key) {
		return Math.abs(key.hashCode() % table.length);
	}
	
	/* Returnerar det Entry-par som har nyckeln key i listan som finns
	 * på position index i tabellen. Om det inte finns något sådant skall
	 * null returneras. */
	private Entry<K, V> find(int index, K key) {
		Entry<K, V> node = table[index];
		
		while (node != null && !node.key.equals(key)) {
			node = node.next;
		}
		
		return node;
	}
	
	private void rehash() {
		int oldCapacity = capacity;
		Entry<K, V>[] oldTable = table;
		capacity *= 2;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;		
		Entry<K, V> node = null;
		
		for (int i = 0; i < oldCapacity; i++) {
			node = oldTable[i];
			
			while(node != null) {
				put(node.getKey(), node.getValue());
				node = node.next;
			}
		}
		
		oldTable = null;
	}
}