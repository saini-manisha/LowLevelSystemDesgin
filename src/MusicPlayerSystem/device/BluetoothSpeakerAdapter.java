package MusicPlayerSystem.device;

import MusicPlayerSystem.external.BluetoothSpeakerAPI;
import MusicPlayerSystem.models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice{

    private BluetoothSpeakerAPI bluetoothApi;
    public BluetoothSpeakerAdapter(BluetoothSpeakerAPI api){
        this.bluetoothApi=api;
    }

    public void playAudio(Song song){
        String payload=song.getTitle()+" by "+song.getArtist();
        bluetoothApi.playSountViaBluetooth(payload);
    }

}
