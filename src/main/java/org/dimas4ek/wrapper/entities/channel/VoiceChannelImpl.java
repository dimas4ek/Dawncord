package org.dimas4ek.wrapper.entities.channel;

import org.json.JSONObject;

public class VoiceChannelImpl extends IChannel implements VoiceChannel {

    public VoiceChannelImpl(JSONObject channel) {
        super(channel);
    }
}