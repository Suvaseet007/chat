package com.yoctotta.train;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadRepo {
	public String store(MultipartFile file);

	public Path loadFile(String filename);
	
	public Stream<Path> loadAll();
	
	public Resource loadAsResource(String filename);

}
