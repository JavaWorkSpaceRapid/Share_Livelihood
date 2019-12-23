package share.base.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LiveResult<E> {
    @JsonProperty("Result")
    private int result;
    @JsonProperty("RtnMsg")
    private List<RtnMessage> rtnMsg;
    @JsonProperty("RtnData")
    private E rtnData;

    public LiveResult(int result, List<RtnMessage> rtnMsg, E rtnData) {
        this.result = result;
        this.rtnMsg = rtnMsg;
        this.rtnData = rtnData;
    }
}