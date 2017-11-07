package ShaqAndSoldier.Copyfy.service;

import java.io.InputStream;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 *
 * @author Aram
 */
@Path("/upload")
public class FileUpload {
    private static final String UPLOAD_FOLDER = "C:\\Users\\Aram\\Desktop\\uploadtest;";
    @Context
    private UriInfo context;
    
    @POST
    @Consumes
    public Response uploadFile(
        @FormDataParam("file") InputStream uploadedInputStream,
        @FormDataParam("file") FormDataContentDisposition fileDetail
    ){
        if(uploadedInputStream == null || fileDetail==null){
            return Response.status(400).entity("Invalid form data").build();
        }
        
        try{
            createFolderIfNotExist(UPLOAD_FOLDER);
        }catch(SecurityException e){
            return Response.status(500).entity("Can not create destination folder on server").build();
        }
        
        String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
        
        try{
            saveToFile(uploadedInputStream, uploadedFileLocation);
        }catch(IOException e){
            return Response.status(500).entity("Cannot save File").build();
        }
        return Response.status(200).entity("File saved to " + uploadedFileLocation).build();
    }

    private void createFolderIfNotExist(String dirName ) throws SecurityException {
        File dir = new File(dirName);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    private void saveToFile(InputStream inStream, String target) throws IOException {
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(target));
        
        while ((read = inStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        
        out.flush();
        out.close();
    }
}
