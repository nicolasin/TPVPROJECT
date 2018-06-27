package es.nicolas.uca.tpv.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import es.nicolas.uca.tpv.services.Interface.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final static String UPLOADS_FOLDER = "src/main/resources/static/imagenes/";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathFoto = getPath(filename);
		log.info("pathFoto: " + pathFoto);

		Resource recurso = new UrlResource(pathFoto.toUri());

		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
		}
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		Path rootPath = getPath(UPLOADS_FOLDER + "/" + fileName);
		if (Files.exists(rootPath)) {
			fileName = UUID.randomUUID() + file.getOriginalFilename();
			rootPath = getPath(UPLOADS_FOLDER + "/" + fileName);
		}
		log.info("rootPath: " + rootPath);
		Files.copy(file.getInputStream(), rootPath);
		return fileName;
	}

	@Override
	public String copyTo(String foldername, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		Path rootPath = getPath(fileName, foldername);
		if (Files.exists(rootPath)) {
			fileName = UUID.randomUUID() + file.getOriginalFilename();
			rootPath = getPath(fileName, foldername);
		}
		log.info("rootPath: " + rootPath);
		Files.copy(file.getInputStream(), rootPath);
		return fileName;
	}

	@Override
	public boolean delete(String filename, String folderName) {
		Path rootPath = getPath(filename, folderName);
		File archivo = rootPath.toFile();

		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete()) {
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}
	public Path getPath(String filename, String folder) {
		return Paths.get(UPLOADS_FOLDER+folder+"/").resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());

	}

	@Override
	public void init() throws IOException {
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();

		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete()) {
				return true;
			}
		}
		return false;
		
	}
}
