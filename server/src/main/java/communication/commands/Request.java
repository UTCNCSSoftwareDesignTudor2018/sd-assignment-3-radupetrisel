package communication.commands;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.MINIMAL_CLASS)
@JsonSubTypes({
    @Type(value = Login.class),
    @Type(value = ViewArticles.class),
    @Type(value = Close.class)
})
public interface Request {

}
