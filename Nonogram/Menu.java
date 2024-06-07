import javax.swing.*;

public class Menu {
    public int selectDifficulty() {
        String opt;

        while (true) {
            opt = JOptionPane.showInputDialog(null, 
                "Select Difficulty: \n" +
                "1: Easy (8 x 8) \n" +
                "2: Normal (10 x 10) \n" +
                "3: Hard (15 x 15) \n" +
                "4: Super Hard (20 x 20)");
        
            switch (opt) {
                case "1":
                    return 8;

                case "2":
                    return 10;
                
                case "3":
                    return 15;

                case "4":
                    return 20;
                
                default:
            }
        }
    }

    public static boolean restartGame() {
        int reply = JOptionPane.showConfirmDialog(null, "Play one more time?", " ", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public static void picName() {
        String name = GetMap.nameOfMap;
        name = name.replace("_", " ");
        name = (name.charAt(0) + "").toUpperCase() + name.substring(1);

        JOptionPane.showMessageDialog(null, "You Won!\n\n" + "Pic: " + name);
    }
}
