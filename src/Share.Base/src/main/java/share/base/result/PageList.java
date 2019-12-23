package share.base.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageList<T> {
    @JsonProperty("List")
    private List<T> list;
    @JsonProperty("PageInfo")
    private PageInfo pageInfo;

    public PageList(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageList(int pageIndex, int pageSize, long totalCount) {
        this(new PageInfo(pageIndex, pageSize, totalCount));
    }

}
