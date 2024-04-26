package org.dimas4ek.wrapper.entities.message.poll;

import com.fasterxml.jackson.databind.JsonNode;
import org.dimas4ek.wrapper.entities.guild.Guild;
import org.dimas4ek.wrapper.utils.JsonUtils;
import org.dimas4ek.wrapper.utils.MessageUtils;

import java.time.ZonedDateTime;
import java.util.List;

public class PollImpl implements Poll {
    private final JsonNode poll;
    private final Guild guild;
    private String question;
    private List<Answer> answers;
    private ZonedDateTime expiry;
    private Boolean multiselect;
    private List<Result> results;

    public PollImpl(JsonNode poll, Guild guild) {
        this.poll = poll;
        this.guild = guild;
    }

    @Override
    public String getQuestion() {
        if (question == null) {
            question = poll.get("question").get("text").asText();
        }
        return question;
    }

    @Override
    public List<Answer> getAnswers() {
        if (answers == null) {
            answers = JsonUtils.getEntityList(poll.get("answers"), answer -> new Answer(answer, guild));
        }
        return answers;
    }

    @Override
    public ZonedDateTime getExpiry() {
        if (expiry == null) {
            if (poll.has("expiry")) {
                expiry = MessageUtils.getZonedDateTime(poll, "expiry");
            }
        }
        return expiry;
    }

    @Override
    public boolean isExpired() {
        return expiry != null && expiry.isBefore(ZonedDateTime.now());
    }

    @Override
    public boolean isMultiselect() {
        if (multiselect == null) {
            multiselect = poll.get("allow_multiselect").asBoolean();
        }
        return multiselect;
    }

    @Override
    public List<Result> getResults() {
        if (results == null) {
            results = JsonUtils.getEntityList(poll.get("results"), Result::new);
        }
        return results;
    }
}
