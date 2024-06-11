import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
    private LinkedList<Data>[] table; // Array of linked lists to store data
    private int size; // Number of elements in the dictionary

    // Constructor to initialize the hash table with the specified size
    public HashDictionary(int size) {
        table = new LinkedList[size];
        this.size = 0;
    }

    // Method to add a record to the dictionary
    @Override
    public int put(Data record) throws DictionaryException {
        int index = hash(record.getConfiguration()); // Calculate hash index
        if (table[index] == null) { // If the index is empty, create a new linked list
            table[index] = new LinkedList<>();
        } else { // If the index is not empty, check for existing configuration
            for (Data data : table[index]) {
                if (data.getConfiguration().equals(record.getConfiguration())) {
                    throw new DictionaryException(); // Throw exception if configuration already exists
                }
            }
        }
        table[index].add(record); // Add record to the linked list at the calculated index
        size++; // Increment the size of the dictionary
        return table[index].size() > 1 ? 1 : 0; // Return 1 if collision occurred, else 0
    }

    // Method to remove a record from the dictionary
    @Override
    public void remove(String config) throws DictionaryException {
        int index = hash(config); // Calculate hash index
        if (table[index] != null) { // If the index is not null
            for (Data data : table[index]) {
                if (data.getConfiguration().equals(config)) { // Find the record with matching configuration
                    table[index].remove(data); // Remove the record
                    size--; // Decrement the size of the dictionary
                    return;
                }
            }
        }
        throw new DictionaryException(); // Throw exception if record not found
    }

    // Method to get the score of a configuration from the dictionary
    @Override
    public int get(String config) {
        int index = hash(config); // Calculate hash index
        if (table[index] != null) { // If the index is not null
            for (Data data : table[index]) {
                if (data.getConfiguration().equals(config)) { // Find the record with matching configuration
                    return data.getScore(); // Return the score of the record
                }
            }
        }
        return -1; // Return -1 if configuration not found
    }

    // Method to get the number of records in the dictionary
    @Override
    public int numRecords() {
        return size; // Return the size of the dictionary
    }

    // Method to calculate the hash index for a given key (configuration)
    private int hash(String key) {
        // Implement your hash function here
        // For example, a simple hash function could be:
        return Math.abs(key.hashCode() % table.length); // Return the absolute value of hashCode modulo table length
    }
}