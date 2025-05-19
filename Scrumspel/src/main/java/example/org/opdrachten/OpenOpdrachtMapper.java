package example.org.opdrachten;

import example.org.Templates.Mapper;
import example.org.Templates.Opdracht;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OpenOpdrachtMapper implements Mapper<Opdracht> {
    @Override
    public Opdracht mapRow(ResultSet rs) throws SQLException {
        return new OpenOpdracht(
//                rs.getInt("id"),
                rs.getString("vraag"),
                rs.getString("antwoord")
        );
    }

    @Override
    public String getTableName() {
        return "open_opdrachten";
    }
}
