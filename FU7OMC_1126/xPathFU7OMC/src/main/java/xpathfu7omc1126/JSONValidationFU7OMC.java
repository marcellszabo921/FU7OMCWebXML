package xpathfu7omc1126;

import java.io.FileInputStream;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONValidationFU7OMC {

    public static void main(String[] args) {
        String schemaPath = "src/main/resources/orarendFU7OMCSchema.json";
        String jsonPath = "src/main/resources/orarendFU7OMC.json";
        {

            try(InputStream schemaStream = new FileInputStream(schemaPath);
                InputStream jsonStream = new FileInputStream(jsonPath)) {

                JSONObject schemaJson = new JSONObject(new JSONTokener(schemaStream));
                Schema schema = SchemaLoader.load(schemaJson);

                JSONObject json = new JSONObject(new JSONTokener(jsonStream));

                schema.validate(json);
                System.out.println("Validation");

            }catch(Exception e) {
                System.out.println("Hiba történt a validáció során.");
                System.out.println(e.getMessage());
            }
        }
    }

}
