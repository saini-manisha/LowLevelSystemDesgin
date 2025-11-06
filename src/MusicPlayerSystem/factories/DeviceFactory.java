package MusicPlayerSystem.factories;

import MusicPlayerSystem.device.BluetoothSpeakerAdapter;
import MusicPlayerSystem.device.HeadphonesAdapter;
import MusicPlayerSystem.device.IAudioOutputDevice;
import MusicPlayerSystem.device.WiredSpeakerAdapter;
import MusicPlayerSystem.enums.DeviceType;
import MusicPlayerSystem.external.BluetoothSpeakerAPI;
import MusicPlayerSystem.external.HeadphonesAPI;
import MusicPlayerSystem.external.WiredSpeakerAPI;

public class DeviceFactory {

    public static IAudioOutputDevice createDevice(DeviceType deviceType){
        switch (deviceType){
            case BLUETOOTH:
                return new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
            case WIRED:
                return new WiredSpeakerAdapter(new WiredSpeakerAPI());
            case HEADPHONES:
            default:
                return new HeadphonesAdapter(new HeadphonesAPI());

        }
    }
}
