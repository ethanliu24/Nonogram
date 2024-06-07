import java.io.*;
import java.nio.file.*;

public class GetMap {

    public static String nameOfMap;
    static int size = Nonogram.size;
    String[][] gameFile = new String[size][];

    public String[][] readMap() {
        String fileName = pickMap(); //C:\\Code\\Nonogram\\Maps\\!names... + map name
        String line;
        int counter = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            line = br.readLine();
            
            while (counter < size && line != null) {
                gameFile[counter] = line.split("");
                counter ++;
                line = br.readLine();
            }

            br.close();
        } catch (IOException iox) {
            
        }
        
        return gameFile;
    }
    
    public String[][] hintsRow() {
        String line;
        int counter = 0;
        boolean noSquares;
        String[][] row = new String[size][];

        for (int i = 0; i < size; i++) {
            line = "";
            noSquares = true;
            for (int j = 0; j < size; j++) {
                if (gameFile[i][j].equals("1")) {
                    counter ++;
                    noSquares = false;
                } else if (gameFile[i][j].equals("0")) {
                    if (counter != 0) {
                        line += counter + " ";
                    }

                    counter = 0;
                }
            }

            if (noSquares) {
                line = "0 ";
            } else if (counter != 0) {
                line += counter + " ";
                counter = 0;
            }

            row[i] = line.split(" ");
        }

        return row;
    }

    public String[][] hintsCol() {
        String line = "";
        int counter = 0;
        boolean noSquares = true;
        String[][] col = new String[size][];

        for (int i = 0; i < size; i++) {
            line = "";
            noSquares = true;
            for (int j = 0; j < size; j++) {
                if (gameFile[j][i].equals("1")) {
                    counter ++;
                    noSquares = false;
                } else {
                    if (counter != 0) {
                        line += counter + " ";
                    }

                    counter = 0;
                }
            }

            if (noSquares) {
                line = "0 ";
            } else if (counter != 0) {
                line += counter + " ";
                counter = 0;
            }

            col[i] = line.split(" ");

        }

        return col;
    }
       
    private static String pickMap() {
        String folderPath;
        String mapNamesPath;
        String mapName = "";
        String line = "";
        int numMaps = 0;
        int mapIndex;

        //access file locations
        if (size == 8) {
            folderPath = "C:\\Code\\Java\\Nonogram\\Maps\\8x8";
            mapNamesPath = "C:\\Code\\Java\\Nonogram\\Maps\\8x8\\!names8.txt";
        } else if (size == 10) {
            folderPath = "C:\\Code\\Java\\Nonogram\\Maps\\10x10";
            mapNamesPath = "C:\\Code\\Java\\Nonogram\\Maps\\10x10\\!names10.txt";
        } else if (size == 15) {
            folderPath = "C:\\Code\\Java\\Nonogram\\Maps\\15x15";
            mapNamesPath = "C:\\Code\\Java\\Nonogram\\Maps\\15x15\\!names15.txt";
        } else {
            folderPath = "C:\\Code\\Java\\Nonogram\\Maps\\20x20";
            mapNamesPath = "C:\\Code\\Java\\Nonogram\\Maps\\20x20\\!names20.txt";
        } 

        try {
            BufferedReader br = new BufferedReader(new FileReader(mapNamesPath));

            //find out how many maps there are
            line = br.readLine();

            while (line != null) {
                numMaps ++;
                line = br.readLine();
            } 

            mapIndex = (int)(Math.random() * numMaps);

            mapName = Files.readAllLines(Paths.get(mapNamesPath)).get(mapIndex);
            nameOfMap = mapName;

            br.close();
        } catch (IOException iox) {
            System.out.println(iox);
        }

        return folderPath + "\\" + mapName + ".txt";
    }
}
