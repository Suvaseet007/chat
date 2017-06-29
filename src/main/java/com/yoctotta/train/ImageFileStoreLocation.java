package com.yoctotta.train;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class ImageFileStoreLocation implements ImageUploadRepo {
	private final Path rootLocation;

	public ImageFileStoreLocation(StorageLocation location) {
		this.rootLocation = Paths.get(location.getFolderLocation());
	}

	public String store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename().toString());
			}
			String fileName = file.getOriginalFilename();
			String originalFileName = fileName;
			int count = 0;
			while (this.rootLocation.resolve(fileName).toFile().exists()) {
				fileName = originalFileName.split("\\.")[0] + "_" + (count++) + "." + originalFileName.split("\\.")[1];
			}

			Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
			return this.rootLocation.resolve(fileName).toString();

		} catch (IOException io) {
			System.out.println("Failed to store file");
			throw new RuntimeException(io);
		}
	}

	public Path loadFile(String filename) {
		return rootLocation.resolve(filename);
	}

	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 2).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			System.out.println("Failed to read store file");
			throw new RuntimeException(e);
		}
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = loadFile(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
		} catch (MalformedURLException e) {
			System.out.println("Could not read file");
			throw new RuntimeException(e);
		}
		throw new RuntimeException();
	}

}
