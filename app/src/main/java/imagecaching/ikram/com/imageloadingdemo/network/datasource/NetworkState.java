package imagecaching.ikram.com.imageloadingdemo.network.datasource;

import imagecaching.ikram.com.imageloadingdemo.network.utils.Constants;

public class NetworkState {
    private String message;
    private @Constants.ContentState int state;

    public NetworkState(@Constants.ContentState int state, String message){
        this.state=state;
        this.message=message;
    }

    public @Constants.ContentState int getState() {
        return state;
    }

    public void setState(@Constants.ContentState int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
