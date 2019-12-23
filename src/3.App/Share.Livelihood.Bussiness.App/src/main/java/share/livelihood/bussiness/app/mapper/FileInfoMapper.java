package share.livelihood.bussiness.app.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import share.livelihood.bussiness.domain.core.file.FileInfoDomain;
import share.livelihood.bussiness.dto.file.FileInfoDto;

import java.util.List;

import static fr.xebia.extras.selma.CollectionMappingStrategy.ALLOW_GETTER;

@Mapper(withIgnoreMissing = IgnoreMissing.ALL,
        withCollectionStrategy = ALLOW_GETTER,
        withIoC = IoC.SPRING)
public interface FileInfoMapper {
    FileInfoDto asFileInfoDto(FileInfoDomain in);

    List<FileInfoDto> asFileInfoDto(List<FileInfoDomain> in);
}
