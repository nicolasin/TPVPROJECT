package es.nicolas.uca.tpv.services.Interface;



import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource load(String filename) throws MalformedURLException;

	public String copy(MultipartFile file) throws IOException;

	public void deleteAll();

	public void init() throws IOException;

	public String copyTo(String foldername, MultipartFile file) throws IOException;

	public boolean delete(String filename, String folderName);

	public boolean delete(String foto);
}
