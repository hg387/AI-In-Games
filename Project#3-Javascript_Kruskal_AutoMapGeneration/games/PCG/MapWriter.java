import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapWriter {
    public int width = 33;
    public int height = 24;

    public static String requisite = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<map version=\"1.0\" orientation=\"orthogonal\" width=\"33\" height=\"24\" tilewidth=\"64\" tileheight=\"64\">\n" +
            "\t<properties>\n" +
            "\t\t<property name=\"name\" value=\"Blackrock\"/>\n" +
            "\t</properties>\n" +
            "\t<tileset firstgid=\"1\" name=\"graphics\" tilewidth=\"64\" tileheight=\"64\">\n" +
            "\t<image source=\"graphics2x-basic.png\" width=\"640\" height=\"1344\"/>\n" +
            "\t</tileset>\n" +
            "\t<tileset firstgid=\"211\" name=\"walls\" tilewidth=\"64\" tileheight=\"64\">\n" +
            "\t\t<image source=\"graphics2x-walls.png\" width=\"128\" height=\"1024\"/>\n" +
            "\t</tileset>\n";

    public static void writeMap(int[] map){
        try(BufferedWriter bw = Files.newBufferedWriter(
                Paths.get("games","example", "PCGMap.tmx"),
                StandardCharsets.UTF_8))
        {
            bw.write(requisite);

            String layer1 = "\t<layer name=\"Tile Layer 1\" width=\"33\" height=\"24\">\n" +
                    "\t\t<data>\n";
            bw.write(layer1);

            for (int m: map){
                String s = "\t\t\t<tile gid=\"31\"/>\n";
                bw.write(s);
            }

            bw.write("\t\t</data>\n" +
                    "\t</layer>\n");

            String layer2 = "\t<layer name=\"Tile Layer 2\" width=\"33\" height=\"24\">\n" +
                    "\t\t<data>\n";
            bw.write(layer2);

            for (int m: map){
                String s = "\t\t\t<tile gid=\""+ m +"\"/>\n";
                bw.write(s);
            }

            bw.write("\t\t</data>\n" +
                    "\t</layer>\n");

            bw.write("</map>");
            bw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
