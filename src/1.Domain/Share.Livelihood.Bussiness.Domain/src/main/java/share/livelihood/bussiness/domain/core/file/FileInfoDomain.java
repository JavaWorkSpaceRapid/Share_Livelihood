package share.livelihood.bussiness.domain.core.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import share.domain.EntityBase;

@Document
@Data
@NoArgsConstructor
public class FileInfoDomain extends EntityBase
{
    private String id;
    private String fileName;
    private Long fileSize;
    private String fieldName;
    private String contentType;

    public FileInfoDomain(String id, String fileName, Long fileSize, String fieldName, String contentType) {
        super();
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fieldName = fieldName;
        this.contentType = contentType;
    }
}
