package action;

public interface IDataManagement<T> {

    String getInfo(T obj);

    void redact(T obj);

    T create();

    String getReportInfo(T obj);

    String getFullReport();

    void delete(T obj);

}
