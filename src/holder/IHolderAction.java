package holder;

public interface IHolderAction<T> {

    T getCollection();

    void writeToFile(String fileName);

    void readFromFile(String fileName);

    void generifyReport();

}
