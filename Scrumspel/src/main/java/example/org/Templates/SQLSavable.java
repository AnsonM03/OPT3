package example.org.Templates;

import java.util.List;

public interface SQLSavable {
    String getTableName();
    List<String> getColumnNames();
    List<Object> getValues();
}
