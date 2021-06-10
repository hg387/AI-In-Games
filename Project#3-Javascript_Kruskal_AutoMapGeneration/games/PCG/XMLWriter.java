import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLWriter {

    final static String req1 = "<A4Game name=\"MapGen2\">\n" +
            "\t<story>\n" +
            "\t\t<line>You have no idea how you got here, find the exit!</line>\n" +
            "\t</story>\n" +
            "\t<ending>\n" +
            "\t\t<line>  Contratulations, you found the exit!</line>\n" +
            "\t</ending>\n" +
            "\n" +
            "\t<tiles sourcewidth=\"64\" sourceheight=\"64\" targetwidth=\"32\" targetheight=\"32\">\n" +
            "\t\t<types file=\"graphics2x-basic.png\">\n" +
            "\t\t\t0,0,1,0,1,0,0,0,0,0,\n" +
            "\t\t\t1,1,1,0,0,0,0,0,0,0,\n" +
            "\t\t\t4,0,1,1,1,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,1,1,1,2,3,1,1,1,0,\n" +
            "\t\t\t0,1,1,1,1,0,0,0,1,0,\n" +
            "\t\t\t0,0,0,1,0,0,1,0,1,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,1,1,1,1,\n" +
            "\t\t\t0,0,0,0,0,1,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,1,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,1,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,1,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,1,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,1,0,0,\n" +
            "\t\t\t0,0,0,0,1,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t</types>\n" +
            "\t\t<seeThrough file=\"graphics2x-basic.png\">\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t1,1,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,1,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,1,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,1,0,1,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,1,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t</seeThrough>\n" +
            "\t\t<canDig file=\"graphics2x-basic.png\">\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t1,1,1,1,1,1,1,1,1,0,\n" +
            "\t\t\t1,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t\t  \n" +
            "  \t\t</canDig>\n" +
            "\t\t<types file=\"graphics2x-characters.png\">\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t</types>\n" +
            "\t\t<seeThrough file=\"graphics2x-characters.png\">\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \t\t\t\t  \n" +
            "\t\t</seeThrough>\n" +
            "\t\t<canDig file=\"graphics2x-characters.png\">\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\n" +
            "\t\t\t0,0,0,0,0,0,0,0,0,0,\t\t\t\t  \t\t\t\t  \n" +
            "  \t\t</canDig> \n" +
            "\t\t<types file=\"graphics2x-walls.png\">\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t</types>\n" +
            "\t\t<seeThrough file=\"graphics2x-walls.png\">\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t\t1,1,\n" +
            "\t\t</seeThrough>\n" +
            "\t\t<canDig file=\"graphics2x-walls.png\">\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "\t\t\t0,0,\n" +
            "  \t\t</canDig>   \t\t \t\t\n" +
            "  \t\t<!-- default animations: -->\n" +
            "\t\t<animation name=\"coinpurse\" dx=\"1\" dy=\"1\" period=\"4\" looping=\"false\" file=\"graphics2x-basic.png\">9</animation>\n" +
            "\t\t<animation name=\"curious\" dx=\"1\" dy=\"1\" period=\"4\" looping=\"true\" file=\"graphics2x-basic.png\">74,-1</animation>\n" +
            "\t\t<animation name=\"scared\" dx=\"1\" dy=\"1\" period=\"4\" looping=\"true\" file=\"graphics2x-basic.png\">84,-1</animation>\n" +
            "\t\t<animation name=\"angry\" dx=\"1\" dy=\"1\" period=\"4\" looping=\"true\" file=\"graphics2x-basic.png\">94,-1</animation>\n" +
            "\t\t<animation name=\"tired\" dx=\"1\" dy=\"1\" period=\"4\" looping=\"true\" file=\"graphics2x-basic.png\">104,-1</animation>\n" +
            "\t\t<animation name=\"happy\" dx=\"1\" dy=\"1\" period=\"4\" looping=\"true\" file=\"graphics2x-basic.png\">75,-1</animation>\n" +
            "\t\t<animation name=\"magic missile\" dx=\"1\" dy=\"1\" period=\"8\" looping=\"false\" file=\"graphics2x.png\">114</animation>\t\t\n" +
            "\t\t<animation name=\"fireball\" dx=\"1\" dy=\"1\" period=\"8\" looping=\"false\" file=\"graphics2x.png\">124</animation>\t\t\n" +
            "\t\t<animation name=\"incinerate\" dx=\"1\" dy=\"1\" period=\"8\" looping=\"false\" file=\"graphics2x.png\">134</animation>\t\t\n" +
            "\t</tiles>\t\n" +
            "\n" +
            "\t<characterDefinition file=\"players.xml\"/>\n" +
            "\t<objectDefinition file=\"objects.xml\"/>\n" +
            "\n" +
            "\t<map file=\"PCGMap.tmx\"/>\n" +
            "\n" +
            "\t<player class=\"Warrior\" x=";

    final static String req2 = "map=\"0\"/>\t\n" +
            "\n" +
            "\t<onStart>\n" +
            "\t\t<addTopic topic=\"yourself\" text=\"tell me about yourself\"/>\n" +
            "\t\t<addTopic topic=\"rumors\" text=\"Have you heard any rumors lately?\"/>\n" +
            "\t</onStart>\n" +
            "</A4Game>";

    public static void writeXML(int initialX, int initialY){
        try(BufferedWriter bw = Files.newBufferedWriter(
                Paths.get("games","example", "example.xml")))
        {
            String newReq = req1 + "\"" + initialX + "\" " + "y=\"" + initialY + "\" " + req2;
            bw.write(newReq);
            bw.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

