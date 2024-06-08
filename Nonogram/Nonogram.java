import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Nonogram {

    public static int size;
    public static GameState gameState;

    public static void main(String[] args) {
        playMusic("bg_music.wav");
        
        Menu menu = new Menu();
        size = menu.selectDifficulty();
        new Game();

        while (true) {
            System.out.print("");
            if (gameState == GameState.GAME_OVER) {
                size = menu.selectDifficulty();
                new Game();
            }
        }
    }

    public static void playMusic(String fileName) {
        try {
            File musicFile = new File(fileName);

            if (musicFile.exists()) {
                AudioInputStream bgm = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(bgm);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}