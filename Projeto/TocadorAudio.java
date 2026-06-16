import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Tocador de audio em formato wav.
 * 
 * @author Arthur Anton
 * @version 09/06/2026
 */

public class TocadorAudio{
    public static void tocarSom(String filePath) {
        try {
            // Cria tipo File com o file plath do arquivo
            File soundFile = new File(filePath);
            
            // Converte o tipo arquivo em tipo de saida de audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            
            // 3. Reserva recursos no sistema
            Clip clip = AudioSystem.getClip();
            
            // 4. Abri a saida de audio e toca
            clip.open(audioStream);
            clip.start();
            
            System.out.println("Tocando audio...");
            
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de audio não suportado.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Linha de audio indisponivel.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de audio.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}