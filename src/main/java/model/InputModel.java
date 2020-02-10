package model;

import java.util.List;

public class InputModel {
    private List<TapsInformations> taps;

    public InputModel() {
    }

    public InputModel(List<TapsInformations> taps) {
        this.taps = taps;
    }

    public List<TapsInformations> getTaps() {
        return taps;
    }

    public void setTaps(List<TapsInformations> taps) {
        this.taps = taps;
    }
}
