package holder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Holder<T> implements IHolderAction, Serializable {

    protected T collection;

    @Override
    public T getCollection() {
        return collection;
    }

    @Override
    public final void writeToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(collection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
