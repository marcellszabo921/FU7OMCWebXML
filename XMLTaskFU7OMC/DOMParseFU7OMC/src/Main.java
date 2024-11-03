import hu.domparse.fu7omc.DOMReadFU7OMC;
import hu.domparse.fu7omc.DOMWriteFU7OMC;
import hu.domparse.fu7omc.DOMModifyFU7OMC;
import org.w3c.dom.Document;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "src/resources/XMLFU7OMC.xml";

        DOMReadFU7OMC dm = new DOMReadFU7OMC(path);
        Document dom = dm.buildUpDom();
        DOMWriteFU7OMC write = new DOMWriteFU7OMC(dom.getDocumentElement());
        DOMModifyFU7OMC modify = new DOMModifyFU7OMC(dom); // Példányosítás a módosító osztályhoz

        Scanner scanner = new Scanner(System.in); // Scanner a felhasználói bemenethez

        try {
            write.printXmlStructure(dom.getDocumentElement(), "");
            System.out.print("Szeretnéd frissíteni az adatokat? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("y")) {
                System.out.print("Kérlek add meg a gyárkódot: ");
                String gyarKod = scanner.nextLine().trim();

                // Kérjük be az új adatokat
                System.out.print("Új név (hagyj üresen, ha nem akarod frissíteni): ");
                String newNev = scanner.nextLine();

                System.out.print("Új város (hagyj üresen, ha nem akarod frissíteni): ");
                String newVaros = scanner.nextLine();

                // Módosítás végrehajtása
                modify.modifyGyarKod(gyarKod, newNev, newVaros);
                write.writeXmlFile(dom, "src/resources/XMLFU7OMC.xml");
            } else if (response.equals("n")) {
                System.out.println("Kilépés a programból.");
            } else {
                System.out.println("Érvénytelen válasz. A program leáll.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close(); // Scanner bezárása
        }
    }
}
