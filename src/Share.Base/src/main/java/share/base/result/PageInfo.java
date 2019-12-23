package share.base.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageInfo {
    @JsonProperty("PageSize")
    private int pageSize;
    @JsonProperty("PageIndex")
    private int pageIndex;
    @JsonProperty("RecordCount")
    private long recordCount;

    public PageInfo(int pageIndex, int pageSize, long totalCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.recordCount = totalCount;
    }
}
