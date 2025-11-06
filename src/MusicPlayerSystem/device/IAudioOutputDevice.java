package MusicPlayerSystem.device;

import MusicPlayerSystem.models.Song;

public interface IAudioOutputDevice {
    void playAudio(Song song);
}
