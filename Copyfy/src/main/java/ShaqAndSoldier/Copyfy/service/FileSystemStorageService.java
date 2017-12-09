package ShaqAndSoldier.Copyfy.service;

import ShaqAndSoldier.Copyfy.controller.FileUploadController;
import ShaqAndSoldier.Copyfy.model.Song;
import ShaqAndSoldier.Copyfy.model.Tag;
import ShaqAndSoldier.Copyfy.repository.SongRepository;
import ShaqAndSoldier.Copyfy.service.exceptions.UserNotValidException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileSystemStorageService implements StorageService {
    @Autowired
    SongRepository sngRepo;
    Song sg;
            
    private final Path rootLocation;
    private final UserService userService;
    
    @Autowired
    public FileSystemStorageService(StorageProperties properties, UserService userService) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.userService = userService;
    }

    @Override
    public void store(MultipartFile file){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            //System.out.println(filename);
            //Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
              //      StandardCopyOption.REPLACE_EXISTING);
            
            byte[] encoded = Base64.encodeBase64(file.getBytes());        
            String base64String = new String(encoded, StandardCharsets.US_ASCII);
            //System.out.println(base64String);
            
            try{
                PrintWriter writer = new PrintWriter("song.txt", "UTF-8");
                writer.println(base64String);
                writer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            sg = new Song();
            sg.setBase64str(base64String);
            sg.setTitle(filename);
            sg.setOwner(userService.getUser().getUsername());
            sg.setAccess(Song.Access.PUBLIC);
            Set<Tag> set = new HashSet<>();
            sg.setTags(set);
            System.out.println(sg.getAccess() + sg.getTags().toString() + sg.getTitle());
            sngRepo.save(sg);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }
        
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
        //return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
    
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
    
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }
}