package az.lms.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ashraf
 * @project LMS
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class FileUtil {
    private S3Client s3Client;
    @Autowired
    public FileUtil(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String bucketName, String objectKey, MultipartFile multipartFile) throws IOException {
        log.info("file size {}:",multipartFile.getSize());
        if(multipartFile.getSize()>400000){
            throw new MaxUploadSizeExceededException(multipartFile.getSize());
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();
            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, multipartFile.getSize()));
        }
    }
}