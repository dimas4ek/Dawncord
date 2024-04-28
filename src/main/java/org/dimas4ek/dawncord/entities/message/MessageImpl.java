package org.dimas4ek.dawncord.entities.message;

import com.fasterxml.jackson.databind.JsonNode;
import org.dimas4ek.dawncord.ApiClient;
import org.dimas4ek.dawncord.Routes;
import org.dimas4ek.dawncord.action.MessageModifyAction;
import org.dimas4ek.dawncord.action.ThreadCreateAction;
import org.dimas4ek.dawncord.entities.User;
import org.dimas4ek.dawncord.entities.UserImpl;
import org.dimas4ek.dawncord.entities.application.Application;
import org.dimas4ek.dawncord.entities.application.ApplicationImpl;
import org.dimas4ek.dawncord.entities.channel.GuildChannel;
import org.dimas4ek.dawncord.entities.channel.thread.Thread;
import org.dimas4ek.dawncord.entities.channel.thread.ThreadImpl;
import org.dimas4ek.dawncord.entities.guild.Guild;
import org.dimas4ek.dawncord.entities.guild.GuildMember;
import org.dimas4ek.dawncord.entities.guild.role.GuildRole;
import org.dimas4ek.dawncord.entities.message.component.ActionRow;
import org.dimas4ek.dawncord.entities.message.embed.Embed;
import org.dimas4ek.dawncord.entities.message.poll.Poll;
import org.dimas4ek.dawncord.entities.message.poll.PollImpl;
import org.dimas4ek.dawncord.entities.message.sticker.Sticker;
import org.dimas4ek.dawncord.event.CreateEvent;
import org.dimas4ek.dawncord.event.ModifyEvent;
import org.dimas4ek.dawncord.types.MessageFlag;
import org.dimas4ek.dawncord.types.MessageType;
import org.dimas4ek.dawncord.utils.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MessageImpl implements Message {
    private final JsonNode message;
    private final Guild guild;
    private String id;
    private MessageType type;
    private GuildChannel channel;
    private String content;
    private User author;
    private List<MessageFlag> flags;
    private List<Attachment> attachments;
    private List<Embed> embeds;
    private List<User> mentions;
    private List<GuildRole> mentionRoles;
    private List<Reaction> reactions;
    private Application application;
    private Message referencedMessage;
    private Thread thread;
    private List<Sticker> stickers;
    private Poll poll;
    private List<ActionRow> actionRows;
    private Boolean isPinned;
    private Boolean isMentionEveryone;
    private Boolean isTTS;
    private ZonedDateTime timestamp;
    private ZonedDateTime editedTimestamp;

    public MessageImpl(JsonNode message, Guild guild) {
        this.message = message;
        this.guild = guild;
    }

    @Override
    public String getId() {
        if (id == null) {
            id = message.get("id").asText();
        }
        return id;
    }

    @Override
    public long getIdLong() {
        return Long.parseLong(getId());
    }

    @Override
    public MessageType getType() {
        if (type == null) {
            type = EnumUtils.getEnumObject(message, "type", MessageType.class);
        }
        return type;
    }

    @Override
    public GuildChannel getChannel() {
        if (channel == null) {
            channel = guild.getChannelById(message.get("channel_id").asText());
        }
        return channel;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public CreateEvent<Thread> startThread(String name, Consumer<ThreadCreateAction> handler) {
        String id = ActionExecutor.startThreadFromMessage(handler, this, name);
        return new CreateEvent<>(guild.getThreadById(id));
    }

    @Override
    public void startThread(String name) {
        startThread(name, null);
    }

    @Override
    public String getContent() {
        if (content == null) {
            content = message.has("content")
                    ? message.get("content").asText()
                    : null;
        }
        return content;
    }

    @Override
    public User getFrom() {
        if (author == null) {
            author = new UserImpl(message.get("author"));
        }
        return author;
    }

    @Override
    public List<MessageFlag> getFlags() {
        if (flags == null) {
            flags = EnumUtils.getEnumListFromLong(message, "flags", MessageFlag.class);
        }
        return flags;
    }

    @Override
    public List<Attachment> getAttachments() {
        if (attachments == null) {
            attachments = JsonUtils.getEntityList(message.get("attachments"), Attachment::new);
        }
        return attachments;
    }

    @Override
    public List<Embed> getEmbeds() {
        if (embeds == null) {
            embeds = JsonUtils.getEntityList(message.get("embeds"), EmbedUtils::getEmbedFromJson);
        }
        return embeds;
    }

    @Override
    public List<User> getMentions() {
        if (mentions == null) {
            mentions = JsonUtils.getEntityList(message.get("mentions"), UserImpl::new);
        }
        return mentions;
    }

    @Override
    public List<GuildRole> getMentionRoles() {
        if (mentionRoles == null) {
            mentionRoles = new ArrayList<>();
            for (JsonNode roleNode : message.get("mention_roles")) {
                mentionRoles.add(guild.getRoleById(roleNode.asText()));
            }
        }
        return mentionRoles;
    }

    @Override
    public List<Reaction> getReactions() {
        if (reactions == null) {
            reactions = JsonUtils.getEntityList(message.get("reactions"), reaction -> new ReactionImpl(reaction, guild, this));
        }
        return reactions;
    }

    @Override
    public Reaction getReaction(String emojiIdOrName) {
        if (MessageUtils.isEmojiLong(emojiIdOrName)) {
            return getReactions().stream().filter(reaction -> reaction.getGuildEmoji().getId().equals(emojiIdOrName)).findFirst().orElse(null);
        }
        return getReactions().stream().filter(reaction -> reaction.getEmoji().equals(emojiIdOrName)).findFirst().orElse(null);
    }

    @Override
    public void deleteReactions(String emojiIdOrName) {
        ApiClient.delete(Routes.Channel.Message.Reaction.Get(channel.getId(), getId(), emojiIdOrName));
    }

    @Override
    public void deleteReactions() {
        ApiClient.delete(Routes.Channel.Message.Reaction.All(channel.getId(), getId()));
    }

    @Override
    public Application getApplication() {
        if (application == null) {
            application = message.has("application") ? new ApplicationImpl(message.get("application"), guild) : null;
        }
        return application;
    }

    @Override
    public Message getReferencedMessage() {
        if (referencedMessage == null) {
            referencedMessage = message.has("referenced_message") && message.hasNonNull("referenced_message")
                    ? new MessageImpl(message.get("referenced_message"), guild)
                    : null;
        }
        return referencedMessage;
    }

    @Override
    public Thread getThread() {
        if (thread == null) {
            thread = message.has("thread")
                    ? new ThreadImpl(message.get("thread"), guild)
                    : null;
        }
        return thread;
    }

    @Override
    public List<Sticker> getStickers() {
        if (stickers == null) {
            stickers = MessageUtils.retrieveStickersFromMessage(message, guild);
        }
        return stickers;
    }

    @Override
    public Poll getPoll() {
        if (poll == null) {
            if (message.has("poll") && message.get("poll") != null) {
                poll = new PollImpl(message.get("poll"), guild);
            }
        }
        return poll;
    }

    @Override
    public List<GuildMember> getPollVoters(String answerId) {
        return JsonUtils.getEntityList(
                JsonUtils.fetch(Routes.Channel.Message.Poll.GetAnswerVoters(getChannel().getId(), getId(), answerId)).get("users"),
                guildMember -> guild.getMemberById(guildMember.get("id").asText())
        );
    }

    @Override
    public List<GuildMember> getPollVoters(long answerId) {
        return getPollVoters(String.valueOf(answerId));
    }

    @Override
    public List<ActionRow> getComponents() {
        if (actionRows == null) {
            actionRows = !message.get("components").isEmpty() ? JsonUtils.getEntityList(message.get("components"), actionRow -> new ActionRow(actionRow, getGuild())) : null;
        }
        return actionRows;
    }

    @Override
    public boolean isPinned() {
        if (isPinned == null) {
            isPinned = message.get("pinned").asBoolean();
        }
        return isPinned;
    }

    @Override
    public boolean isMentionEveryone() {
        if (isMentionEveryone == null) {
            isMentionEveryone = message.get("mention_everyone").asBoolean();
        }
        return isMentionEveryone;
    }

    @Override
    public boolean isTTS() {
        if (isTTS == null) {
            isTTS = message.get("tts").asBoolean();
        }
        return isTTS;
    }

    @Override
    public ZonedDateTime getTimestamp() {
        if (timestamp == null) {
            timestamp = MessageUtils.getZonedDateTime(message, "timestamp");
        }
        return timestamp;
    }

    @Override
    public ZonedDateTime getEditedTimestamp() {
        if (editedTimestamp == null) {
            editedTimestamp = message.hasNonNull("edited_timestamp")
                    ? MessageUtils.getZonedDateTime(message, "edited_timestamp")
                    : null;
        }
        return editedTimestamp;
    }

    @Override
    public ModifyEvent<Message> modify(Consumer<MessageModifyAction> handler) {
        ActionExecutor.modifyMessage(handler, this);
        return new ModifyEvent<>(new MessageImpl(JsonUtils.fetch(Routes.Channel.Message.Get(channel.getId(), getId())), guild));
    }

    @Override
    public void delete() {
        ApiClient.delete(Routes.Channel.Message.Get(channel.getId(), getId()));
    }
}