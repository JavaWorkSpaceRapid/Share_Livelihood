package share.base.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RtnMessage {
    @JsonProperty("RtnMsg")
    private String rtnMsg;
    @JsonProperty("RtnCode")
    private int rtnCode;

    public RtnMessage(String rtnMessage, int rtnCode) {
        this.rtnMsg = rtnMessage;
        this.rtnCode = rtnCode;
    }

}
