package fu7omc1112;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSONReadFU7OMC {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/main/resources/orarendFU7OMC.JSON")){
            JSONParser parser = new JSONParser();
            JSONObject obejct = (JSONObject) parser.parse(reader);

            JSONObject root = (JSONObject) obejct.get("SZM_orarend");
            JSONArray arr = (JSONArray) root.get("ora");

            System.out.println("Mérnökinformatikus órarend 2024/25 I. félév:\n");

            for(int i = 0; i < arr.size(); i++) {
                JSONObject ora = (JSONObject) arr.get(i);
                JSONObject ido = (JSONObject) ora.get("idopont");
                System.out.println("\nTárgy: " + ora.get("targy"));
                System.out.println("Időpont: " + ido.get("nap") + ":" + ido.get("tol") + " - " + ido.get("ig"));
                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}