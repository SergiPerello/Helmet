package com.sergames;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {
    String url;
    Clip clip = null;

    public Music(String url) {
        this.url = url;
    }

    public Clip start() {
        try {
            URL urlMusic = this.getClass().getResource(url);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(urlMusic);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        return clip;
    }

    void setVolume(double gain) {
        // Get the gain control from clip
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        // set the gain (between 0.0 and 1.0)
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}
