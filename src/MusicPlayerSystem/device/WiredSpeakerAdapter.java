package MusicPlayerSystem.device;

import MusicPlayerSystem.external.WiredSpeakerAPI;
import MusicPlayerSystem.models.Song;

public class WiredSpeakerAdapter implements  IAudioOutputDevice{

    private WiredSpeakerAPI wiredApi;

    public WiredSpeakerAdapter(WiredSpeakerAPI api){
        this.wiredApi=api;
    }

    public void playAudio(Song song){
        String payload=song.getTitle()+" by "+song.getArtist();
        wiredApi.playSoundViaCable(payload);
    }
}
