package spelling;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Tony
 * This class supports the playing of audio files.  
 * ----You do not need to edit or change this class at all----
 */
public class AudioHelper 
{
	public static boolean testing = false;
	
    /**
     * Play the audio file at the path selected
     * @param audioFilePath the path to an audio file (.wav is known to work) 
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public static void play(String audioFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
    	if (testing) {return;}
    	File audioFile = new File(audioFilePath);
    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    	AudioFormat format = audioStream.getFormat();
    	DataLine.Info info = new DataLine.Info(Clip.class, format);
    	Clip audioClip = (Clip) AudioSystem.getLine(info);
    	audioClip.open(audioStream);
    	audioClip.start();
    	do 
    	{
    		try{Thread.sleep(300);} catch (InterruptedException e){}
    	} while (audioClip.isActive());
    	audioClip.close();
    	audioStream.close();
    }	
}