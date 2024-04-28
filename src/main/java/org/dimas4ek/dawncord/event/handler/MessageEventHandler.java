package org.dimas4ek.dawncord.event.handler;

import org.dimas4ek.dawncord.event.MessageEvent;
import org.dimas4ek.dawncord.types.GatewayEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MessageEventHandler implements EventHandler<MessageEvent> {
    private static final Map<GatewayEvent, Consumer<MessageEvent>> eventHandlers = new HashMap<>();

    @Override
    public Map<GatewayEvent, Consumer<MessageEvent>> getEventHandlers() {
        return eventHandlers;
    }

    public void create(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_CREATE, handler);
    }

    public void update(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_UPDATE, handler);
    }

    public void delete(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_DELETE, handler);
    }

    public void deleteBulk(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_DELETE_BULK, handler);
    }

    public void reactionAdd(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_REACTION_ADD, handler);
    }

    public void reactionRemove(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_REACTION_REMOVE, handler);
    }

    public void reactionRemoveAll(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_REACTION_REMOVE_ALL, handler);
    }

    public void reactionRemoveEmoji(Consumer<MessageEvent> handler) {
        eventHandlers.put(GatewayEvent.MESSAGE_REACTION_REMOVE_EMOJI, handler);
    }
}
