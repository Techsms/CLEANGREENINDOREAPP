package com.example.CleanGreenIndore;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private Clip audioClip;

    // Method to load and play a song
    public void playSong(String songFilePath) {
        try {
            // Get audio input stream from the file
            File audioFile = new File(songFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get a sound clip resource
            audioClip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream
            audioClip.open(audioStream);

            // Start playing the audio clip
            audioClip.start();

            // Keep the program running to play the song
            System.out.println("WELCOME TO CLEAN GREEN INDORE APP!!!!!!");
            Thread.sleep(audioClip.getMicrosecondLength() / 1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
            System.out.println("Error playing song: " + ex.getMessage());
        }
    }

    // Method to stop the song
    public void stopSong() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            System.out.println("Song stopped.");
        }
    }

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        // Provide the path to your song file here
        String songFilePath = "C:\\Users\\lenovo\\Desktop\\2018030953final.wav";  // Update this path with your song file
        player.playSong(songFilePath);
    }
}


