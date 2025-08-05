package storage;
import model1.Expense;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseStorage {
    private final String FILE_NAME = "expenses.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Expense> load() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return new ArrayList<>();
            CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Expense.class);
            return mapper.readValue(file, type);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void save(List<Expense> expenses) {
        try {
            mapper.writeValue(new File(FILE_NAME), expenses);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
