package MusicPlayerSystem.device;

import MusicPlayerSystem.external.HeadphonesAPI;
import MusicPlayerSystem.models.Song;

public class HeadphonesAdapter implements IAudioOutputDevice{

    private HeadphonesAPI headphonesApi;

    public HeadphonesAdapter(HeadphonesAPI api)
    {
        this.headphonesApi=api;
    }

    public void playAudio(Song song){
        String payload=song.getTitle()+" by "+ song.getArtist();
        headphonesApi.playSoundViaJack(payload);
    }

}
