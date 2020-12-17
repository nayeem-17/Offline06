package server.controller.network;

import server.util.NetworkUtil;

public class AsyncWriteData {
    private NetworkUtil networkUtil;

    public AsyncWriteData(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    public void writeData(String message) {
        networkUtil.write(message);
    }
}
