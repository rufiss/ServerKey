package com.example.demo;

import com.lacunasoftware.pkiexpress.PadesSigner;
import com.lacunasoftware.pkiexpress.StandardSignaturePolicies;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;




public class DemoApplication {

	public static void main(String[] args) throws IOException {

		PadesSigner signer = new PadesSigner();

		signer.setSignaturePolicy(StandardSignaturePolicies.PadesBasicWithLTV);

		String Filename = "SamplePdf";

		signer.setPdfToSign("C://Users//danie//Documents//ServerKey//demo//src//main//java//com//example//resources//SamplePdf.pdf");

		signer.setPkcs12("C://Users//danie//Documents//ServerKey//demo//src//main//java//com//example//resources//Wayne Enterprises, Inc.pfx");
		signer.setCertPassword("1234");

		signer.setVisualRepresentation(PadesVisualElements.getVisualRepresentation());

		signer.setTrustLacunaTestRoot(true);

		// String outputFile = generateFileId(".pdf", "SamplePdf");
        Path APP_DATA = getDataPath(Filename, ".Pdf");

		signer.setOutputFile(APP_DATA);

		signer.sign();


		System.out.println(APP_DATA);

	}

	public static String generateFileId(String extension, String originalFilename) {
		String filename = originalFilename + extension;
		return filename.replace('.', '_');
	}

	private static Path tempFolderPath;

	public static void createTempFolder() throws IOException {
		if (tempFolderPath == null) {
			tempFolderPath = Files.createTempDirectory("APP_DATA");
		}
	}

	public static Path getTempFolderPath() throws IOException {
		if (tempFolderPath == null) {
			tempFolderPath = Files.createTempDirectory("APP_DATA");
		}
		return tempFolderPath;
	}

	public static Path getDataPath(String fileId) throws IOException {
		return getDataPath(fileId, null);
	}
	public static Path getDataPath(String fileId, String extension) throws IOException {
		String filename = fileId.replace('_', '.');
		// Note: we're passing the filename arguments with "_" as "." because of URL safety
		if (extension != null) {
			filename += extension;
		}

		return getTempFolderPath().resolve(filename);
	}
}
