package az.lms.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import java.net.MalformedURLException;

import java.nio.file.Path;


/**
 * @author ashraf
 * @project LMS
 */
@Component
@Slf4j
public class FileUtil {

    public Resource load(String filename,Path root) {
        try {
            Path file = root.resolve(filename);
            log.info("path:{}",file);

            Resource resource = new UrlResource(file.toUri());
            log.info("resource:{}",resource);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


}
